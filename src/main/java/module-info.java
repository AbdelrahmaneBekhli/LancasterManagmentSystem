module com.example.lancastermanagmentsystem {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.lancastermanagmentsystem to javafx.fxml;
    exports com.example.lancastermanagmentsystem;
}