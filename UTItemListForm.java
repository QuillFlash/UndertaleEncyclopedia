import io.materialtheme.darkstackoverflow.DarkStackOverflowTheme;
import mdlaf.MaterialLookAndFeel;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UTItemListForm extends JFrame
{

    private JTextPane itemSQLName;
    private JTextPane itemSQLDescription;
    private JLabel itemSQLImage;
    private JButton previousItemButton;
    private JButton nextItemButton;
    private JButton newEntryButton;
    private JPanel UTItemListFormPanel;

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
        previousItemButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {

            }
        });
        nextItemButton.addActionListener(new ActionListener()
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
