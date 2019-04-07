package Staffs_page;

import Database_connect.DB_connection;
import Owner_page.Information_owner;
import Owner_page.Owner_controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Staff_controller{
    public TableView<Information_owner> table;
    public TableColumn<Information_owner,String> one;
    public TableColumn<Information_owner,String> two;
    public TableColumn<Information_owner,String> three;
    public TableColumn<Information_owner,String> four;
    public TableColumn<Information_owner,Integer> five;
    public TableColumn<Information_owner,Double> six;
    public ObservableList<Information_owner> data;
    public TextField name;
    DB_connection db_connection = new DB_connection();
    Connection my_connection = db_connection.getDB_connect();

    public void searchByEmail(ActionEvent actionEvent) {
        try {
            data = FXCollections.observableArrayList();
            String v = name.getText();
            ResultSet rs = my_connection.createStatement().executeQuery("select * from bill where customerEmail='" + v +"'"   );


            while (rs.next()){
                data.add(new Information_owner(rs.getString("date"),rs.getString("customerId"),rs.getString("productId"),
                        rs.getString("productName"),rs.getString("qty"),rs.getString("price")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        one.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        two.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        three.setCellValueFactory(new PropertyValueFactory<>("productId"));
        four.setCellValueFactory(new PropertyValueFactory<>("productName"));
        five.setCellValueFactory(new PropertyValueFactory<>("productQty"));
        six.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));

        table.setItems(null);
        table.setItems(data);
    }
}
