package com.meuf.sosyalkulup;

/**
 * Created by UgurCkmkc on 02/12/2017.
 */

public class MyNotificationData {

    private int id;
    private String eventName,eventDescription;

    public MyNotificationData(int id, String eventName, String eventDescription) {
        this.id = id;
        this.eventName = eventName;
        this.eventDescription = eventDescription;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }
}
