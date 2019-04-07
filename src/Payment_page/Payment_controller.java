package Payment_page;

import Database_connect.DB_connection;
import Log_in_page.Information;
import Products_page.Products_controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.ResourceBundle;

public class Payment_controller implements Initializable {
    public Button pay;
    public TextField cardnumber;
    public TextField month;
    public TextField year;
    public TextField cvv;
    public TableView<AddToCart> table;
    public TableColumn<AddToCart, String> one;
    public TableColumn<AddToCart, String> two;
    public TableColumn<AddToCart, String> three;
    public TableColumn<AddToCart, String> four;
    public TableColumn<AddToCart, String> five;

    private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private Calendar calendar = Calendar.getInstance();
    private String senderEmailID = "jeffsfishingshack@gmail.com";
    private String senderPassword = "shajeeth44";
    private String emailSMTPserver = "smtp.gmail.com";
    final String emailServerPort = "465";
    String receiverEmailID = null;
    static String emailSubject = null;

    AddToCart ac = new AddToCart("", "", "", "", "");




    public void errorAlert(String x) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("");
        alert.setContentText(x);
        alert.showAndWait();
    }



    public void pay(ActionEvent actionEvent) {
        int cardNumberDigit = 0;
        char cardNumberElement;
        int cVVDigit = 0;
        char cVVElement;
        for (int index = 0; index < cardnumber.getText().length(); index++) {
            cardNumberElement = cardnumber.getText().charAt(index);
            if (Character.isDigit(cardNumberElement)) {
                cardNumberDigit++;
            }
        }
        for (int index = 0; index < cvv.getText().length(); index++) {
            cVVElement = cvv.getText().charAt(index);
            if (Character.isDigit(cVVElement)) {
                cVVDigit++;
            }
        }
        //Validating card details
        if (cardnumber.getText().equals(null) && month.getText().equals(null) && year.getText().equals(null) && cvv.getText().equals(null)) {
            errorAlert("Please Fill All Fields!");
        } else if (cardNumberDigit != 16) {
            errorAlert("Enter Valid Card Number");
        } else if (month.getText().equals(null)) {
            errorAlert("Please Fill the Month Field!");
        } else if (year.getText().equals(null)) {
            errorAlert("Please Fill the Year Field!");
        } else if (cVVDigit != 3) {
            errorAlert("Please Enter the Valid CVV!");
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("");
            alert.setContentText("Payment Success!");
            alert.showAndWait();
            String x = "<table>" + "<tr><th></th><tr><th>Product Name</th><th>Product ID</th><th>Cost</th><th>Quantity</th><th>Amount</th></tr>";

            for (int i = 0; i < Products_controller.cost_list.size(); i++) {

                x += ("<tr><td> " + Products_controller.name_list.get(i) + "</td><td>" + Products_controller.code_list.get(i) + "</td><td>" + Products_controller.cost_list.get(i) + "</td><td>"+ Products_controller.qty_list.get(i) + "</td><td>" + Products_controller.amount_list.get(i) + "</td></td></tr>");
            }

            x += "</table>";
            //Sending a Tax Invoice
            receiverEmailID = Information.getEmailInfo();
            emailSubject = "Tax Invoice";
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
                msg.setContent("<!DOCTYPE html>\n" +
                        "<html lang=\"en\">\n" +
                        "<head>\n" +
                        "    <meta charset=\"UTF-8\">\n" +
                        "   \n" +
                        "    <title>Invoice</title>\n" +
                        "\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "\n" +
                        "<h2 align=\"center\">Jeff's Fishing Shack</h2>\n" +
                        "<h1 align=\"center\">Tax Invoice</h1>\n" +
                        "\n" +
                        "Jeff's Fishing Shack</br>\n" +
                        "Trading as:Octopus Ptv Ltd</br\n" +
                        "\n" +
                        "Shop 4,Hillarys Boat Harbour</br>\n" +
                        "Hillarys,WA,6025</br>\n" +
                        "T:<a href=\"08 9402 2222\" >08 9402 2222</a><BR>\n" +
                        "E:<a href=\" Sales@JFS.com.au\" >Sales@JFS.com.au</a>\n" +
                        "<BR>\n" +
                        "<p align=\"right\">Date:" + dateFormat.format(calendar.getTime()) + "</p>\n" +
                        "Receipt#:" + Products_controller.count1 + "<BR>\n" +
                        "\n" +
                        "Customer:" + Information.getNameInfo() + "<BR>\n" +
                        "<Br>\n" +
                        "<Br>\n" +
                        "<Br>\n" +
                        "Customer#:" + Information.getUserId() + " <Br>\n" +
                        "Customer email:" + Information.getEmailInfo() + "\n" +
                        "<Br><Br><Br>\n" +
                        "Purchases<Br><Br>\n" +
                        "\n" +x +
                        "<br>\n" +
                        "<br>\n" +
                        "<h2 align=\"right\">Total Paid:</h2>\n" +
                        "<br>\n" +
                        "<br>\n" +
                        "\n" +
                        "<p align=\"center\" style=\"font-size: 20px;\">Thank you for your business.\n" +
                        "</p>\n" +
                        "<p align=\"center\"style=\"font-size: 20px;\">Jeff's-Where the real fisherman shop</p>\n" +
                        "</body>\n" +
                        "</html>", "text/html");
                msg.setSubject(emailSubject);
                msg.setFrom(new InternetAddress(senderEmailID));
                msg.addRecipients(Message.RecipientType.TO, String.valueOf(new InternetAddress(receiverEmailID)));
                Transport.send(msg);
                Products_controller.count1++;


            } catch (Exception e) {
                e.printStackTrace();
            }


        }
        class SMTPAuthenticator extends Authenticator {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmailID, senderPassword);
            }
        }
    }

    public class SMTPAuthenticator extends Authenticator {
        public PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(senderEmailID, senderPassword);
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        one.setCellValueFactory(new PropertyValueFactory<>("productId1"));
        two.setCellValueFactory(new PropertyValueFactory<>("productName1"));
        three.setCellValueFactory(new PropertyValueFactory<>("productQty1"));
        four.setCellValueFactory(new PropertyValueFactory<>("productEach1"));
        five.setCellValueFactory(new PropertyValueFactory<>("totalPrice1"));
        table.setItems(Products_controller.sha);
    }

    public void remove(ActionEvent actionEvent) {
       table.getItems().removeAll(table.getSelectionModel().getSelectedItem());
    }

    public void back(ActionEvent actionEvent) throws IOException {
        //New activity for the back button
        loadWindow("/Products_page/Products.fxml", "Products");
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
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



