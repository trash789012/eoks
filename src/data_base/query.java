package data_base;

import dialogs.information;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Николай on 24.11.2015.
 */
public class query {
    public static Connection connection;
    public static java.sql.Statement statement;
    public static ResultSet resultSet;

    public static void Connection() throws ClassNotFoundException, SQLException	
    {
        connection = null;
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:Servers.s3db");		//подключение к БД
        //new information("ok", "");
    }

    public static void CreateTB(String ServerName) throws ClassNotFoundException, SQLException {
        statement = connection.createStatement();
        String context = "CREATE TABLE if NOT EXISTS " + ServerName + " (id INTEGER PRIMARY KEY AUTOINCREMENT , storage text);";
        statement.execute(context);	//создание таблицы, если не существует такой
    }

    public static void WriteTB(String ServerName, String Path) throws SQLException {
        statement = connection.createStatement();
        String context = "INSERT INTO " + ServerName + " (storage) VALUES ('" + Path + "');";
        statement.execute(context);	//запись в таблицу
    }

    public static void DeleteTB(String ServerName) throws SQLException {
        statement = connection.createStatement();
        String context = "DROP TABLE " + ServerName + ";";
        statement.execute(context); 		//удалить  позицию
    }

    public static void ReadTB(String ServerName) throws ClassNotFoundException, SQLException {	//вывод результатов
        String context = "SELECT * FROM " + ServerName + ";";

        statement = connection.createStatement();
        resultSet = statement.executeQuery(context);

        while (resultSet.next()) {					
            int id = resultSet.getInt("id");
            String storage = resultSet.getString("storage");
            System.out.println("id = " + id);
            System.out.println("path = " + storage);
            System.out.println();
        }
    }
}
