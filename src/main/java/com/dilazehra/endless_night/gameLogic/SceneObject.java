package com.dilazehra.endless_night.gameLogic;

public class SceneObject {
    public String getBackground() {
        return background;
    }

    String background;

    public String getText() {
        return text;
    }

    String text;

    public String getAvatar() {
        return avatar;
    }

    String avatar;

    public SceneObject(){
        background = null;
        text = null;
        avatar = null;
    }
    public SceneObject(String text, String background, String avatar) {
        this.background = background;
        this.text = text;
        this.avatar = avatar;
    }


}
