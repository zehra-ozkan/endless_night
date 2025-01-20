package com.dilazehra.endless_night.gameLogic;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
import org.json.JSONArray;



public class JSONManager {

    private static final String SCENEINFOPATH = "src\\main\\resources\\com\\dilazehra\\sceneInfo.json";
    private static final String NEXTSCENEPATH = "src\\main\\resources\\com\\dilazehra\\nextScenes.json";
    private static final String CHARACTERPATH = "src\\main\\resources\\com\\dilazehra\\characters.json";

    private static final String NEWCHARKEY = "New character added.";

    private static JSONObject nextScenes = null;
    private static JSONObject jsonData = null;
    private static JSONObject characterData = null;

    public static void loadJsonData()  {
        if (jsonData == null) { // Only load if not already loaded
            try {
                FileReader fileReader = new FileReader(SCENEINFOPATH);
                StringBuilder stringBuilder = new StringBuilder();
                int i;
                while ((i = fileReader.read()) != -1) {
                    stringBuilder.append((char) i);
                }
                fileReader.close();
                jsonData = new JSONObject(stringBuilder.toString());
            } catch (IOException e) {
                System.out.println("rah rah rah could not find path for scene information");
                System.out.println(e);
            }
        }
        //todo repetition, maybe in a method?
        if (nextScenes == null) { // Only load if not already loaded
            try {
                FileReader fileReader = new FileReader(NEXTSCENEPATH);
                StringBuilder stringBuilder = new StringBuilder();
                int i;
                while ((i = fileReader.read()) != -1) {
                    stringBuilder.append((char) i);
                }
                fileReader.close();
                nextScenes = new JSONObject(stringBuilder.toString());
            } catch (IOException e) {
                System.out.println("rah rah rah could not find path for next");
                System.out.println(e);
            }
        }
        if (characterData == null) { // Only load if not already loaded
            try {
                FileReader fileReader = new FileReader(CHARACTERPATH);
                StringBuilder stringBuilder = new StringBuilder();
                int i;
                while ((i = fileReader.read()) != -1) {
                    stringBuilder.append((char) i);
                }
                fileReader.close();
                characterData = new JSONObject(stringBuilder.toString());
            } catch (IOException e) {
                System.out.println("rah rah rah could not find path for next");
                System.out.println(e);
            }
        }
    }

    public JSONManager() {
        loadJsonData();
    }

    public static List<SceneObject> getSceneArray(String sceneName) throws IOException {
        if(jsonData == null) loadJsonData();

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
    public static CharacterObj getCharacter(String characterName) throws IOException {
        if(characterData == null) loadJsonData();
        JSONArray ch = jsonData.getJSONArray(characterName);
        String name = ch.getString(1);
        String image = ch.getString(0);
        String text = ch.getString(2);

        return new CharacterObj(name, image, text);
    }
    public static String getNextScene(String sName){
        if(nextScenes == null) loadJsonData();
        return nextScenes.getString(sName);
    }
    public static boolean newCharacter(String text){
        return text.contains(NEWCHARKEY);
    }

}
