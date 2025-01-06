package com.dilazehra.endless_night.sceneController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class entranceSceneController {

    @FXML
    private Button start_btn;

    @FXML
    private Text welcome_text;

    @FXML
    void btn_clicked(ActionEvent event) {
        System.out.println("now we start");
    }

}
