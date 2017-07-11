package edu.byui.maddldsdj;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

import java.util.List;

public class PendingApproval extends AppCompatActivity implements View.OnClickListener {
    private final static String TAG = "PendingApproval";
    private Button buttonReturnToCatalog;
    private Context _context;
    private Catalog _catalog;
    private DatabaseReference _pending = FirebaseDatabase.getInstance().getReference("PendingRequests");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_approval);
        buttonReturnToCatalog = (Button) findViewById(R.id.button_Return_To_Catalog);
        buttonReturnToCatalog.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v == buttonReturnToCatalog) {
            // Create intent to display song details
            Intent dispPlayList = new Intent(this, CatalogActivity.class);

            // Launch the intent
            startActivity(dispPlayList);
        }
    }
}
