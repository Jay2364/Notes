package com.example.notes.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.notes.Model.NoteList;
import com.example.notes.Params.Params;

import java.util.ArrayList;
import java.util.List;

public class MyDbHandler extends SQLiteOpenHelper {

    public MyDbHandler(Context context) {
        super(context, Params.DB_NAME, null, Params.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String create = "CREATE TABLE " + Params.TABLE_NAME + " (" + Params.KEY_ID +
                " INTEGER PRIMARY KEY, " + Params.KEY_TITLE + " TEXT," + Params.KEY_DESC + " TEXT" + ")";
        sqLiteDatabase.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void addNote(NoteList noteList) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Params.KEY_TITLE,noteList.getTitle());
        values.put(Params.KEY_DESC,noteList.getDesc());
        db.insert(Params.TABLE_NAME,null,values);
    }

    public List<NoteList> getAllNotes(){
        List<NoteList> noteLists = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String select = "SELECT * FROM " + Params.TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(select,null);
        if (cursor.moveToFirst()){
            do {NoteList notes = new NoteList();
                notes.setId(Integer.parseInt(cursor.getString(0)));
                notes.setTitle(cursor.getString(1));
                notes.setDesc(cursor.getString(2));
                noteLists.add(notes);
            } while(cursor.moveToNext());
        }
        return noteLists;
    }

    public void deleteNoteByTitle(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Params.TABLE_NAME,Params.KEY_TITLE + "=?", new String[]{String.valueOf(name)});
        db.close();
    }

    public int updateNoteTitle(NoteList noteList){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Params.KEY_TITLE,noteList.getTitle());
        values.put(Params.KEY_DESC,noteList.getDesc());

        return database.update(Params.TABLE_NAME,values,Params.KEY_TITLE + "=?", new String[]{String.valueOf(noteList.getTitle())});
    }

    public int updateNoteDesc(NoteList noteList){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Params.KEY_TITLE,noteList.getTitle());
        values.put(Params.KEY_DESC,noteList.getDesc());

        return database.update(Params.TABLE_NAME,values,Params.KEY_DESC + "=?", new String[]{String.valueOf(noteList.getDesc())});
    }

}
