package Log_in_page;

import Database_connect.DB_connection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Log_in_controller {
     @FXML
     TextField email;
     @FXML
     PasswordField password;


    public void Log_in(ActionEvent actionEvent) throws SQLException, IOException, InterruptedException {
        //Database connection from db_connection class
        DB_connection db_connection = new DB_connection();
        Connection my_connection = db_connection.getDB_connect();
        Statement myStatement = my_connection.createStatement();
        ResultSet myresultSet = myStatement.executeQuery("SELECT * FROM user");//SQL Path
        String dbEmail = "";
        String dbPassword = "";
        String dbAddress = "";
        String dbName = "";
        int dbId = 0;

        while (myresultSet.next()) {
            //Getting data from database
            dbEmail = myresultSet.getString("Email");
            dbPassword = myresultSet.getString("Password");
            dbId = Integer.parseInt(myresultSet.getString("ID"));
            dbAddress = myresultSet.getString("Address");
            dbName = myresultSet.getString("Name");

            //Email and Password Validation
            if (email.getText().equals("staff@server.com") && password.getText().equals("staff123")) {

                Information.setEmailInfo(email.getText());
                Information.setPasswordInfo(password.getText());
                Information.userId = dbId;
                Information.setAddress(dbAddress);
                Information.setNameInfo(dbName);

                loadWindow("/Staffs_page/Staff.fxml", "Staff Page");
                ((Node) actionEvent.getSource()).getScene().getWindow().hide();


                break;
            } else if (email.getText().equals("jeff@server.com") && password.getText().equals("jeff123")) {
                Information.setEmailInfo(email.getText());
                Information.setPasswordInfo(password.getText());
                Information.userId = dbId;
                Information.setAddress(dbAddress);
                Information.setNameInfo(dbName);
                loadWindow("/Owner_page/Owner.fxml", "Owner Page");
                ((Node) actionEvent.getSource()).getScene().getWindow().hide();
                break;
            } else if (dbEmail.equals(email.getText()) && dbPassword.equals(password.getText())) {
                Information.setEmailInfo(email.getText());
                Information.setPasswordInfo(password.getText());
                Information.userId = dbId;
                Information.setAddress(dbAddress);
                Information.setNameInfo(dbName);
                loadWindow("/Products_page/Products.fxml", "Products");
                ((Node) actionEvent.getSource()).getScene().getWindow().hide();
                break;

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("");
                alert.setContentText("Password is incorrect!");
                alert.showAndWait();
            }
        }
    }

    public void Sign_up(ActionEvent actionEvent) throws IOException {
        //New Activity for the create account link
        loadWindow("/Sign_up_page/Sign_up.fxml", "Signup");
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
