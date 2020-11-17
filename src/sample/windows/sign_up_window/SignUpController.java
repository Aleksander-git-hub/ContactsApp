package sample.windows.sign_up_window;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.omg.CORBA.DATA_CONVERSION;
import sample.animations.Shake;
import sample.dao.DataBaseHandler;
import sample.users.User;
import sample.windows.add_window.AddWindowController;
import sample.windows.manager_window.ManagerWindowController;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SignUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField loginNewUser;

    @FXML
    private PasswordField passwordNewUser;

    @FXML
    private TextField emailNewUser;

    @FXML
    private Button signUpNewUser;


    @FXML
    void initialize() {

        signUpNewUser.setOnAction(event -> {
            String loginText = loginNewUser.getText().trim();
            String passwordText = passwordNewUser.getText().trim();
            String emailText = emailNewUser.getText().intern();
            DataBaseHandler handler = new DataBaseHandler();

            User user = new User(loginText, passwordText, emailText);
            /*// передаем пользователя который зайдет в ManagerWindow
            AddWindowController.myUser(user);*/

            //User user = new User(loginText, passwordText, emailText);

            if (!loginText.equals("") && !passwordText.equals("")) {
                boolean flag = signUser(user, handler);
                // если не зарегистрирован, то добавляем в БД
                if (flag) {
                    System.out.println("Success");
                    handler.signUpUser(user);
                    // после добавления в БД отсылаем на почту сообщение об успешной регистрации
                    try {
                        sendMail(user);
                    } catch (IOException | MessagingException e) {
                        e.printStackTrace();
                    }
                    //  после добавления нового user заходим в ManagerWindow
                    signUpNewUser.getScene().getWindow().hide();
                    ManagerWindowController.entranceToManagerWindow();
                }
            } else {
                shakeFields();
                System.out.println("Fields are empty");
            }

        });
    }

    private void sendMail(User user) throws IOException, MessagingException {
        FileInputStream fileInputStream = new FileInputStream("config_mail.properties");
        Properties properties = new Properties();
        properties.load(fileInputStream);

        String adminUser = properties.getProperty("mail.user");
        String adminPassword = properties.getProperty("mail.password");
        String mailHost = properties.getProperty("mail.host");
        String mailSSL = properties.getProperty("mail.ssl");
        String mailPort = properties.getProperty("mail.port");

        String recipient = user.getEmail();

        Properties properties1 = new Properties();
        properties1.put("mail.smtp.host", mailHost);
        properties1.put("mail.smtp.ssl.enable", mailSSL);
        properties1.put("mail.smtp.port", mailPort);
        properties1.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(properties1, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(adminUser, adminPassword);
            }
        });

        Transport.send(buildMessage(session, adminUser, recipient, user.getLogin()));
    }

    private Message buildMessage(Session session, String adminUser, String recipient,
                              String login) throws MessagingException {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(adminUser));
        InternetAddress address = new InternetAddress(recipient);
        message.setRecipient(Message.RecipientType.TO, address);
        message.setSubject("Successful registration message");
        message.setSentDate(new Date());
        message.setText("Dear, " + login + "!\n" +
                "You are successfully registered in our application \"MyContacts\". " +
                "We wish you a pleasant use!");
        return message;
    }

    // проверяем зарегистрирован уже такой пользователь или нет
    private boolean signUser(User user, DataBaseHandler handler) {
        boolean flag = false;
        ResultSet resultSet = handler.getUser(user);
        long counter = 0L;
        while (true) {
            try {
                if (!resultSet.next()) break;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            counter++;
        }
        if (counter == 0L) {
            flag = true;
        } else {
            shakeFields();
            System.out.println("This user is already exist");
        }
        return flag;
    }

    private void shakeFields() {
        Shake shakeLogin = new Shake(loginNewUser);
        Shake shakePassword = new Shake(passwordNewUser);
        Shake shakeEmail = new Shake(emailNewUser);
        shakeLogin.playAnimation();
        shakePassword.playAnimation();
        shakeEmail.playAnimation();
    }


}
