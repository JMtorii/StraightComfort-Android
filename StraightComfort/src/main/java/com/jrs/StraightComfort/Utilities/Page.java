package com.jrs.StraightComfort.Utilities;

/**
 * Created by Steve_2 on 2014-06-28.
 */
public class Page {
    private String imageName;
    private String content;
    int pageNum;

    public Page(String imageName, String content, int pageNum) {
        this.imageName = imageName;
        this.content = content;
        this.pageNum = pageNum;
    }

    public String getImageName() {
        return imageName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public Integer getPageNum(){return pageNum;}

}