package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.notes.Model.NoteList;
import com.example.notes.data.MyDbHandler;

import java.util.ArrayList;
import java.util.List;

public class Description extends AppCompatActivity {

    EditText edit1;
    EditText edit2;
    Button button;

    public String title;
    public String description;

    MyDbHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        Intent intent = getIntent();
        db = new MyDbHandler(getApplicationContext());

        edit1 = findViewById(R.id.edit1);
        edit2 = findViewById(R.id.edit2);
    }

    void getEditText(){
        title = edit1.getText().toString();
        description = edit2.getText().toString();
    }

    public void Add(View view) {
        getEditText();

        NoteList noteList = new NoteList();
        noteList.setTitle(title);
        noteList.setDesc(description);

        db.addNote(noteList);

        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }
}