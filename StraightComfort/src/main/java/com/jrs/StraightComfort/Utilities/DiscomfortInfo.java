package com.jrs.StraightComfort.Utilities;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Steve_2 on 2014-06-29.
 */
public class DiscomfortInfo implements Serializable {

    private Bodypart discomfort;
    private ArrayList<SolutionInfo> solutionInfos;

    public DiscomfortInfo(Bodypart discomfort, ArrayList<SolutionInfo> solutionInfos)
    {
        this.discomfort = discomfort;
        this.solutionInfos = solutionInfos;
    }
    public Bodypart getBodypart()
    {
        return discomfort;
    }

    public ArrayList<SolutionInfo> getSolutionInfos(){
        return solutionInfos;
    }





}

