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
            FXMLLoader loader = FxmlLoader.load("converseScene");
            root = loader.load();
            //image can now be set
            ConverseController cController = loader.getController();
            cController.setAvatar(ImageLoader.load("cute_avatar.png"));


            cController.setMainImage(ImageLoader.load("image0.png"));
            cController.setText("there was something and now there is not I am this alson known as that now we are toherehe ");
            cController.setButtons(1);

            Stage stage = (Stage) start_btn.getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setTitle("Hello!");
            stage.setScene(scene);
            stage.show();
        }
    }

}
