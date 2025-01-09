package com.dilazehra.endless_night.sceneController;

import com.dilazehra.endless_night.FxmlLoader;
import com.dilazehra.endless_night.GameLogic;
import com.dilazehra.endless_night.ImageLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

import static com.dilazehra.endless_night.GameLogic.endKey;
import static com.dilazehra.endless_night.GameLogic.getText;

public class ConverseController {

    private Parent root;
    private String imageName;
    int current = 0;


    @FXML
    private TextArea bottom_text;

    @FXML
    private ImageView avatar;

    @FXML
    private Button next_btn;

    @FXML
    private Button back_btn;

    @FXML
    private ImageView main_image;



    //duplicate what to do?

    @FXML
    void btn_clicked(ActionEvent event) throws IOException {
        if(event.getSource() == next_btn) {
            boolean pass = false;
            if(!Objects.equals(getText(imageName, current), endKey)){
                setText(getText(imageName, current));
                setButtons(5);
                current++;
                return;
            }
            else{
                setButtons(2);//this means the last line of the image
                pass = true;
            }
            if(!pass) return;

            //TODO can be put in a method
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
            stage.show();///sdf
        } else if (event.getSource() == back_btn) {
            if(!Objects.equals(getText(imageName, current), endKey)){
                setText(getText(imageName, current));
                setButtons(5);
                current--;
            }
            else{
                setButtons(1);//this means the first line of the image
            }
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
        imageName = imagePath;
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
        else{
            next_btn.setDisable(false);
            back_btn.setDisable(false);
        }
    }



}
