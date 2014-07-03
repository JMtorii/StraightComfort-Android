package com.jrs.StraightComfort.Utilities;

import java.util.ArrayList;

/**
 * Created by Steve_2 on 2014-07-02.
 */
public class SolutionInfo{
    String furniture;
    ArrayList<Integer> pages;

    public SolutionInfo(String furniture, ArrayList<Integer> pages)
    {
        this.furniture = furniture;
        this.pages = pages;
    }
    public String getFurniture()
    {
        return furniture;
    }



}