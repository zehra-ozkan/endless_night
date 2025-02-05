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
    int currentCharacter;


    @FXML
    private ImageView character_image;
    @FXML
    private Button next_character, prev_character, next_btn, back_btn, menu_btn, close_btn;

    @FXML
    private TextArea description, bottom_text;
    @FXML
    private TextField nameField;

    @FXML
    private ImageView avatar, main_image;



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

        }
        else if (event.getSource() == back_btn) {
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
        else if(event.getSource() == next_character){
            currentCharacter++;
            handleCharacter();
            setCharButtons();
        }
        else if(event.getSource() == prev_character){
            currentCharacter--;
            handleCharacter();
            setCharButtons();
        }
    }


    @FXML
    void menu_on(ActionEvent event) {
        menu_pane.setOpacity(1);
        rootPane.setOpacity(0.45);
        System.out.println("menu is now on ");
        close_btn.setDisable(false);
        menu_pane.setMouseTransparent(false);



        currentCharacter = 0;
        handleCharacter();

        //todo IN A METHOD
        menu_btn.setDisable(true);
        menu_btn.setOpacity(0);
        bottom_text.setOpacity(0);
        next_btn.setDisable(true);
        back_btn.setDisable(true);
        next_btn.setOpacity(0);
        back_btn.setOpacity(0);
        avatar.setOpacity(0);



    }
    @FXML
    void menu_off(ActionEvent event) {
        rootPane.setOpacity(1);
        menu_pane.setOpacity(0);
        close_btn.setDisable(true);
        System.out.println("menu is off now");
        menu_pane.setMouseTransparent(true);

        //todo IN A METHOD
        menu_btn.setDisable(false);
        menu_btn.setOpacity(1);
        bottom_text.setOpacity(1);
        next_btn.setDisable(false);
        back_btn.setDisable(false);
        next_btn.setOpacity(1);
        back_btn.setOpacity(1);
        avatar.setOpacity(1);
    }

    void handleCharacter(){
        setCharButtons();
        if(characters.isEmpty()) return;
        if(currentCharacter >= characters.size()) return;

        CharacterObj ch = characters.get(currentCharacter);
        setCharacterImage(ch.getPhoto());
        setCharacterName(ch.getName());
        setCharacterText(ch.getDesc());

    }

    void setText(String text){
        bottom_text.setText(text);
    }
    void setAvatar(String imagePath){
        avatar.setImage(new Image(getClass().getResourceAsStream(imagePath)));
    }

    void setCharacterImage(String imagePath){
        character_image.setImage(new Image(getClass().getResourceAsStream(imagePath)));
    }
    void setCharacterName(String name){
        nameField.setText(name);
    }
    void setCharacterText(String t){
        description.setText(t);
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
    //todo terrible logic to be cleared
    void setCharButtons() {
        if (characters.isEmpty()) {
            // No characters available, disable both buttons
            next_character.setDisable(true);
            next_character.setOpacity(0);

            prev_character.setDisable(true);
            prev_character.setOpacity(0);

            return;
        }
//
//        // Disable 'next_character' if we are at the last character
//        next_character.setDisable(currentCharacter >= characters.size() - 1);
//
//        // Disable 'prev_character' if we are at the first character
//        prev_character.setDisable(currentCharacter <= 0);

        if(currentCharacter >= characters.size() - 1){
            next_character.setDisable(true);
            next_character.setOpacity(0);
        }
        else{
            next_character.setDisable(false);
            next_character.setOpacity(1);
        }

        if(currentCharacter <= 0){
            prev_character.setDisable(true);
            prev_character.setOpacity(0);
        }
        else{
            prev_character.setDisable(false);
            prev_character.setOpacity(1);
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
            if(!obj.getAvatar().equals("SAME")) setAvatar(obj.getAvatar());
            avatar.setOpacity(1);
        }
        else{
            avatar.setOpacity(0);
        }
        if(!obj.getBackground().equals("SAME")) {
            setMainImage(obj.getBackground());
        }

        //finding character name logic
//        if(JSONManager.newCharacter(obj.getText())){
//            System.out.println("adding character");
//            String t = obj.getText();
//            String[] m = t.split(" ");
//            System.out.println("character name = " + m[3]);
//            addCharacter(m[3]);
//        }
        if (JSONManager.newCharacter(obj.getText())) {
            System.out.println("adding character");
            String t = obj.getText();

            // Remove the fixed part of the string
            String name = t.replaceFirst("New character added\\. ", "")  // Remove beginning
                    .replaceFirst(" unlocked.", "")     // Remove ending
                    .trim();

            System.out.println("character name = " + name);
            addCharacter(name);
        }
    }
    private void addCharacter(String charName)  {
        if(included(charName)){
            System.out.println("character is already included");
            return;
        }

        CharacterObj ch = JSONManager.getCharacter(charName);
        characters.add(ch);
        System.out.println("character added. size = " + characters.size());
        setCharButtons();
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
    private boolean included(String name){
        for(CharacterObj ch : characters){
            if(ch.getName().equals(name)){
                return true;
            }
        }
        return false;
    }

}
