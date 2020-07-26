package com.example.app;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        WebView aboutone = (WebView) findViewById(R.id.aboutone);
        WebView abouttwo = (WebView) findViewById(R.id.abouttwo);
        WebView aboutthree = (WebView) findViewById(R.id.aboutthree);
        WebView aboutfour = (WebView) findViewById(R.id.aboutfour);

        String text = "Waroeng Games is a retail store for Playstation Games and was founded in 2015. Starting when we try our hobby to playing games for fun. So, we think we can share our joy to help other gamers to feel the atmosphere of the original games that we have.";
        String text1 = "We will be happy to welcome you if you want to visit our store. Please check our e-catalog on this website to see the games available in our store. do not hesitate to contact us via the platform provided if you want to order a game, request a game, or ask about us";
        String text2 = "We lived based on your trust, and it's always our responsibilities to make sure that you get the best ever from us.";
        String text3 = "GamingIsMoreFunWithWaroengGames!";

        aboutone.loadData("<p style=\"text-align: justify\">"+ text+"</p","text/html", "UTF-8");
        abouttwo.loadData("<p style=\"text-align: justify\">"+ text1+"</p","text/html", "UTF-8");
        aboutthree.loadData("<p style=\"text-align: justify\">"+ text2+"</p","text/html", "UTF-8");
        aboutfour.loadData("<p style=\"text-align: justify\">"+ text3+"</p","text/html", "UTF-8");
    }
}
