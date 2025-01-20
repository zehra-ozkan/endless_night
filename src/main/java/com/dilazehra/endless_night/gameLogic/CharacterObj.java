package com.dilazehra.endless_night.gameLogic;

public class CharacterObj {
    private String name;

    public String getName() {
        return name;
    }

    public String getPhoto() {
        return photo;
    }

    private String photo;

    public String getDesc() {
        return desc;
    }

    private String desc;

    public CharacterObj(String name, String photo, String desc) {
        this.name = name;
        this.photo = photo;
        this.desc = desc;
    }


}
