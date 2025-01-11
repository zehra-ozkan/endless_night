package com.dilazehra.endless_night.gameLogic;

public class SceneObject {
    String background;
    String text;
    String avatar;
    String nextScene;

    public String getBackground() {
        return background;
    }


    public String getText() {
        return text;
    }


    public String getAvatar() {
        return avatar;
    }


    public SceneObject(){
        background = null;
        text = null;
        avatar = null;
        nextScene = null;
    }
    public SceneObject(String text, String background, String avatar) {
        this.background = background;
        this.text = text;
        this.avatar = avatar;
    }


}
