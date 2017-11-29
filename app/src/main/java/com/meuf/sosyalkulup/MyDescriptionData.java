package com.meuf.sosyalkulup;

/**
 * Created by faruk on 8.05.2017.
 */

public class MyDescriptionData {

    private int clubInfoId,clubId;
    private String Title, description, clubpics;

    public MyDescriptionData(int clubInfoId,String Title, String description,int clubId, String clubpics)
    {
        this.clubInfoId = clubInfoId;
        this.Title=Title;
        this.description=description;
        this.clubId=clubId;
        this.clubpics=clubpics;
    }
    public int getclubInfoId(){return clubInfoId;}

    public void setclubInfoId(int clubInfoId){this.clubInfoId=clubInfoId;}

    public String getTitle(){return Title;}

    public void setTitle(String Title){this.Title = Title;}

    public String getDescription(){return description;}

    public void setDescription(String description){this.description=description;}

    public int getClubId(){return clubId;}

    public void setClubId(int clubId){this.clubId=clubId;}

    public String getclubpics(){return clubpics;}

    public void setclubpics(String clubPics){this.clubpics=clubpics;}
}

