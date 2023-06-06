import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ElementStorage
{
    public static void storeElements(ResultSet resultSet, JLabel image, JLabel name, JLabel description)
    {
        try
        {
            int id = resultSet.getInt("id");
            if (id > 0)
            {
                UEDBTableRepresentation tableElements = new UEDBTableRepresentation();
                tableElements.setId(id);
                tableElements.setImage(resultSet.getBinaryStream("image"));
                tableElements.setName(resultSet.getString("name"));
                tableElements.setDescription(resultSet.getString("description"));
                image.setIcon(tableElements.getImage());
                name.setText(tableElements.getName());
                description.setText(tableElements.getDescription());
            }
        }
        catch (SQLException sqlException)
        {
            JOptionPane.showMessageDialog(null, "Ooops, an error occurred. Here's the error's full description:\n" + sqlException);
        }
    }
}
