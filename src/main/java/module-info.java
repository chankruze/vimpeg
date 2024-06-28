module store.geekofia.vimpeg {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    requires org.controlsfx.controls;

    opens store.geekofia.vimpeg to javafx.fxml;
    exports store.geekofia.vimpeg;
    exports store.geekofia.vimpeg.controllers;
    opens store.geekofia.vimpeg.controllers to javafx.fxml;
}