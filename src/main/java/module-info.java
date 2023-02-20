module wesfritzc195pa {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens wesfritzc195pa to javafx.fxml;
    opens wesfritzc195pa.toolbox to javafx.base;
    exports wesfritzc195pa;
    opens wesfritzc195pa.models to javafx.base;
    exports wesfritzc195pa.controllers;
    opens wesfritzc195pa.controllers to javafx.fxml;
}