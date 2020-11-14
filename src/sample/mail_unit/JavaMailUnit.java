//package sample.mail_unit;
//
//import javax.mail.*;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.util.Date;
//import java.util.Properties;
//
///*
//    Существует 4 протокола работы с почтой
//    IMAP - протокол получения почты, работа происходит на сервере
//    POP3 - протокол получения почты, работает на нешм локальном устройстве
//    SMTP - протокол отправки почты
// */
//
//public class JavaMailUnit {
//    public static void main(String[] args) throws IOException, MessagingException {
//        // считаем данные из нашего файла config_mail.properties
//        FileInputStream fileInputStream = new FileInputStream("config_mail.properties");
//        Properties properties = new Properties();
//        properties.load(fileInputStream);
//
//        // создаем 3 константы
//        String user = properties.getProperty("mail.user");
//        String password = properties.getProperty("mail.password");
//        String host = properties.getProperty("mail.host");
//
//        // куда отправляем
//        String to = "aleksander.chugunoff@yandex.ru";
//        Integer port = 465;
//        // кто обрабатывает наши сообщения host
//        //String hostSMTP = host;
//        // порт на стороне host через который все это будет проходить
//        //Integer port = 465;
//
//        // Собираем объект, который будет содержать все эти конфигурации
//        Properties proper = new Properties();
//        proper.put("mail.smtp.host", host);
//        proper.put("mail.smtp.ssl.enable", "true");
//        proper.put("mail.smtp.port", port);
//        // в яндексе необходимо произвести авторизацию
//        proper.put("mail.smtp.auth", "true");
//
//        // создаем сессию
//
//        Session session = Session.getInstance(proper, new Authenticator() {
//            @Override
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(user, password);
//            }
//        });
//
//        // собираем наше сообщение в какой то определенной сесси
//        Message message = new MimeMessage(session); // текст html and others
//        message.setFrom(new InternetAddress(user));
//        InternetAddress address = new InternetAddress(to);
//        // передаем в ресурсы, кто будет принимать
//        message.setRecipient(Message.RecipientType.TO, address);
//        message.setSubject("Email From MyContacts App");
//        // дата отправки
//        message.setSentDate(new Date());
//        // текст сообщения
//        message.setText("Are you registered");
//
//        // обращаемся к Transport который отправляет наше сообщение
//        Transport.send(message);
//    }
//}
