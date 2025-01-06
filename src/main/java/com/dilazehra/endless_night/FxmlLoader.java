package com.dilazehra.endless_night;

import javafx.fxml.FXMLLoader;

public class FxmlLoader {
    public static FXMLLoader load(String fileName) {
        return new FXMLLoader(FxmlLoader.class.getResource("/com/dilazehra/Scenes/" + fileName + ".fxml"));
    }

}
