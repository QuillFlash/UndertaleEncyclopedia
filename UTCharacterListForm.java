import io.materialtheme.darkstackoverflow.DarkStackOverflowTheme;
import mdlaf.MaterialLookAndFeel;

import javax.swing.*;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UTCharacterListForm extends JFrame implements RowViewButtonClick
{
    private JButton previousCharacterButton;
    private JLabel characterSQLName;
    private JLabel characterSQLDescription;
    private JLabel characterSQLImage;
    private JButton nextCharacterButton;
    private JButton newEntryButton;
    private JPanel UTCharacterListFormPanel;
    Connection connection = ConnectToDatabase.getConnection();
    private ResultSet sqlResultSet;

    public JPanel getUTCharacterListFormPanel()
    {
        return UTCharacterListFormPanel;
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

    public UTCharacterListForm()
    {
        try
        {
            PreparedStatement preparedDataQueryStatement = connection.prepareStatement("SELECT * FROM characters WHERE id = 1;");
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
                characterSQLImage.setIcon(imageIcon);
                characterSQLName.setText(name);
                characterSQLDescription.setText(description);
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
        previousCharacterButton.addActionListener(actionEvent -> actionOnLeftButtonClick(sqlResultSet));
        nextCharacterButton.addActionListener(actionEvent -> actionOnRightButtonClick(sqlResultSet));
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
            PreparedStatement preparedDataQueryStatement = connection.prepareStatement("SELECT * FROM characters WHERE NOT id = 1;");
            resultSet = preparedDataQueryStatement.executeQuery();
            if (resultSet.isFirst())
            {
                previousCharacterButton.setVisible(false);
            }
            else
            {
                if (resultSet.previous())
                {
                    ElementStorage.storeElements(resultSet, characterSQLImage, characterSQLName, characterSQLDescription);
                }
                nextCharacterButton.setVisible(true);
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
            PreparedStatement preparedDataQueryStatement = connection.prepareStatement("SELECT * FROM characters WHERE NOT id = 1;");
            resultSet = preparedDataQueryStatement.executeQuery();
            if (resultSet.isLast())
            {
                nextCharacterButton.setVisible(false);
            }
            else
            {
                if (resultSet.next())
                {
                    ElementStorage.storeElements(resultSet, characterSQLImage, characterSQLName, characterSQLDescription);
                }
                previousCharacterButton.setVisible(true);
            }
        }
        catch (Exception exception)
        {
            JOptionPane.showMessageDialog(null, "Ooops, an error occurred. Here's the error's full description:\n" + exception);
        }
    }
}
