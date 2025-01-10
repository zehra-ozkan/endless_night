package com.dilazehra.endless_night.gameLogic;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
import org.json.JSONArray;



public class JSONManager {

    private static JSONObject jsonData = null;
    private static final String DATAPATH = "C:\\java projects\\endless night\\src\\main\\resources\\com\\dilazehra\\sceneInfo.json";
    // Loads JSON data from a file (only once)
    public static JSONObject loadJsonData()  {
        if (jsonData == null) { // Only load if not already loaded

            try {
                FileReader fileReader = new FileReader(DATAPATH);
                StringBuilder stringBuilder = new StringBuilder();
                int i;
                while ((i = fileReader.read()) != -1) {
                    stringBuilder.append((char) i);
                }
                fileReader.close();
                jsonData = new JSONObject(stringBuilder.toString());
            } catch (IOException e) {
                System.out.println("rah rah rah could not find path");
                System.out.println(e);
            }
        }
        return jsonData;
    }

    public JSONManager() {
        jsonData = loadJsonData();
    }

    public static List<SceneObject> getSceneArray(String sceneName) throws IOException {
        if(jsonData == null) {
            jsonData = loadJsonData();
        }
        JSONArray sceneArray = jsonData.getJSONArray(sceneName);
        List<SceneObject> sceneSteps = new ArrayList<>();

        for (int i = 0; i < sceneArray.length(); i++) {
            JSONObject step = sceneArray.getJSONObject(i);
            String text = step.getString("text");
            String image = step.getString("image");
            String avatar = step.getString("avatar");

            sceneSteps.add(new SceneObject(text, image, avatar));
        }
        return sceneSteps;
    }

}
