module uam.edu.ni.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;

    opens uam.edu.ni.demo to javafx.fxml;
    opens uam.edu.ni.demo.controller to javafx.fxml;
    opens uam.edu.ni.demo.DATA to javafx.base;
    exports uam.edu.ni.demo;
}