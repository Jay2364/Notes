package com.example.notes.Model;

public class NoteList {
    private int id;
    private String title;
    private String desc;

    public NoteList(String title, String desc){
        this.title = title;
        this.desc = desc;
    }
    public NoteList(){

    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
