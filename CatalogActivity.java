package edu.byui.maddldsdj;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class CatalogActivity extends AppCompatActivity implements View.OnClickListener {
    private Button buttonUpVote;
    private Button buttonViewPlaylist;
    private Button buttonRequestApproval;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);
        buttonUpVote = (Button) findViewById(R.id.button_up_vote);
        buttonViewPlaylist = (Button) findViewById(R.id.button_view_playlist);
        buttonRequestApproval = (Button) findViewById(R.id.button_request_approval);
    }

    @Override
    public void onClick(View v) {
        if(v == buttonUpVote){
            Toast.makeText(CatalogActivity.this, "Voted for song", Toast.LENGTH_SHORT).show();
        }
        if (v == buttonViewPlaylist){
            Toast.makeText(CatalogActivity.this, "view Playlist Selected", Toast.LENGTH_SHORT).show();
        }
        if (v == buttonRequestApproval){
            Toast.makeText(CatalogActivity.this, "Request for song approval selected", Toast.LENGTH_SHORT).show();
        }

    }
}
