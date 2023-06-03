import io.materialtheme.darkstackoverflow.DarkStackOverflowTheme;
import mdlaf.MaterialLookAndFeel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UTCharacterListForm extends JFrame
{
    private JLabel characterPageTitle;
    private JButton previousCharacterButton;
    private JTextPane characterSQLName;
    private JLabel characterNameLabel;
    private JTextPane characterSQLDescription;
    private JLabel characterSQLImage;
    private JButton nextCharacterButton;
    private JButton newEntryButton;
    private JPanel UTCharacterListFormPanel;

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
        previousCharacterButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {

            }
        });
        nextCharacterButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {

            }
        });
        newEntryButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                AddDBEntriesForm dbEntriesForm = new AddDBEntriesForm();
                dbEntriesForm.setContentPane(dbEntriesForm.getAddDBEntriesFormPanel());
                dbEntriesForm.setTitle("Undertale Encyclopedia - New Entry");
                dbEntriesForm.setSize(600, 600);
                dbEntriesForm.setVisible(true);
            }
        });
    }
}
