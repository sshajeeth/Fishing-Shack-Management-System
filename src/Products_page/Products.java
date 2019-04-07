package Products_page;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Products extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Products.fxml"));
        primaryStage.setTitle("Jeff's Fishing Shack");
        primaryStage.setScene(new Scene(root));
        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.show();
        primaryStage.setMaximized(true);

    }
}
