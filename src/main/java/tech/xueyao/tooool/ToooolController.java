package tech.xueyao.tooool;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;

import java.io.IOException;

public class ToooolController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onBatchUpdateFilename() {
        FXMLLoader fxmlLoader = new FXMLLoader(ToooolApplication.class.getResource("batch-update-filename.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 600, 400);
            Stage stage = new Stage();
            stage.setTitle("超级小工具");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.getScene().getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}