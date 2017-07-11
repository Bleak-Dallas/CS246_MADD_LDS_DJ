package edu.byui.maddldsdj;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * The PlayListActivity class
 * <p>
 * @author Dallas Bleak
 * @version 1.0
 * @since 2017-06-22
 */
public class PlayListActivity extends AppCompatActivity implements View.OnClickListener {
    private final static String TAG = "PlayListAct";
    private Button buttonViewCatalog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_list);
        buttonViewCatalog = (Button) findViewById(R.id.button_view_catalog);
        buttonViewCatalog.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == buttonViewCatalog) {
            Log.v(TAG, "buttonViewCatalog called");
            Intent intent = new Intent(this, CatalogActivity.class);
            startActivity(intent);
        }
    }
}
