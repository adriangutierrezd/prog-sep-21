module com.example.prog_sep_2021 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.prog_sep_2021 to javafx.fxml;
    exports com.example.prog_sep_2021;
}