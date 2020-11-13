package sample.dao;

/*
 Work with DB
 */

import sample.users.User;

import java.sql.*;

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
            statement.executeUpdate(); // метод, который закидывает в базу данных
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    // метод для получения данных из БД
    // ResultSet будет массив из полей нашего пользователя
    public ResultSet getUser(User user) {
        ResultSet resultSet = null;

        String select = "SELECT * FROM " + Const.USER_TABLE + " WHERE " +
                Const.USER_LOGIN + "= ? AND " + Const.USER_PASSWORD + "= ?";
        try {
            PreparedStatement statement = getDbConnection().prepareStatement(select);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());

            resultSet = statement.executeQuery(); // метод, который позволяет получить из БД
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return resultSet;
    }
}
