package com.example.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class Gamelist extends AppCompatActivity {

    private DatabaseReference DBConnection;
    private StorageReference STConnection;
    private String gameId, storageUrl;

    ImageView ImgGame;
    EditText TextTitle, TextGenre, TextPrice;
    Button saveBtn, Cancelbtn;
    ProgressBar progressBar;

    private Uri filepath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamelist);

        ImgGame = findViewById(R.id.imageView);
        TextTitle = findViewById(R.id.editTextNama);
        TextGenre = findViewById(R.id.editTextGenre);
        TextPrice = findViewById(R.id.editTextPrice);

        saveBtn = findViewById(R.id.buttonSave);
        Cancelbtn = findViewById(R.id.buttonCancel);

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        DBConnection = FirebaseDatabase.getInstance().getReference("Game");
        STConnection = FirebaseStorage.getInstance().getReference();
        gameId = DBConnection.push().getKey();


        ImgGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showGallery();
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage();
            }
        });

        Cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Text = TextTitle.getText().toString();
                TextTitle.setText("");
                String Text1 = TextGenre.getText().toString();
                TextGenre.setText("");
                String Text2 = TextPrice.getText().toString();
                TextPrice.setText("");
            }
        });

    }

    private void uploadImage() {
        if (filepath != null){
            String imageName = TextTitle.getText().toString();

            final StorageReference ref = STConnection.child("images/"+imageName);

            UploadTask uploadTask = ref.putFile(filepath);

            Task<Uri> uriTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }

                    //program akan melanjutkan untuk mendapatkan URL dari foto yang di upload
                    return ref.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()){
                        Uri downloadUrl = task.getResult();

                        storageUrl = downloadUrl.toString();

                        saveData();

                        Toast.makeText(getApplicationContext(), "Data Saved Successfully", Toast.LENGTH_SHORT).show();

                        progressBar.setProgress(0);
                        progressBar.setVisibility(View.INVISIBLE);

                        finish();
                    }
                }
            });

            uploadTask.addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                    progressBar.setVisibility(View.VISIBLE);
                    double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());

                    progressBar.setProgress((int)progress);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressBar.setVisibility(View.INVISIBLE);

                    Toast.makeText(getApplicationContext(),"Gagal Menyimpan Data!"+e.getMessage(),Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    private void saveData() {
        Games games = new Games(gameId,
                TextTitle.getText().toString(),
                TextGenre.getText().toString(),
                TextPrice.getText().toString(),
                storageUrl);

        DBConnection.child(gameId).setValue(games);

    }

    private void showGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Choose Image"), 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null){

            filepath = data.getData();

            Picasso.get().load(filepath).fit().into(ImgGame);

        }else {
            Toast.makeText(getApplicationContext(),"No Choosen File",Toast.LENGTH_SHORT).show();
        }

    }
}
