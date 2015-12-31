package com.games.slavar.flagquiz;

/**
 * Created by Slavar on 12/29/2015.
 */
public class Flag {

    private String name;
    private String fileName;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Flag() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String pictureUrl) {
        this.fileName = pictureUrl;
    }
}
