package tech.xueyao.tooool;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;

import java.io.IOException;

public class ToooolApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ToooolApplication.class.getResource("tooool.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 700);
        stage.setTitle("超级小工具");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.getScene().getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}