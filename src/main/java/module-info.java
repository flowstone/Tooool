module tech.xueyao.tooool {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires cn.hutool;
    requires java.sql;
    requires java.desktop;

    opens tech.xueyao.tooool to javafx.fxml;
    exports tech.xueyao.tooool;
}