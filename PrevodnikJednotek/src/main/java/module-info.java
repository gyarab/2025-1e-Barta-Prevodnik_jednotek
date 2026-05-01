module org.example.prevodnikjednotek {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.prevodnikjednotek to javafx.fxml;
    exports org.example.prevodnikjednotek;
}