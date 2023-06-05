import io.materialtheme.darkstackoverflow.DarkStackOverflowTheme;
import mdlaf.MaterialLookAndFeel;

import javax.swing.*;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UTZoneListForm extends JFrame implements RowViewButtonClick
{

    private JLabel zonePageTitle;
    private JLabel zoneNameLabel;
    private JLabel zoneDescriptionLabel;
    private JLabel zoneSQLImage;
    private JLabel zoneSQLName;
    private JLabel zoneSQLDescription;
    private JButton nextZoneButton;
    private JButton previousZoneButton;
    private JButton newEntryButton;
    private JPanel UTZoneListFormPanel;
    private ResultSet sqlResultSet;
    Connection connection = ConnectToDatabase.getConnection();

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
                UEDBZoneTable zoneTable = new UEDBZoneTable();
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
            if (resultSet.isFirst())
            {
                previousZoneButton.setVisible(false);
            }
            else
            {
                if (resultSet.previous())
                {
                    int id = resultSet.getInt(("id"));
                    if (id > 0)
                    {
                        storeElements(resultSet, id);
                    }
                }
                nextZoneButton.setVisible(true);
            }
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, "Ooops, an error occurred. Here's the error's full description:\n" + exception);
        }
    }

    @Override
    public void actionOnRightButtonClick(ResultSet resultSet)
    {
        try
        {
            if (resultSet.isLast())
            {
                nextZoneButton.setVisible(false);
            }
            else
            {
                if (resultSet.next())
                {
                    int id = resultSet.getInt(("id"));
                    if (id > 0)
                    {
                        storeElements(resultSet, id);
                    }
                }
                previousZoneButton.setVisible(true);
            }
        }
        catch (Exception exception)
        {
            JOptionPane.showMessageDialog(null, "Ooops, an error occurred. Here's the error's full description:\n" + exception);
        }
    }

    private void storeElements(ResultSet resultSet, int id)
    {
        try
        {
            UEDBZoneTable tableElements = new UEDBZoneTable();
            tableElements.setId(id);
            tableElements.setImage(resultSet.getBinaryStream("image"));
            tableElements.setName(resultSet.getString("name"));
            tableElements.setDescription(resultSet.getString("description"));
            zoneSQLImage.setIcon(tableElements.getImage());
            zoneSQLName.setText(tableElements.getName());
            zoneSQLDescription.setText(tableElements.getDescription());
        }
        catch (SQLException sqlException)
        {
            JOptionPane.showMessageDialog(null, "Ooops, an error occurred. Here's the error's full description:\n" + sqlException);
        }
    }
}