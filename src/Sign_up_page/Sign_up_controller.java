package Sign_up_page;

import Database_connect.DB_connection;
import Log_in_page.Information;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;


public class Sign_up_controller {
    public Button signup;
    public TextField name_id;
    public TextField address_id;
    public TextField mobile_id;
    public TextField email_id;
    public PasswordField password_id;
    public PasswordField repassword_id;
    final String senderEmailID = "jeffsfishingshack@gmail.com";
    final String senderPassword = "shajeeth44";
    final String emailSMTPserver = "smtp.gmail.com";
    final String emailServerPort = "465";
    String receiverEmailID = null;
    static String emailSubject = null;
    static String emailBody = null;
    Alert alert = null;


    public void Sign_up(ActionEvent actionEvent) throws SQLException {
        int digit = 0;
        char element;
        for (int index = 0; index < password_id.getText().length(); index++) {
            element = password_id.getText().charAt(index);
            if (Character.isDigit(element)) {
                digit++;
            }
        }
        //Database Connection
        DB_connection db_connection = new DB_connection();
        Connection my_connection = db_connection.getDB_connect();
        PreparedStatement pstmt = null;
        Statement myStatement = my_connection.createStatement();
        ResultSet resultSet = myStatement.executeQuery("select * from user");
        String dbEmail = "";
        while (resultSet.next()) {
            dbEmail = resultSet.getString("Email");
        }
        try {
            if (name_id.getText().equals("") && address_id.getText().equals("") && mobile_id.getText().equals("")
                    && email_id.getText().equals("") && password_id.getText().equals("")) {
                errorAlert("Please Fill All Fields!!");
            } else if (name_id.getText().equals("")) {
                errorAlert("Please Fill Full Name!!");
            } else if (address_id.getText().equals("")) {
                errorAlert("Please Fill Permanent Address!!");
            } else if (mobile_id.getText().equals("")) {
                errorAlert("Please Fill Mobile Number!!");
            } else if (email_id.getText().equals("")) {
                errorAlert("Please fill Email!!");
            } else if (password_id.getText().equals("")) {
                errorAlert("Please fill a password!!");
            } else if (password_id.getText().length() < 7) {
                errorAlert("Password must be 8 characters!");
            } else if (!repassword_id.getText().equals(password_id.getText())) {
                errorAlert("Password not matched!!");
            } else if (dbEmail.equals(email_id.getText())) {
                errorAlert("Email Already Exists!!");
            } else if (digit >= 2) {
                pstmt = my_connection.prepareStatement("insert into user (Name, Address, Mobile, Email, Password)" + "values(?,?,?,?,?)");
                //inserting details to database
                pstmt.setString(1, name_id.getText());
                pstmt.setString(2, address_id.getText());
                pstmt.setString(3, mobile_id.getText());
                pstmt.setString(4, email_id.getText());
                pstmt.setString(5, password_id.getText());
                pstmt.executeUpdate();
                Information.setNameInfo(name_id.getText());
                Information.setEmailInfo(email_id.getText());
                Information.setPasswordInfo(password_id.getText());
                Information.setAddress(address_id.getText());

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Jeff's Fishing Shack");
                alert.setHeaderText("");
                alert.setContentText("Welcome to Jeff's Fishing Shack!\nYour Account was Created Succesfully...");
                alert.showAndWait();
                receiverEmailID = email_id.getText();
                emailSubject = "Welcome to Jeff's Fishing Shack!!!";
                emailBody = "Hi Mr/Miss/Mrs." + Information.getNameInfo() + ",\nWe are warmly welcome to Jeff's Fishing Shack.\n\n\nThank You ";

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


                } catch (Exception e) {
                    e.printStackTrace();
                }
                loadWindow("/Products_page/Products.fxml", "Products");
                ((Node) actionEvent.getSource()).getScene().getWindow().hide();
            } else {
                alert.setContentText("Password must be contain 2 non-alphabatic characters!");
            }


        } catch (Exception e) {
            errorAlert("Error");
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
        Stage stage = new Stage(StageStyle.UTILITY);
        stage.setScene(scene);
        stage.setTitle(title);
        stage.show();
    }

    public void errorAlert(String x) {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("");
        alert.setContentText(x);
        alert.showAndWait();
    }


}
