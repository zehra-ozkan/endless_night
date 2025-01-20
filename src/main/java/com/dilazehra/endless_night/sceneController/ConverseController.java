package com.dilazehra.endless_night.sceneController;

import com.dilazehra.endless_night.FxmlLoader;
import com.dilazehra.endless_night.gameLogic.CharacterObj;
import com.dilazehra.endless_night.gameLogic.JSONManager;
import com.dilazehra.endless_night.gameLogic.SceneObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConverseController {

    private Parent root;
    private String imageName;
    private String sceneName;

    @FXML
    private Pane rootPane;
    private List<SceneObject> sceneInfo;
    private List<CharacterObj> characters;

    int current = 0;


    @FXML
    private ImageView character_image;
    @FXML
    private Button next_character;

    @FXML
    private Button prev_character;
    @FXML
    private TextArea description;
    @FXML
    private TextField nameField;

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
    @FXML
    private Button menu_btn;
    @FXML
    private Button close_btn;

    @FXML
    private Pane menu_pane;


    @FXML
    void keyTyped(KeyEvent event) throws IOException {
        System.out.println("comes here");
        if (event.getCode() == javafx.scene.input.KeyCode.SPACE) {
            System.out.println("Spacebar was typed!");
            moveForward();
        }
    }



    private void moveForward() throws IOException {
        boolean pass = false;

        current++;
        System.out.println("going forward");

        if(current < sceneInfo.size()){
            SceneObject k = sceneInfo.get(current);
            handleScene(k);
            setButtons(5);//TODO NEEDS TO BE FİXED
        }
        else{
            pass = true;
        }
        if(!pass) return;

        //todo have not added two option scenes
        //todo duplicate
        //moves up to the next scene

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
        setButtons(5);


    }
    @FXML
    void btn_clicked(ActionEvent event) throws IOException {
        if(event.getSource() == next_btn) {
            System.out.println("move forward pressed");
          moveForward();

        } else if (event.getSource() == back_btn) {
            System.out.println("move back pressed");

            current--;

            if(current >= 0){
                SceneObject k = sceneInfo.get(current);
                handleScene(k);

               // setMainImage(k.getBackground());
                setButtons(5);//TODO NEEDS TO BE FİXED

            }
            System.out.println("going backward");

        }
    }


    @FXML
    void menu_on(ActionEvent event) {
        menu_pane.setOpacity(1);
        rootPane.setOpacity(0.45);
        System.out.println("menu is now on ");
        close_btn.setDisable(false);
        menu_pane.setMouseTransparent(false);

        //todo maybe for other elements
        menu_btn.setDisable(true);
        menu_btn.setOpacity(0);


    }
    @FXML
    void menu_off(ActionEvent event) {
        rootPane.setOpacity(1);
        menu_pane.setOpacity(0);
        close_btn.setDisable(true);
        System.out.println("menu is off now");
        menu_pane.setMouseTransparent(true);

        //todo maybe for other elements
        menu_btn.setDisable(false);
        menu_btn.setOpacity(1);
    }

    void setText(String text){
        bottom_text.setText(text);
    }
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
        setButtons(1);
    }

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
        if(!obj.getBackground().equals("SAME")) {
            setMainImage(obj.getBackground());
        }

        if(JSONManager.newCharacter(obj.getText())){

        }
    }
    private void addCharacter(String charName){

    }
    @FXML
    public void initialize(){
        System.out.println("enters initilise");
        rootPane.setOpacity(1);
        menu_pane.setOpacity(0);
        close_btn.setDisable(true);
        System.out.println("menu is off now");
        menu_pane.setMouseTransparent(true);

        characters = new ArrayList<>();
    }

}
