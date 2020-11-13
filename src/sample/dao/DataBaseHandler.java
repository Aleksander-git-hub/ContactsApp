package sample.dao;

/*
 Work with DB
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataBaseHandler extends Configs
{
    Connection dbConnection;

    public Connection getDbConnection()
            throws ClassNotFoundException, SQLException {
        // строка подключения к БД
        String connectionString = "jdbc:postgresql://" + dbHost + ":"
                + dbPort + "/" + dbName;

        Class.forName("org.postgresql.Driver");

        dbConnection = DriverManager.getConnection(connectionString,
                dbUser, dbPassword);

        return dbConnection;
    }

    // записываем пользователя в БД
    public void signUpUser(String login, String password, String passwordRep, String email) {
        if (password.equals(passwordRep)) {
            // в этой строке SQL запрос в БД при помощи которого добавляем
            String insert = "INSERT INTO " + Const.USER_TABLE + " (" +
                    Const.USER_LOGIN + ", " + Const.USER_PASSWORD + ", " +
                    Const.USER_EMAIL + ") " + "VALUES(?,?,?)";

            // прописываем данные которые здесь "VALUES(?,?,?)"
            try {
                PreparedStatement statement = getDbConnection().prepareStatement(insert);
                statement.setString(1, login);
                statement.setString(2, password);
                statement.setString(3, email);
                // выполнить эту команду
                statement.executeUpdate();
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
        } else {

            System.out.println("Your passwords do not match");
        }
    }
}
