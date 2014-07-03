package com.jrs.StraightComfort.Utilities;

/**
 * Created by Steve_2 on 2014-05-24.
 */
public class Bodypart {

     String name = null;
     boolean selected = false;

    public Bodypart(String name, boolean selected) {
        super();
        this.name = name;
        this.selected = selected;
    }

    public Bodypart(String name) {
        super();
        this.name = name;
        this.selected = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
