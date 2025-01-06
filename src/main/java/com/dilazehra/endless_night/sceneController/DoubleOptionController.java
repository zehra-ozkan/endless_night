package com.dilazehra.endless_night.sceneController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class DoubleOptionController {

    private Image image;
    @FXML
    private Button left_btn;

    @FXML
    private ImageView img_view;

    @FXML
    private Button right_btn;

    @FXML
    void btn_clicked(ActionEvent event) {
        if(event.getSource() == left_btn) {
            System.out.println("you are wrong");
        }
        else if(event.getSource() == right_btn) {
            System.out.println("you are right");
        }
    }
    void setImage(String newImage) {
        this.image = new Image(getClass().getResourceAsStream(newImage));
        img_view.setImage(this.image);
    }
    void setRight_btn(String text){
        right_btn.setText(text);
    }
    void setLeft_btn(String text){
        left_btn.setText(text);
    }

}
