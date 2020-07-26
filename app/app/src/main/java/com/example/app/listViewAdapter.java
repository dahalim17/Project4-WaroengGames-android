package com.example.app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class listViewAdapter extends BaseAdapter {


    private ArrayList<String> id;
    private ArrayList<String> title;
    private ArrayList<String> genre;
    private  ArrayList<String> price;
    private  ArrayList<String> image;
    private AppCompatActivity activity;


    public listViewAdapter(ArrayList<String> id, ArrayList<String> title, ArrayList<String> genre, ArrayList<String> price, ArrayList<String> image, AppCompatActivity activity) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.price = price;
        this.image = image;
        this.activity = activity;
    }


    @Override
    public int getCount() {
        return id.size();
    }

    @Override
    public Object getItem(int position) {
        return id.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        convertView = LayoutInflater.from(activity.getApplicationContext())
                .inflate(R.layout.desing_layout_listview ,parent,false);

        convertView.findViewById(R.id.textViewTitle).setTag(String.valueOf(id.get(position)));
        ((TextView)convertView.findViewById(R.id.textViewTitle)).setText(String.valueOf(title.get(position)));

        ((TextView)convertView.findViewById(R.id.textViewGenre)).setText(String.valueOf(genre.get(position)));

        ((TextView)convertView.findViewById(R.id.textViewPrice)).setText(String.valueOf(price.get(position)));

        String imgUrl = image.get(position);

        if (imgUrl != ""){

            Picasso.get().load(imgUrl).into((ImageView)convertView.findViewById(R.id.imageVIewGamelist));
        }else {
            ((ImageView)convertView.findViewById(R.id.imageVIewGamelist)).setImageResource(R.mipmap.ic_launcher);

        }


        return convertView;
    }
}
