import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectToDatabase
{
    public static Connection getConnection()
    {
        Connection mariaDbConnection;
        try
        {
            mariaDbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/UndertaleEncyclopedia", "root", "");
        }
        catch (SQLException exception)
        {
            JOptionPane.showMessageDialog(null, "Oops, an error occurred. Here's the error's full description:\n" + exception);

            mariaDbConnection = null;
        }
        return mariaDbConnection;
    }
}