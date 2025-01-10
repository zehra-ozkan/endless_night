package com.dilazehra.endless_night;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;



public class App extends Application {
    public GameLogic game;

    @Override
    public void start(Stage stage) throws IOException {
        game = new GameLogic();
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/dilazehra/Scenes/enranceScene.fxml"));
        FXMLLoader fxmlLoader = FxmlLoader.load("enranceScene");
        Scene scene = new Scene(fxmlLoader.load());
        stage.setWidth(800);   // Optional: Set the stage size
        stage.setHeight(600);  // Optional: Set the stage size
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}