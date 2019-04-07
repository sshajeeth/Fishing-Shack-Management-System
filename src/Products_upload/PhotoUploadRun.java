package Products_upload;

import java.io.FileNotFoundException;
import java.sql.SQLException;

public class PhotoUploadRun {
    public static void main(String[]args) throws FileNotFoundException, SQLException {
        Upload upload = new Upload();
        upload.productsUpload();
    }
}
