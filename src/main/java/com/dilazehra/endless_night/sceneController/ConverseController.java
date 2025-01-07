package com.dilazehra.endless_night.sceneController;

import com.dilazehra.endless_night.FxmlLoader;
import com.dilazehra.endless_night.ImageLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class ConverseController {

    private Parent root;


    @FXML
    private Text bottom_text;

    @FXML
    private ImageView avatar;

    @FXML
    private Button next_btn;

    @FXML
    private Button back_btn;

    @FXML
    private ImageView main_image;

    @FXML
    private Label text_part;


    //duplicate what to do?

    @FXML
    void btn_clicked(ActionEvent event) throws IOException {
        if(event.getSource() == next_btn) {
            FXMLLoader loader = FxmlLoader.load("doubleOptionScene");
            root = loader.load();
            //image can now be set
            DoubleOptionController dController = loader.getController();
            dController.setImage(ImageLoader.load("image1.png"));
            dController.setLeft_btn("8");
            dController.setRight_btn("4");

            Stage stage = (Stage) next_btn.getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setTitle("Hello!");
            stage.setScene(scene);
            stage.show();
        }
    }

    void setText(String text){
        bottom_text.setText(text);
    }
    //path needs to be checked
    void setAvatar(String imagePath){
        avatar.setImage(new Image(getClass().getResourceAsStream(imagePath)));
    }
    void setMainImage(String imagePath){
        main_image.setImage(new Image(getClass().getResourceAsStream(imagePath)));
//        main_image.setImage(new Image(imagePath));
    }

    //1 means first, 2 means last
    void setButtons(int stat){
        if(stat == 1){
            back_btn.setDisable(true);
        }
        else if(stat == 2){
            next_btn.setDisable(true);
        }
    }



}
