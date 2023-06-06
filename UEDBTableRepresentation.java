import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class UEDBTableRepresentation
{
    private int id;
    private Icon image;
    private String name;
    private String description;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public Icon getImage()
    {
        return image;
    }

    public Icon setImage(InputStream imageStream)
    {
        try
        {
            if (imageStream != null)
            {
                BufferedImage bufferedImage = ImageIO.read(imageStream);
                if (bufferedImage != null)
                {
                    image = new ImageIcon(bufferedImage);
                    return image;
                }
            }
        }
        catch (IOException ioException)
        {
            JOptionPane.showMessageDialog(null, "Oops, an error occurred. Here's the error's full description:\n" + ioException);
        }
        return null;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }
}