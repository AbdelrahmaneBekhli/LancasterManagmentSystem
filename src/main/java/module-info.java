module com.example.lancastermanagmentsystem {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires kernel;
    requires io;
    requires layout;

    opens com.example.lancastermanagmentsystem to javafx.fxml;
    exports com.example.lancastermanagmentsystem;
}