package com.example.app;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity2 extends AppCompatActivity {

    private DatabaseReference DBConnection;
    private ListView listViewGamelist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        DBConnection = FirebaseDatabase.getInstance().getReference("Game");
        
        listViewGamelist = findViewById(R.id.listViewGameList);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity2.this, Gamelist.class));
            }
        });
        readData();


        listViewGamelist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String idGames = ((TextView)view.findViewById(R.id.textViewTitle)).getTag().toString();

                Intent detailgame = new Intent(MainActivity2.this, DetailsGame.class);

                detailgame.putExtra("id", idGames);

                startActivity(detailgame);
            }
        });
    }

    private void readData() {
        final ArrayList<String> id = new ArrayList<>();
        final ArrayList<String> title = new ArrayList<>();
        final ArrayList<String> genre = new ArrayList<>();
        final ArrayList<String> price = new ArrayList<>();
        final ArrayList<String> image = new ArrayList<>();

        DBConnection.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> snapshotIterable = dataSnapshot.getChildren();
                Iterator<DataSnapshot> iterator = snapshotIterable.iterator();

                while (iterator.hasNext()){
                    Games value = iterator.next().getValue(Games.class);


                assert  value != null;
                    id.add(value.getId());
                    title.add(value.getTitle());
                    genre.add(value.getGenre());
                    price.add(value.getPrice());
                    image.add(value.getImage());

                    ((listViewAdapter)listViewGamelist.getAdapter()).notifyDataSetChanged();
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        listViewGamelist.setAdapter(new listViewAdapter(id, title, genre, price, image, this));
    }

    @Override
    public void onResume(){
        super.onResume();
        readData();
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();




            finish();
            overridePendingTransition(0,0);
            startActivity(getIntent());
            overridePendingTransition(0,0);


            return true;
        }

    }