package Owner_page;

import Database_connect.DB_connection;
import Staffs_page.Staff_controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
//use inheritance to implement jeff can do satff work
public class Owner_controller extends Staff_controller {
    public TableView<Information_owner> table;
    public TableColumn<Information_owner,String> one;
    public TableColumn<Information_owner,String> two;
    public TableColumn<Information_owner,String> three;
    public TableColumn<Information_owner,String> four;
    public TableColumn<Information_owner,Integer> five;
    public TableColumn<Information_owner,Double> six;
    public ObservableList<Information_owner>data;
    public TextField date;
    public TextField month;
    public TextField year;
    public TextField name;

    //Database Connection
    DB_connection db_connection = new DB_connection();
    Connection my_connection = db_connection.getDB_connect();


    public void search(ActionEvent actionEvent) {
        try {
            data = FXCollections.observableArrayList();
            String s  =date.getText()+month.getText()+year.getText();
            String v = name.getText();
            //SQL Path
            ResultSet rs = my_connection.createStatement().executeQuery("select * from bill where date=" +s  );

            while (rs.next()) {
                //Getting Data from database
                data.add(new Information_owner(rs.getString("date"), rs.getString("customerId"), rs.getString("productId"),
                        rs.getString("productName"), rs.getString("qty"), rs.getString("price")));


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //Adding Data to the table
        one.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        two.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        three.setCellValueFactory(new PropertyValueFactory<>("productId"));
        four.setCellValueFactory(new PropertyValueFactory<>("productName"));
        five.setCellValueFactory(new PropertyValueFactory<>("productQty"));
        six.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        //one.setCellValueFactory(new PropertyValueFactory<>("customerId"));

        table.setItems(null);
        table.setItems(data);
    }


}
