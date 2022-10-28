package com.example.notes;

import static android.app.ProgressDialog.show;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;

import com.example.notes.Model.NoteList;
import com.example.notes.data.MyDbHandler;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ImageView plus;
    ListView listView;
    MyDbHandler db;

    ArrayList<adapterModel> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        plus = findViewById(R.id.imageView);

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Description.class);
                startActivity(intent);
            }
        });

        db = new MyDbHandler(getApplicationContext());

        /* ------------------------------ Click Listener -------------------------------*/
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(),ShowNote.class);
                intent.putExtra("title",arrayList.get(i).getNoteTitle());
                intent.putExtra("desc",arrayList.get(i).getNoteDescription());
                startActivity(intent);
            }
        });
        /* ------------------------------ Click Listener -------------------------------*/



        /* ------------------------------ Long Click Listener N -------------------------------*/

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                // Create Pop-Up menu
                PopupMenu menu = new PopupMenu(getApplicationContext(),view);
                menu.getMenu().add("DELETE");
                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {

                        // Delete your item
                        if (menuItem.getTitle() == "DELETE") {
                            String to_delete = arrayList.get(i).getNoteTitle();
                            db.deleteNoteByTitle(to_delete);
                        }
                        show();
                        return true;
                    }
                });
                menu.show();
                return true;
            }
        });

        /* ------------------------------ Long Click Listener -------------------------------*/

        show();

    }

    public void show(){
        arrayList = new ArrayList<>();
        List<NoteList> noteList = db.getAllNotes();

        for (NoteList notes : noteList){
            arrayList.add(new adapterModel(notes.getTitle(),notes.getDesc()));
        }

        CustomAdapter customAdapter = new CustomAdapter(this,arrayList);
        listView.setAdapter(customAdapter);
    }

}