import io.materialtheme.darkstackoverflow.DarkStackOverflowTheme;
import mdlaf.MaterialLookAndFeel;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddDBEntriesForm extends JFrame
{
    private JTextField entryNameTextField;
    private JTextField entryDescTextField;
    private JButton selectImageButton;
    private JButton submitEntryButton;
    private JPanel AddDBEntriesFormPanel;

    public JPanel getAddDBEntriesFormPanel()
    {
        return AddDBEntriesFormPanel;
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
    public AddDBEntriesForm()
    {
        selectImageButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {

            }
        });
        submitEntryButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {

            }
        });
    }
}
