module uam.edu.ni.demo {
    requires javafx.controls;
    requires javafx.fxml;

    opens uam.edu.ni.demo to javafx.fxml;
    opens uam.edu.ni.demo.controller to javafx.fxml;
    exports uam.edu.ni.demo;
}