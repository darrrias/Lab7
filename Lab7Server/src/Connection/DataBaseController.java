package Connection;

import DataBase.CitiesDataBase;
import DataBase.UsersDataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseController {
//    private final String url = "jdbc:postgresql://localhost:5432/studs";
//    private final String user = "postgres";
    private final String url = "jdbc:postgresql://pg:5432/studs";
    private final String user = "s283945";
    private final String password = "iow988";
    private Connection connection;
    private Statement statement;
    private static UsersDataBase users;
    private static CitiesDataBase citiesDataBase;

    public DataBaseController() throws SQLException {
        connection = DriverManager.getConnection(url, user, password);
        statement = connection.createStatement();
        users = new UsersDataBase(connection);
        citiesDataBase = new CitiesDataBase(connection);
    }

    protected static UsersDataBase getUserDataBase() {
        return users;
    }

    public static CitiesDataBase getcitiesDataBase() {
        return citiesDataBase;
    }
}