package com.info.eventoutfyp;

public class User {

    String id;
    int type;

    public User(){}

    public User(String id, int type)
    {
        this.id = id;
        this.type = type;
    }

    public String getId()
    {
        return id;
    }

    public int getUserType()
    {
        return type;
    }

}
