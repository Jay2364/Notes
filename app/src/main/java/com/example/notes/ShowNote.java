package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.notes.Model.NoteList;
import com.example.notes.data.MyDbHandler;

import org.w3c.dom.Text;

public class ShowNote extends AppCompatActivity {

    EditText textView1;
    EditText textView2;

    String editTitle;
    String editDesc;

    MyDbHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_note);

        textView1 = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);

        textView1.setText(getIntent().getStringExtra("title"));
        textView2.setText(getIntent().getStringExtra("desc"));
        db = new MyDbHandler(this);

    }

    public void save(View view) {
        editTitle = textView1.getText().toString();
        editDesc = textView2.getText().toString();

        NoteList updateNote = new NoteList();
        updateNote.setTitle(editTitle);
        updateNote.setDesc(editDesc);

        int count1 = db.updateNoteTitle(updateNote);
        int count2 = db.updateNoteDesc(updateNote);

        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}