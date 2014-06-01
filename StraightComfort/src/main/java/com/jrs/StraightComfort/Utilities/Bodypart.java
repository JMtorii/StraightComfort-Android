package com.jrs.StraightComfort.Utilities;

/**
 * Created by Steve_2 on 2014-05-24.
 */
public class Bodypart {

    private String name = null;
    private boolean checked = false;

    public Bodypart (String name, boolean checked){
        super();
        this.name = name;
        this.checked = checked;
    }
    public Bodypart (String name)
    {
        super();
        this.name = name;
        this.checked = false;
    }
    public Bodypart(String ... parts)
    {
        for (String part  : parts)
           new Bodypart(part);

    }
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name  = name;
    }
    public boolean isChecked()
    {
        return checked;
    }
    public void setChecked(boolean checked)
    {
        this.checked = checked;
    }
}
