package com.jrs.StraightComfort.Utilities;

import java.util.ArrayList;

/**
 * Created by Steve_2 on 2014-06-08.
 */

public class Content {
    private String title;
    private String icon;
    private ArrayList<Page> pages;


    public Content(String title,String icon, ArrayList<Page> pages) {
        this.title = title;
        this.icon = icon;
        this.pages = pages;

    }

    public Content(String title) {
        this.title = title;
        this.pages = null;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon(){return icon; }

    public ArrayList<Page> getPages() {
        return pages;
    }

    public void setPages(ArrayList<Page> pages) {
        this.pages = pages;
    }

    public void addPage(Page page) {
        ArrayList<Page> temp = pages;
        temp.add(page);
        this.setPages(temp);
    }





}
