import io.materialtheme.darkstackoverflow.DarkStackOverflowTheme;
import mdlaf.MaterialLookAndFeel;

import javax.swing.*;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UTItemListForm extends JFrame implements RowViewButtonClick
{

    private JLabel itemSQLImage;
    private JButton previousItemButton;
    private JButton nextItemButton;
    private JButton newEntryButton;
    private JPanel UTItemListFormPanel;
    private JLabel itemSQLName;
    private JLabel itemSQLDescription;
    Connection connection = ConnectToDatabase.getConnection();
    private ResultSet sqlResultSet;

    public JPanel getUTItemListFormPanel()
    {
        return UTItemListFormPanel;
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
    public UTItemListForm()
    {
        try
        {
            PreparedStatement preparedDataQueryStatement = connection.prepareStatement("SELECT * FROM items WHERE id = 1;");
            sqlResultSet = preparedDataQueryStatement.executeQuery();
            if (sqlResultSet.next())
            {
                InputStream imageStream = sqlResultSet.getBinaryStream("image");
                String name = sqlResultSet.getString("name");
                String description = sqlResultSet.getString("description");
                UEDBTableRepresentation characterTableRepresentation = new UEDBTableRepresentation();
                Icon imageIcon = characterTableRepresentation.setImage(imageStream);
                characterTableRepresentation.setName(name);
                characterTableRepresentation.setDescription(description);
                itemSQLImage.setIcon(imageIcon);
                itemSQLName.setText(name);
                itemSQLDescription.setText(description);
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
        previousItemButton.addActionListener(actionEvent -> actionOnLeftButtonClick(sqlResultSet));
        nextItemButton.addActionListener(actionEvent -> actionOnRightButtonClick(sqlResultSet));
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
            PreparedStatement preparedDataQueryStatement = connection.prepareStatement("SELECT * FROM items WHERE NOT id = 1;");
            resultSet = preparedDataQueryStatement.executeQuery();
            if (resultSet.isFirst())
            {
                previousItemButton.setVisible(false);
            }
            else
            {
                if (resultSet.previous())
                {
                    ElementStorage.storeElements(resultSet, itemSQLImage, itemSQLName, itemSQLDescription);
                }
                nextItemButton.setVisible(true);
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
            PreparedStatement preparedDataQueryStatement = connection.prepareStatement("SELECT * FROM items WHERE NOT id = 1;");
            resultSet = preparedDataQueryStatement.executeQuery();
            if (resultSet.isLast())
            {
                nextItemButton.setVisible(false);
            }
            else
            {
                if (resultSet.next())
                {
                    ElementStorage.storeElements(resultSet, itemSQLImage, itemSQLName, itemSQLDescription);
                }
                previousItemButton.setVisible(true);
            }
        }
        catch (Exception exception)
        {
            JOptionPane.showMessageDialog(null, "Ooops, an error occurred. Here's the error's full description:\n" + exception);
        }
    }
}
