package com.example.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class DetailsGame extends AppCompatActivity {

    private DatabaseReference DBConnection;
    private StorageReference STConnection;

    ImageView ImgGame;
    EditText TextTitle, TextGenre, TextPrice;
    Button updateBtn, deletebtn;
    ProgressBar progressBar;

    private String imgUrl, gamesId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_game);

        ImgGame = findViewById(R.id.imageView);
        TextTitle = findViewById(R.id.editTextNama);
        TextGenre = findViewById(R.id.editTextGenre);
        TextPrice = findViewById(R.id.editTextPrice);

        updateBtn = findViewById(R.id.buttonUpdate);
        deletebtn = findViewById(R.id.buttonDelete);

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        DBConnection = FirebaseDatabase.getInstance().getReference("Game");

        gamesId  = getIntent().getExtras().getString("id");

        readData();

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateData();
                Toast.makeText(getApplicationContext(), "Update Successfully", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteData();
                deleteImage();
                Toast.makeText(getApplicationContext(), "Delete Successfully", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }

    private void deleteImage() {
        STConnection = FirebaseStorage.getInstance().getReferenceFromUrl(imgUrl);

        STConnection.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

            }
        });
    }

    private void deleteData() {
        Query delQuery = DBConnection.orderByKey().equalTo(gamesId);

        delQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DBConnection.child(gamesId).removeValue();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void updateData() {
        Query updateQuery = DBConnection.orderByKey().equalTo(gamesId);

        updateQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot addSnapshot: dataSnapshot.getChildren()){
                    addSnapshot.getRef().child("title").setValue(TextTitle.getText().toString());
                    addSnapshot.getRef().child("genre").setValue(TextGenre.getText().toString());
                    addSnapshot.getRef().child("price").setValue(TextPrice.getText().toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void readData() {
        progressBar.setVisibility(View.VISIBLE);
        Query findQuery = DBConnection.orderByKey().equalTo(gamesId);

        findQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot getSnapshot : dataSnapshot.getChildren()){

                    TextTitle.setText(getSnapshot.child("title").getValue().toString());
                    TextGenre.setText(getSnapshot.child("genre").getValue().toString());
                    TextPrice.setText(getSnapshot.child("price").getValue().toString());

                    imgUrl = getSnapshot.child("image").getValue().toString();

                    Picasso.get().load(imgUrl).fit().into(ImgGame);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
        progressBar.setVisibility(View.INVISIBLE);
    }
}
