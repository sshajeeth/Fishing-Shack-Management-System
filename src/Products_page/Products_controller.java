package Products_page;

import Database_connect.DB_connection;
import LinkedListImplement.LinkedList;
import Log_in_page.Information;
import Payment_page.AddToCart;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Products_controller implements Initializable {

    public Label hooksLabel;
    public Label fishingLinesLabel;
    public Label sinkersLabel;
    public Label swivelsLabel;
    public Label reelsLabel;
    public Label rodsLabel;
    public Label total;
    public Label rodsPrice;
    public Label reelsPrice;
    public Label hooksPrice;
    public Label fishingLinesPrice;
    public Label sinkersPrice;
    public Label swivelsPrice;
    public TextField rodsQty;
    public TextField reelsQty;
    public TextField hooksQty;
    public TextField fishingLinesQty;
    public TextField sinkersQty;
    public TextField swivelsQty;
    public static ObservableList<AddToCart> sha = FXCollections.observableArrayList();
     ImageView[] imageViews = new ImageView[7];
    public ImageView pro1;
    public ImageView pro2;
    public ImageView pro3;
    public ImageView pro4;
    public ImageView pro5;
    public ImageView pro6;
    @FXML
    Button rods;
     Label[] label = new Label[7];
     double totalAmount;
    public static int count1 = 1;

    public static ArrayList<String> code_list = new ArrayList<String>();
    public static ArrayList<String>  name_list = new ArrayList<String>();
    public static ArrayList<String>  cost_list = new ArrayList<String>();
    public static ArrayList<String>  qty_list = new ArrayList<String>();
    public static ArrayList<String>  amount_list = new ArrayList<String>();

//Database Connection
     DB_connection db_connection = new DB_connection();
     Connection my_connection = db_connection.getDB_connect();
    //Getting today Date
     DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
     Calendar calendar = Calendar.getInstance();



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Retrieving Product Images and products labels from database
        try {
            imageViews[2] = pro1;
            imageViews[3] = pro2;
            imageViews[1] = pro3;
            imageViews[4] = pro4;
            imageViews[5] = pro5;
            imageViews[6] = pro6;


            label[2] = rodsLabel;
            label[3] = reelsLabel;
            label[1] = hooksLabel;
            label[4] = fishingLinesLabel;
            label[5] = sinkersLabel;
            label[6] = swivelsLabel;

            PreparedStatement pst = null;
            byte[] b;
            int count = 0;
            Blob blob;
            String labels = "null";
            for (int i = 1; i < 7; i++) {
                String sql = "select * from products where id=" + i;//SQL path
                pst = my_connection.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();


                FileOutputStream os = new FileOutputStream(new File("sinkers.png"));

                while (rs.next()) {
                    //Getting data from database
                    blob = rs.getBlob("image");
                    labels = rs.getString("imagename");
                    b = blob.getBytes(1, (int) blob.length());
                    os.write(b);

                    os.close();

                    Image image = new Image("File:sinkers.png", 100, 150, true, true);
                    imageViews[i].setImage(image);
                    label[i].setText(labels);
                }
            }

            pst.close();


        } catch (Exception ex) {
            System.out.println("not success");

        }


    }

    public void settings(ActionEvent actionEvent) throws IOException {
        //New activity for the settings button
        loadWindow("/Settings_page/Settings.fxml", "Settings");
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
    }

    public void checkout(ActionEvent actionEvent) throws IOException, SQLException {
        //New activity for the checkout button
        loadWindow("/Payment_page/Payment.fxml", "Settings");
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
    }

    //Rods Size
    public void rodsSize1(ActionEvent actionEvent) {
        String rodsSize = "Low Power";
        rodsPrice.setText("100.00");
    }

    public void rodsSize2(ActionEvent actionEvent) {
        String rodsSize = "Medium Power";
        rodsPrice.setText("200.00");
    }

    public void rodsSize3(ActionEvent actionEvent) {
        String rodsSize = "Heavy Power";
        rodsPrice.setText("300.00");
    }

    //Reels Size
    public void reelsSize1(ActionEvent actionEvent) {
        String reelsSize = "small";
        reelsPrice.setText("100.00");
    }

    public void reelsSize2(ActionEvent actionEvent) {
        String reelsSize = "Medium";
        reelsPrice.setText("200.00");
    }

    public void reelsSize3(ActionEvent actionEvent) {
        String reelsSize = "Large";
        reelsPrice.setText("300.00");
    }

    //Hooks Size
    public void hookssSize1(ActionEvent actionEvent) {
        String hooksSize = "small";
        hooksPrice.setText("100.00");
    }

    public void hooksSize2(ActionEvent actionEvent) {
        String hooksSize = "Medium";
        hooksPrice.setText("200.00");
    }

    public void hooksSize3(ActionEvent actionEvent) {
        String hooksSize = "Large";
        hooksPrice.setText("300.00");
    }

    //Fishing Lines Size
    public void fishingLinesSize1(ActionEvent actionEvent) {
        String fishingLinesSize = "100m";
        fishingLinesPrice.setText("100.00");
    }

    public void fishingLinesSize2(ActionEvent actionEvent) {
        String fishingLinesSize = "200m";
        fishingLinesPrice.setText("200.00");
    }

    public void fishingLinesSize3(ActionEvent actionEvent) {
        String fishingLinesSize = "300m";
        fishingLinesPrice.setText("300.00");
    }

    //Sinkers Size
    public void sinkersSize1(ActionEvent actionEvent) {
        String sinkersSize = "1000g";
        sinkersPrice.setText("100.00");
    }

    public void sinkersSize2(ActionEvent actionEvent) {
        String sinkersSize = "2000g";
        sinkersPrice.setText("200.00");
    }

    public void sinkersSize3(ActionEvent actionEvent) {
        String sinkersSize = "3000g";
        sinkersPrice.setText("300.00");
    }

    //Swivels Size
    public void swivelsSize1(ActionEvent actionEvent) {
        String swivelsSize = "small";
        swivelsPrice.setText("100.00");
    }

    public void swivelsSize2(ActionEvent actionEvent) {
        String swivelsSize = "Medium";
        swivelsPrice.setText("200.00");
    }

    public void swivelsSize3(ActionEvent actionEvent) {
        String swivelsSize = "Large";
        swivelsPrice.setText("300.00");
    }

    //Rods Add to Cart
    public void rods(ActionEvent actionEvent) throws SQLException {
        double rodsTotal = Double.parseDouble(rodsPrice.getText()) * Integer.parseInt(rodsQty.getText());
        totalAmount += rodsTotal;
        if (rodsQty.getText().equals(null)) {
            errorAlert("Please Fill Qty!");
        }
        total.setText(String.valueOf(totalAmount));
        AddToCart.totalAmount1 = String.valueOf(totalAmount);
        sha.add((new AddToCart("RO", "Rods", rodsQty.getText(), rodsPrice.getText(), String.valueOf(rodsTotal))));
        //Adding data to database
        PreparedStatement pstmt = null;
        pstmt = my_connection.prepareStatement("insert into bill (Receipt_id,date,customerId,customerEmail,productId,productName,eachPrice,qty,price)" + "values(?,?,?,?,?,?,?,?,?)");
        pstmt.setString(1, String.valueOf(count1));
        pstmt.setString(2, dateFormat.format(calendar.getTime()));
        pstmt.setString(3, String.valueOf(Information.getUserId()));
        pstmt.setString(4, Information.getEmailInfo());
        pstmt.setString(5, "RO");
        pstmt.setString(6, "Rods");
        pstmt.setString(7, rodsPrice.getText());
        pstmt.setString(8, rodsQty.getText());
        pstmt.setString(9, String.valueOf(rodsTotal));
        pstmt.executeUpdate();
        rodsQty.clear();
        code_list.add("RO");
        name_list.add("Rods");
        cost_list.add(rodsPrice.getText());
        qty_list.add(rodsQty.getText());
        amount_list.add(String.valueOf(rodsTotal));
    }

    //Reels Add to Cart
    public void reels(ActionEvent actionEvent) throws SQLException {
        double reelsTotal = Double.parseDouble(reelsPrice.getText()) * Integer.parseInt(reelsQty.getText());
        totalAmount += reelsTotal;
        if (reelsQty.getText().equals(null)) {
            errorAlert("Please Fill Qty!");
        }
        total.setText(String.valueOf(totalAmount));
        AddToCart.totalAmount1 = String.valueOf(totalAmount);
        sha.add((new AddToCart("RE", "Reels", reelsQty.getText(), reelsPrice.getText(), String.valueOf(reelsTotal))));
        //Adding data to database
        PreparedStatement pstmt = null;
        pstmt = my_connection.prepareStatement("insert into bill (Receipt_id,date,customerId,customerEmail,productId,productName,eachPrice,qty,price)" + "values(?,?,?,?,?,?,?,?,?)");
        pstmt.setString(1, String.valueOf(count1));
        pstmt.setString(2, dateFormat.format(calendar.getTime()));
        pstmt.setString(3, String.valueOf(Information.getUserId()));
        pstmt.setString(4, Information.getEmailInfo());
        pstmt.setString(5, "RE");
        pstmt.setString(6, "Reels");
        pstmt.setString(7, reelsPrice.getText());
        pstmt.setString(8, reelsQty.getText());
        pstmt.setString(9, String.valueOf(reelsTotal));
        pstmt.executeUpdate();
        reelsQty.clear();
        code_list.add("RE");
        name_list.add("Reels");
        cost_list.add(reelsPrice.getText());
        qty_list.add(reelsQty.getText());
        amount_list.add(String.valueOf(reelsTotal));
    }

    //Hooks Add to cart
    public void hooks(ActionEvent actionEvent) throws SQLException {
        double hooksTotal = Double.parseDouble(hooksPrice.getText()) * Integer.parseInt(hooksQty.getText());
        totalAmount += hooksTotal;
        if (hooksQty.getText().equals(null)) {
            errorAlert("Please Fill Qty!");
        }
        total.setText(String.valueOf(totalAmount));
        AddToCart.totalAmount1 = String.valueOf(totalAmount);
        sha.add((new AddToCart("HO", "Hooks", hooksQty.getText(), hooksPrice.getText(), String.valueOf(hooksTotal))));
        //Adding data to database
        PreparedStatement pstmt = null;
        pstmt = my_connection.prepareStatement("insert into bill (Receipt_id,date,customerId,customerEmail,productId,productName,eachPrice,qty,price)" + "values(?,?,?,?,?,?,?,?,?)");
        pstmt.setString(1, String.valueOf(count1));
        pstmt.setString(2, dateFormat.format(calendar.getTime()));
        pstmt.setString(3, String.valueOf(Information.getUserId()));
        pstmt.setString(4, Information.getEmailInfo());
        pstmt.setString(5, "HO");
        pstmt.setString(6, "Hooks");
        pstmt.setString(7, hooksPrice.getText());
        pstmt.setString(8, hooksQty.getText());
        pstmt.setString(9, String.valueOf(hooksTotal));
        pstmt.executeUpdate();


        code_list.add("HO");
        name_list.add("Hooks");
        cost_list.add(hooksPrice.getText());
        qty_list.add(hooksQty.getText());
        amount_list.add(String.valueOf(hooksTotal));
        hooksQty.clear();
    }

    //Fishing Lines Add to Cart
    public void fishingLines(ActionEvent actionEvent) throws SQLException {
        double fishingLinesTotal = Double.parseDouble(fishingLinesPrice.getText()) * Integer.parseInt(fishingLinesQty.getText());
        totalAmount += fishingLinesTotal;
        if (fishingLinesQty.getText().equals(null)) {
            errorAlert("Please Fill Qty!");
        }
        total.setText(String.valueOf(totalAmount));
        AddToCart.totalAmount1 = String.valueOf(totalAmount);
        sha.add((new AddToCart("FL", "Fishing Lines", fishingLinesQty.getText(), fishingLinesPrice.getText(), String.valueOf(fishingLinesTotal))));
        //Adding data to database
        PreparedStatement pstmt = null;
        pstmt = my_connection.prepareStatement("insert into bill (Receipt_id,date,customerId,customerEmail,productId,productName,eachPrice,qty,price)" + "values(?,?,?,?,?,?,?,?,?)");
        pstmt.setString(1, String.valueOf(count1));
        pstmt.setString(2, dateFormat.format(calendar.getTime()));
        pstmt.setString(3, String.valueOf(Information.getUserId()));
        pstmt.setString(4, Information.getEmailInfo());
        pstmt.setString(5, "FL");
        pstmt.setString(6, "Fishing Lines");
        pstmt.setString(7, fishingLinesPrice.getText());
        pstmt.setString(8, fishingLinesQty.getText());
        pstmt.setString(9, String.valueOf(fishingLinesTotal));
        pstmt.executeUpdate();


        code_list.add("FL");
        name_list.add("Fishing Lines");
        cost_list.add(fishingLinesPrice.getText());
        qty_list.add(fishingLinesQty.getText());
        amount_list.add(String.valueOf(fishingLinesTotal));
        fishingLinesQty.clear();
    }

    //Sinkers Add to Cart
    public void sinkers(ActionEvent actionEvent) throws SQLException {
        double sinkersTotal = Double.parseDouble(sinkersPrice.getText()) * Integer.parseInt(sinkersQty.getText());
        totalAmount += sinkersTotal;
        if (sinkersQty.getText().equals(null)) {
            errorAlert("Please Fill Qty!");
        }
        total.setText(String.valueOf(totalAmount));
        AddToCart.totalAmount1 = String.valueOf(totalAmount);
        sha.add((new AddToCart("SI", "Sinkers", sinkersQty.getText(), sinkersPrice.getText(), String.valueOf(sinkersTotal))));
        PreparedStatement pstmt = null;
        //Adding data to database
        pstmt = my_connection.prepareStatement("insert into bill (Receipt_id,date,customerId,customerEmail,productId,productName,eachPrice,qty,price)" + "values(?,?,?,?,?,?,?,?,?)");
        pstmt.setString(1, String.valueOf(count1));
        pstmt.setString(2, dateFormat.format(calendar.getTime()));
        pstmt.setString(3, String.valueOf(Information.getUserId()));
        pstmt.setString(4, Information.getEmailInfo());
        pstmt.setString(5, "SI");
        pstmt.setString(6, "Sinkers");
        pstmt.setString(7, sinkersPrice.getText());
        pstmt.setString(8, sinkersQty.getText());
        pstmt.setString(9, String.valueOf(sinkersTotal));
        pstmt.executeUpdate();


        code_list.add("SI");
        name_list.add("Sinkers");
        cost_list.add(sinkersPrice.getText());
        qty_list.add(sinkersQty.getText());
        amount_list.add(String.valueOf(sinkersTotal));
        sinkersQty.clear();
    }

    //Swivels Add to Cart
    public void swivels(ActionEvent actionEvent) throws SQLException {
        double swivelsTotal = Double.parseDouble(swivelsPrice.getText()) * Integer.parseInt(swivelsQty.getText());
        totalAmount += swivelsTotal;
        if (swivelsQty.getText().equals(null)) {
            errorAlert("Please Fill Qty!");
        }
        total.setText(String.valueOf(totalAmount));
        AddToCart.totalAmount1 = String.valueOf(totalAmount);
        sha.add((new AddToCart("SW", "Swivels", swivelsQty.getText(), swivelsPrice.getText(), String.valueOf(swivelsTotal))));
        //Adding data to database
        PreparedStatement pstmt = null;
        pstmt = my_connection.prepareStatement("insert into bill (Receipt_id,date,customerId,customerEmail,productId,productName,eachPrice,qty,price)" + "values(?,?,?,?,?,?,?,?,?)");
        pstmt.setString(1, String.valueOf(count1));
        pstmt.setString(2, dateFormat.format(calendar.getTime()));
        pstmt.setString(3, String.valueOf(Information.getUserId()));
        pstmt.setString(4, Information.getEmailInfo());
        pstmt.setString(5, "SW");
        pstmt.setString(6, "Swivels");
        pstmt.setString(7, swivelsPrice.getText());
        pstmt.setString(8, swivelsQty.getText());
        pstmt.setString(9, String.valueOf(swivelsTotal));
        pstmt.executeUpdate();


        code_list.add("SW");
        name_list.add("Swivels");
        cost_list.add(swivelsPrice.getText());
        qty_list.add(swivelsQty.getText());
        amount_list.add(String.valueOf(swivelsTotal));
        swivelsQty.clear();
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


    public void enquiry(ActionEvent actionEvent) throws IOException {
        loadWindow("/Enquiry/Enquiry.fxml", "Owner Page");
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
    }
}
