import io.materialtheme.darkstackoverflow.DarkStackOverflowTheme;
import mdlaf.MaterialLookAndFeel;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UTZoneListForm extends JFrame
{

    private JLabel zonePageTitle;
    private JLabel zoneNameLabel;
    private JLabel zoneDescriptionLabel;
    private JLabel zoneSQLImage;
    private JTextPane zoneSQLName;
    private JTextPane zoneSQLDescription;
    private JButton nextZoneButton;
    private JButton previousZoneButton;
    private JButton newEntryButton;
    private JPanel UTZoneListFormPanel;

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
        previousZoneButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {

            }
        });
        nextZoneButton.addActionListener(new ActionListener()
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
