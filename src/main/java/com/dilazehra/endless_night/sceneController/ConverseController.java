package com.dilazehra.endless_night.sceneController;

import com.dilazehra.endless_night.FxmlLoader;
import com.dilazehra.endless_night.ImageLoader;
import com.dilazehra.endless_night.gameLogic.JSONManager;
import com.dilazehra.endless_night.gameLogic.SceneObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static com.dilazehra.endless_night.GameLogic.endKey;
import static com.dilazehra.endless_night.GameLogic.getText;

public class ConverseController {

    private Parent root;
    private String imageName;
    private String sceneName;
    private List<SceneObject> sceneInfo;

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
    private void handleScene(SceneObject obj){
        if(!obj.getText().equals("NONE")){
            setText(obj.getText());
            bottom_text.setOpacity(1);
        }
        else{
            bottom_text.setOpacity(0);
        }

        if(!obj.getAvatar().equals("NONE")){
            setAvatar(obj.getAvatar());
            avatar.setOpacity(1);
        }
        else{
            avatar.setOpacity(0);
        }
    }
    @FXML
    void btn_clicked(ActionEvent event) throws IOException {
        if(event.getSource() == next_btn) {
            boolean pass = false;
            current++;

            if(current < sceneInfo.size()){
                SceneObject k = sceneInfo.get(current);
                handleScene(k);

                setMainImage(k.getBackground());
                setButtons(5);//TODO NEEDS TO BE FİXED
                //current++;
                return;
            }
            else{
                pass = true;
            }
            if(!pass) return;

            //TODO can be put in a method
//            FXMLLoader loader = FxmlLoader.load("doubleOptionScene");
//            root = loader.load();
//            //image can now be set
//            DoubleOptionController dController = loader.getController();
//            dController.setImage(ImageLoader.load("image1.png"));
//            dController.setLeft_btn("8");
//            dController.setRight_btn("4");
//
//            Stage stage = (Stage) next_btn.getScene().getWindow();
//
//            Scene scene = new Scene(root);
//            stage.setTitle("Hello!");
//            stage.setScene(scene);
//            stage.show();///sdf

            //todo have not added two option scenes
            //todo duplicate

            String nextScene = JSONManager.getNextScene(sceneName);
            System.out.println(sceneName);//for debugging

            FXMLLoader loader = FxmlLoader.load("converseScene");
            root = loader.load();
            ConverseController cController = loader.getController();
            cController.setSceneName(nextScene);
            Stage stage = (Stage) next_btn.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle("Hello!");
            stage.setScene(scene);
            stage.show();

        } else if (event.getSource() == back_btn) {

            current--;

            if(current > 0){
                SceneObject k = sceneInfo.get(current);
                handleScene(k);

                setMainImage(k.getBackground());
                setButtons(5);//TODO NEEDS TO BE FİXED

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
    }

    //1 means first, 2 means last
    void setButtons(int stat){
        if(stat == 1){
            back_btn.setDisable(true);
            next_btn.setDisable(false);

        }
        else if(stat == 2){
            next_btn.setDisable(true);
            back_btn.setDisable(false);
        }
        else{
            next_btn.setDisable(false);
            back_btn.setDisable(false);
        }
    }
    void setSceneName(String sceneName) throws IOException {
        System.out.println(current);
        this.sceneName = sceneName;
        sceneInfo = JSONManager.getSceneArray(sceneName);

        SceneObject k = sceneInfo.get(current);

        setText(k.getText());
        setAvatar(k.getAvatar());
        setMainImage(k.getBackground());
        setButtons(1);//TODO NEEDS TO BE FİXED
    }

}
