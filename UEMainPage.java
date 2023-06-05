import io.materialtheme.darkstackoverflow.DarkStackOverflowTheme;
import mdlaf.MaterialLookAndFeel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UEMainPage extends JFrame
{
    private JPanel UEMainPagePanel;
    private JLabel UEWelcomeText;
    private JPanel labelPanel;
    private JButton zoneListButton;
    private JButton characterListButton;
    private JButton itemListButton;
    private JLabel selectionLabel;

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

    public static void main(String[] args)
    {
        UEMainPage mainPage = new UEMainPage();
        mainPage.setDefaultCloseOperation(EXIT_ON_CLOSE);
        mainPage.setContentPane(mainPage.UEMainPagePanel);
        mainPage.setTitle("Undertale Encyclopedia - Main Page");
        mainPage.setSize(800, 800);
        mainPage.setVisible(true);
    }

    public UEMainPage()
    {
        zoneListButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                UTZoneListForm zoneListForm = new UTZoneListForm();
                zoneListForm.setContentPane(zoneListForm.getUTZoneListFormPanel());
                zoneListForm.setTitle("Undertale Encyclopedia - Zones");
                zoneListForm.setSize(600, 600);
                zoneListForm.setVisible(true);
            }
        });
        characterListButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                UTCharacterListForm characterListForm = new UTCharacterListForm();
                characterListForm.setContentPane(characterListForm.getUTCharacterListFormPanel());
                characterListForm.setTitle("Undertale Encyclopedia - Characters");
                characterListForm.setSize(600, 600);
                characterListForm.setVisible(true);
            }
        });
        itemListButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                UTItemListForm itemListForm = new UTItemListForm();
                itemListForm.setContentPane(itemListForm.getUTItemListFormPanel());
                itemListForm.setTitle("Undertale Encyclopedia - Items");
                itemListForm.setSize(600, 600);
                itemListForm.setVisible(true);
            }
        });
    }
}
