package com.example.felix_pc.librarychain;
import android.annotation.TargetApi;
import android.text.format.Time;

import java.util.ArrayList;

public class Transaction {

    private String BookID;
    private String date;
    private String BookOwner;
    private String BookName;
    private ArrayList bookOwner = new ArrayList();
    private int id;
    /**
     * Get current time in human-readable form.
     *
     * @return current time as a string.
     */
    @TargetApi(3)
    public static String getNow() {

        Time now = new Time();
        now.setToNow();
        String sTime = now.format("%Y_%m_%d %T");
        return sTime;
    }
    public Transaction(String BookOwner, String BookName, int id){
        date = getNow();
        this.BookOwner = BookOwner;
        this.id = id;
        this.BookName = BookName;
        bookOwner.add(BookOwner);
    }

    //array list
    public void setBookOwner(String Owner){
        BookOwner = Owner;
        bookOwner.add(Owner);
    }

    public ArrayList getOwnerList(){
        return bookOwner;
    }


    public String getBookID() {
        return BookID;
    }
    public String getOwner(){
        return BookOwner;
    }
    public String getDate(){
        return date;
    }
    public int getId(){
        return id;
    }
    public void addBookID(String id){
        BookID = id;
    }
    public String getBookName(){
        return BookName;
    }
}
