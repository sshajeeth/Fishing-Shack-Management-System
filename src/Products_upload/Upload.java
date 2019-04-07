package Products_upload;

import Database_connect.DB_connection;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Upload {
    public void productsUpload() throws SQLException, FileNotFoundException {
        //Database Connection
        DB_connection db_connection = new DB_connection();
        Connection myConnection = db_connection.getDB_connect();
        //Images path on Laptop
        File image1 = new File("C:\\Users\\sshaj\\Desktop\\rods.png");
        File image2 = new File("C:\\Users\\sshaj\\Desktop\\reels.png");
        File image3 = new File("C:\\Users\\sshaj\\Desktop\\hooks.png");
        File image4 = new File("C:\\Users\\sshaj\\Desktop\\fishinglines.png");
        File image5 = new File("C:\\Users\\sshaj\\Desktop\\sinkers.png");
        File image6 = new File("C:\\Users\\sshaj\\Desktop\\swivels.png");

        try {
            //uploading photos to database
            PreparedStatement ps = myConnection.prepareStatement("insert into products(code,imagename,image,size)" + " values(?,?,?,?)");
            FileInputStream product1 = new FileInputStream(image1);
            ps.setString(1, "RO");
            ps.setString(2, "Rods");
            ps.setBinaryStream(3, (InputStream) product1, (int) image1.length());
            ps.setString(4, "Light Power,Medium Power,Heavy Power");
            ps.executeUpdate();

            FileInputStream product2 = new FileInputStream(image2);
            ps.setString(1, "RE");
            ps.setString(2, "Reels");
            ps.setBinaryStream(3, (InputStream) product2, (int) image2.length());
            ps.setString(4, "Small,Medium,Large");
            ps.executeUpdate();

            FileInputStream product3 = new FileInputStream(image3);
            ps.setString(1, "HO");
            ps.setString(2, "Hooks");
            ps.setBinaryStream(3, (InputStream) product3, (int) image3.length());
            ps.setString(4, "Small,Medium,Large");
            ps.executeUpdate();

            FileInputStream product4 = new FileInputStream(image4);
            ps.setString(1, "FL");
            ps.setString(2, "Fishing Lines");
            ps.setBinaryStream(3, (InputStream) product4, (int) image4.length());
            ps.setString(4, "100m,200m,300m");
            ps.executeUpdate();

            FileInputStream product5 = new FileInputStream(image5);
            ps.setString(1, "SI");
            ps.setString(2, "Sinkers");
            ps.setBinaryStream(3, (InputStream) product5, (int) image5.length());
            ps.setString(4, "1000g,2000g,3000g");
            ps.executeUpdate();

            FileInputStream product6 = new FileInputStream(image6);
            ps.setString(1, "SW");
            ps.setString(2, "Swivels");
            ps.setBinaryStream(3, (InputStream) product6, (int) image6.length());
            ps.setString(4, "Small,Medium,Large");
            ps.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error");

        }

    }
}
