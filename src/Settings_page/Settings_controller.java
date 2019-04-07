package Settings_page;

import Database_connect.DB_connection;
import Log_in_page.Information;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Settings_controller {
    public PasswordField oldPassword;
    public PasswordField newPassword;
    public PasswordField reEnterPassword;
    public TextField newAddress;
    //DB Connection
    DB_connection db_connection = new DB_connection();
    Connection my_connection = db_connection.getDB_connect();


    public void changePassword(ActionEvent actionEvent) throws SQLException {
        int digit = 0;
        char element;
        for (int index = 0; index < newPassword.getText().length(); index++) {
            element = newPassword.getText().charAt(index);
            if (Character.isDigit(element)) {
                digit++;
            }
        }
        //Validating Password
        if (!oldPassword.getText().equals(Information.getPasswordInfo())) {
            errorAlert("Invalid Old Password!");
        } else if (!newPassword.getText().equals(reEnterPassword.getText())) {
            errorAlert("Password Doens not Match!");
        } else if (digit >= 2){
            try {
                int id = Information.getUserId();
                String password = newPassword.getText();
                //Updating Password on database
                PreparedStatement pst = my_connection.prepareStatement("update  user set Password=? where id=" + id);
                pst.setString(1, password);
                pst.execute();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("");
                alert.setContentText("Password Changed Successfully");
                alert.showAndWait();
            } catch (Exception e) {
            }


        }else {
            errorAlert("Password must be contain 2 non-alphabatic characters!");
        }
    }

    public void changeAddress(ActionEvent actionEvent) {
        try {
            int id = Information.getUserId();
            String address = newAddress.getText();
            //Updating address on database
            PreparedStatement pst = my_connection.prepareStatement("update  user set Address=? where id=" + id);
            pst.setString(1, address);
            pst.execute();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("");
            alert.setContentText("Address Changed Successfully");
            alert.showAndWait();
        } catch (Exception e) {
        }
    }
    public void back(ActionEvent actionEvent) throws IOException {
        // New activity for the back button
        loadWindow("/Products_page/Products.fxml", "Products");
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
    }

    public void errorAlert(String x) {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("");
        alert.setContentText(x);
        alert.showAndWait();
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
