package Enquiry;

import Database_connect.DB_connection;
import Sign_up_page.Sign_up_controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Enquiry_controller {
    public TextField name;
    public TextArea enquiry;
    public TextField email;
    final String senderEmailID = "jeffsfishingshack@gmail.com";
    final String senderPassword = "shajeeth44";
    final String emailSMTPserver = "smtp.gmail.com";
    final String emailServerPort = "465";
    String receiverEmailID = null;
    static String emailSubject = null;
    static String emailBody = null;


    public void Submit(ActionEvent actionEvent) {
        receiverEmailID = "jeffsfishingshack@gmail.com";//receiver email address
        emailSubject = name.getText();//email Subject
        emailBody = name.getText() +"\n"+email.getText()+"\n"+enquiry.getText();//Email body
        Properties properties = new Properties();
        properties.put("mail.smtp.user", senderEmailID);
        properties.put("mail.smtp.host", emailSMTPserver);
        properties.put("mail.smtp.port", emailServerPort);
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.socketFactory.port", emailServerPort);
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.socketFactory.fallback", "false");
        SecurityManager security = System.getSecurityManager();

        try {
            Authenticator auth = new SMTPAuthenticator();
            Session session = Session.getInstance(properties, auth);
            MimeMessage msg = new MimeMessage(session);
            msg.setText(emailBody);
            msg.setSubject(emailSubject);
            msg.setFrom(new InternetAddress(senderEmailID));
            msg.addRecipients(Message.RecipientType.TO, String.valueOf(new InternetAddress(receiverEmailID)));
            Transport.send(msg);

            //An alert box
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("");
            alert.setContentText("Thank you "+name.getText()+"Your Enquiry was submitted. We will reply soon as possible! ");
            alert.showAndWait();

            loadWindow("/Products_page/Products.fxml", "Products");
            ((Node) actionEvent.getSource()).getScene().getWindow().hide();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public class SMTPAuthenticator extends Authenticator {
        public PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(senderEmailID, senderPassword);
        }
    }



    public void loadWindow(String location, String title) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(location));
        Scene scene = new Scene(root);
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(scene);
        stage.setTitle(title);
        stage.show();
    }



}
