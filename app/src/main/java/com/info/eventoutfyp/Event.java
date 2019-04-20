package com.info.eventoutfyp;

import java.io.Serializable;
import java.util.ArrayList;


public class Event implements Serializable {

    private String eventTitle, eventDescription, eventID;
    private String eventDate;
    private boolean discountEnabled;
    private String discountDescription;
    private String venueID;
    private String eventCapacity;
    private ArrayList<String> categories;
    private String startTime;
    private String endTime;

    public Event(){}
    public Event(String eventTitle, String venueID, String eventDescription, boolean discountEnabled, String discountDescription, String eventCapacity, String eventDate, String startTime, String endTime ) {
        this.eventTitle = eventTitle;
        this.venueID = venueID;
        this.eventDescription = eventDescription;
        this.eventCapacity = eventCapacity;
        this.discountDescription = discountDescription;
        this.discountEnabled = discountEnabled;
        this.categories = new ArrayList<>();
        this.eventDate = eventDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }



    public String getEventID(){
        return eventID;
    }

    public void setEventID(String eventID){
        this.eventID = eventID;
    }


    public String getEventDescription(){
        return eventDescription;
    }
    public void setEventDescription(String description){
        this.eventDescription = description;
    }

    public String getEventDate(){
        return eventDate;
    }
    public void setEventDate(String date){
        this.eventDate = date;
    }

    public String getEventTitle(){
        return eventTitle;
    }
    public void setEventTitle(String title){
        this.eventTitle = title;
    }

    public boolean getdiscountEnabled(){
        return discountEnabled;
    }
    public void setDiscountEnabled(boolean enabled){
        this.discountEnabled = enabled;
    }

    public String getDiscountDescription(){
        return discountDescription;
    }
    public void setDiscountDescription(String description){
        this.discountDescription = description;
    }

    public String getVenueID(){
        return venueID;
    }
    public void setVenueID(String venueID){
        this.venueID = venueID;
    }

    public String getEventCapacity(){
        return eventCapacity;
    }
    public void setEventCapacity(String capacity){
        this.eventCapacity = capacity;
    }

    public ArrayList<String> getCategories(){
        return categories;
    }

    public void setCategory(ArrayList<String> categories){
        this.categories = categories;
    }

    public void addCategory(String category){
        this.categories.add(category);
    }

    public String getStartTime(){
        return startTime;
    }

    public void setStartTime(String startTime){
        this.startTime = startTime;
    }

    public String getEndTime(){
        return endTime;
    }

    public void setEndTime(String endTime){
        this.endTime = endTime;
    }






    public String toString() {
        return "Event{" +
                "ID='" + eventID + '\'' +
                ", title= '" + eventTitle + '\'' +
                ", description='" + eventDescription + '\'' +
                ", date='" + eventDate + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", eventCapacity='" + eventCapacity + '\'' +
                ", venueID='" + venueID + '\'' +
                ", discountBool='" + discountEnabled + '\'' +
                ", discountDesc='" + discountDescription + '\'' +
                ", categories='" + categories + '\'' +
                '}';
    }



}