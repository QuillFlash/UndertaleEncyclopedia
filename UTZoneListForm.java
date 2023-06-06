import io.materialtheme.darkstackoverflow.DarkStackOverflowTheme;
import mdlaf.MaterialLookAndFeel;

import javax.swing.*;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UTZoneListForm extends JFrame implements RowViewButtonClick
{
    private JLabel zoneSQLImage;
    private JLabel zoneSQLName;
    private JLabel zoneSQLDescription;
    private JButton nextZoneButton;
    private JButton previousZoneButton;
    private JButton newEntryButton;
    private JPanel UTZoneListFormPanel;
    Connection connection = ConnectToDatabase.getConnection();
    private ResultSet sqlResultSet;

    public JPanel getUTZoneListFormPanel()
    {
        return UTZoneListFormPanel;
    }

    static
    {
        try
        {
            UIManager.setLookAndFeel(new MaterialLookAndFeel(new DarkStackOverflowTheme()));
        }
        catch (UnsupportedLookAndFeelException ulfe)
        {
            ulfe.printStackTrace();
        }
    }

    public UTZoneListForm()
    {
        try
        {
            PreparedStatement preparedDataQueryStatement = connection.prepareStatement("SELECT * FROM zones WHERE id = 1;");
            sqlResultSet = preparedDataQueryStatement.executeQuery();
            if (sqlResultSet.next())
            {
                InputStream imageStream = sqlResultSet.getBinaryStream("image");
                String name = sqlResultSet.getString("name");
                String description = sqlResultSet.getString("description");
                UEDBTableRepresentation zoneTable = new UEDBTableRepresentation();
                Icon imageIcon = zoneTable.setImage(imageStream);
                zoneTable.setName(name);
                zoneTable.setDescription(description);
                zoneSQLImage.setIcon(imageIcon);
                zoneSQLName.setText(name);
                zoneSQLDescription.setText(description);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "No results found");
            }
            initialiseUI();
        }
        catch (Exception exception)
        {
            JOptionPane.showMessageDialog(null, "Oops, an error occurred. Here's the error's full description:\n" + exception);
        }
    }


    private void initialiseUI()
    {
        previousZoneButton.addActionListener(actionEvent -> actionOnLeftButtonClick(sqlResultSet));
        nextZoneButton.addActionListener(actionEvent -> actionOnRightButtonClick(sqlResultSet));
        newEntryButton.addActionListener(actionEvent ->
        {
            AddDBEntriesForm dbEntriesForm = new AddDBEntriesForm();
            dbEntriesForm.setContentPane(dbEntriesForm.getAddDBEntriesFormPanel());
            dbEntriesForm.setTitle("Undertale Encyclopedia - New Entry");
            dbEntriesForm.setSize(600, 600);
            dbEntriesForm.setVisible(true);
        });
    }

    @Override
    public void actionOnLeftButtonClick(ResultSet resultSet)
    {
        try
        {
            PreparedStatement preparedDataQueryStatement = connection.prepareStatement("SELECT * FROM zones;");
            resultSet = preparedDataQueryStatement.executeQuery();
            if (resultSet.isFirst())
            {
                previousZoneButton.setVisible(false);
            }
            else
            {
                if (resultSet.previous())
                {
                    ElementStorage.storeElements(resultSet, zoneSQLImage, zoneSQLName, zoneSQLDescription);
                }
                nextZoneButton.setVisible(true);
            }
        }
        catch (Exception exception)
        {
            JOptionPane.showMessageDialog(null, "Ooops, an error occurred. Here's the error's full description:\n" + exception);
        }
    }

    @Override
    public void actionOnRightButtonClick(ResultSet resultSet)
    {
        try
        {
            PreparedStatement preparedDataQueryStatement = connection.prepareStatement("SELECT * FROM zones;");
            resultSet = preparedDataQueryStatement.executeQuery();
            if (resultSet.isLast())
            {
                nextZoneButton.setVisible(false);
            }
            else
            {
                if (resultSet.next())
                {
                    ElementStorage.storeElements(resultSet, zoneSQLImage, zoneSQLName, zoneSQLDescription);
                }
                previousZoneButton.setVisible(true);
            }
        }
        catch (Exception exception)
        {
            JOptionPane.showMessageDialog(null, "Ooops, an error occurred. Here's the error's full description:\n" + exception);
        }
    }
}