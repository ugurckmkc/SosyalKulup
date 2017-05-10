package com.meuf.sosyalkulup;

/**
 * Created by faruk on 8.05.2017.
 */

public class MyDescriptionData {

    private int clubId;
    private String title, description, clubPics;

    public MyDescriptionData(int clubId,String title, String description, String clubPics)
    {
        this.clubId=clubId;
        this.description=description;
        this.title=title;
        this.clubPics=clubPics;
    }
    public int getClubId(){return clubId;}
    public void setClubId(int clubId){this.clubId=clubId;}
    public String getTitle(){return title;}
    public void setTitle(String title){this.title = title;}
    public String getDescription(){return description;}
    public void setDescription(String description){this.description=description;}
    public String getClubPics(){return clubPics;}
    public void setClubPics(String clubPics){this.clubPics=clubPics;}
}
