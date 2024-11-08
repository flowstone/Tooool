package tech.xueyao.tooool;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;

import java.io.IOException;

public class ToooolApplication extends Application {
    private final Log log = LogFactory.get();

    @Override
    public void start(Stage stage) throws IOException {
        log.info("---- 主界面开始启动 ----");
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