package com.dilazehra.endless_night.sceneController;

import com.dilazehra.endless_night.FxmlLoader;
import com.dilazehra.endless_night.ImageLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class entranceSceneController {
    private Parent root;
    @FXML
    private Button start_btn;

    @FXML
    private Text welcome_text;

    @FXML
    void btn_clicked(ActionEvent event) throws IOException {
        if(event.getSource() == start_btn) {
            System.out.println("now we start");
            FXMLLoader loader = FxmlLoader.load("doubleOptionScene");
            root = loader.load();
            //image can now be set
            DoubleOptionController dController = loader.getController();
            dController.setImage(ImageLoader.load("image1.png"));
            dController.setLeft_btn("8");
            dController.setRight_btn("4");

            Stage stage =(Stage) start_btn.getScene().getWindow();

            Scene scene = new Scene(loader.load());
            stage.setTitle("Hello!");
            stage.setScene(scene);
            stage.show();
        }
    }

}
