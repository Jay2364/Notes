package com.example.notes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<adapterModel> {

    public CustomAdapter(@NonNull Context context, ArrayList<adapterModel> arrayList) {
        super(context, 0, arrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_note,parent,false);

        adapterModel adaptermodel = getItem(position);

        TextView textView1 = (TextView) view.findViewById(R.id.show_title);
        textView1.setText(adaptermodel.getNoteTitle());

        TextView textView2 = (TextView) view.findViewById(R.id.show_desc);
        textView2.setText(adaptermodel.getNoteDescription());

        return view;

    }
}
