package Servlets.User;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import jakarta.mail.*;
import jakarta.mail.internet.*;

public class MailUtils {

    public static void sendEmail(String fromName, String to, String subject, String messageText) throws MessagingException, UnsupportedEncodingException {

        // Gmail thực tế để gửi
        final String username = "trieule.727050@gmail.com";
        final String password = "dzkbhfzgbaousluw"; // app password 16 ký tự

        // Cấu hình SMTP Gmail
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Tạo session
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        // Tạo email
        Message msg = new MimeMessage(session);

        // Người gửi hiển thị (name), email vẫn là username
        msg.setFrom(new InternetAddress(username, fromName));

        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        msg.setSubject(subject);
        msg.setText(messageText);

        // Gửi
        Transport.send(msg);
    }
}
