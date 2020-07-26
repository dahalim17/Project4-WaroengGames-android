package com.example.app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

public class Home extends AppCompatActivity implements View.OnClickListener{
    ImageView logout;
    CardView about;
    CardView contact;
    CardView gamelist;
    CardView comingsoon;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        logout = findViewById(R.id.btn_logout);
        about = findViewById(R.id.card_about);
        contact = findViewById(R.id.card_contact);
        gamelist = findViewById(R.id.card_gamelist);
        comingsoon = findViewById(R.id.card_comingsoon);

        mAuth = FirebaseAuth.getInstance();

        logout.setOnClickListener(this);
        about.setOnClickListener(this);
        contact.setOnClickListener(this);
        gamelist.setOnClickListener(this);
        comingsoon.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_logout:


                //Fungsi Logout
                mAuth.signOut();

                Intent keluar = new Intent(this, MainActivity.class);
                keluar.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(keluar);
                break;

            case R.id.card_about:
                Intent about = new Intent(this, About.class);
                startActivity(about);
                break;

            case R.id.card_contact:
                Intent contact = new Intent(this, Contact.class);
                startActivity(contact);
                break;

            case R.id.card_gamelist:
                Intent gamelist = new Intent(this, MainActivity2.class);
                startActivity(gamelist);
                break;

            case R.id.card_comingsoon:
                Intent comingsoon = new Intent(this, Main3Activity.class);
                startActivity(comingsoon);
                break;
        }
    }
}