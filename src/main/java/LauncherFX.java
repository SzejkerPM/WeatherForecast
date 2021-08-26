import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

public class LauncherFX extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxml/weatherForecastMain.fxml")));
        stage.setScene(new Scene(root, 640, 480));
        stage.setTitle("Pogodynka");
        stage.setResizable(false);
        stage.getIcons().add(new Image("images/icon.png"));
        stage.show();
    }
}
