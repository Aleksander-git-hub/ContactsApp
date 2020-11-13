package sample.dao;

/*
 Work with DB
 */

import sample.users.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataBaseHandler extends Configs {
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
    public void signUpUser(User user) {
        // в этой строке SQL запрос в БД при помощи которого добавляем
        String insert = "INSERT INTO " + Const.USER_TABLE + " (" +
                Const.USER_LOGIN + ", " + Const.USER_PASSWORD + ", " +
                Const.USER_EMAIL + ") " + "VALUES(?,?,?)";

        // прописываем данные которые здесь "VALUES(?,?,?)"
        try {
            PreparedStatement statement = getDbConnection().prepareStatement(insert);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            // выполнить эту команду
            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }
}
