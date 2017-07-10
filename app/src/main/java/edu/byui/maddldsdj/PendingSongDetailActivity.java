package edu.byui.maddldsdj;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class PendingSongDetailActivity extends AppCompatActivity {
    public static final String TAG = "PendingSongDetail";
    public static final String SONG_EXTRA = "song_extra";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_song_detail);
    }
}
