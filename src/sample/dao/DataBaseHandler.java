package sample.dao;

/*
 Work with DB
 */

import sample.contact.Contact;
import sample.users.User;

import java.sql.*;


public class DataBaseHandler extends Configs {
    Connection dbConnection;
    private static String id = "";

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
            ResultSet idTemp = getUserIdFromDB(user);
            if (idTemp.next()) {
                id = idTemp.getString(Const.USER_ID);
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    // запись контакта в БД
    public void setContact(Contact contact) {
        String insert = "INSERT INTO " + Const.CONTACTS_TABLE + " (" + Const.CONTACT_USER_ID + ", " +
                Const.CONTACT_FIRST_NAME + ", " + Const.CONTACT_SECOND_NAME + ", " +
                Const.CONTACT_PHONE_NUMBER + ", " + Const.CONTACT_EMAIL + ") " +
                "VALUES(?,?,?,?,?)";
        try {
            PreparedStatement statement = getDbConnection().prepareStatement(insert);
            statement.setString(1, id); // находим ID нашего user
            statement.setString(2, contact.getFirstName());
            statement.setString(3, contact.getSecondName());
            statement.setString(4, contact.getPhoneNumber());
            statement.setString(5, contact.getEmail());
            statement.executeUpdate();
            System.out.println("Contact add successfully");
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
            // при входе в приложение в id положим значение id нашего пользователя
            ResultSet idTemp = getUserIdFromDB(user);
            if (idTemp.next()) {
                id = idTemp.getString(Const.USER_ID);
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return resultSet;
    }

    // получаем ID пользователя
    private ResultSet getUserIdFromDB(User user) {
        String login = user.getLogin();
        String password = user.getPassword();
        ResultSet id = null;
        String userId = "SELECT " + Const.USER_ID + " FROM " + Const.USER_TABLE +
                " WHERE " + Const.USER_LOGIN + " = ? AND " +
                Const.USER_PASSWORD + " = ?";
        try {
            PreparedStatement statement = getDbConnection().prepareStatement(userId);
            statement.setString(1, login);
            statement.setString(2, password);
            id = statement.executeQuery();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return id;
    }
}
