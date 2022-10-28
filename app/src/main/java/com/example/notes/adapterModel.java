package com.example.notes;

public class adapterModel {
    private String title;
    private String description;

    public adapterModel(String title, String description){
        this.title = title;
        this.description = description;
    }

    public String getNoteTitle() {
        return title;
    }

    public String getNoteDescription() {
        return description;
    }

    public void setNoteTitle(String title) {
        this.title = title;
    }

    public void setNoteDescription(String description) {
        this.description = description;
    }
}
