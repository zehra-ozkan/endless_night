module com.dilazehra.endless_night {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires json;

    opens com.dilazehra.endless_night to javafx.fxml;
    exports com.dilazehra.endless_night;
    exports com.dilazehra.endless_night.sceneController;
    opens com.dilazehra.endless_night.sceneController to javafx.fxml;
    exports com.dilazehra.endless_night.gameLogic;
    opens com.dilazehra.endless_night.gameLogic to javafx.fxml;
}