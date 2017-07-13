package edu.byui.maddldsdj;

import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

/**
 * Fragment to display the list of pending song submissions awaiting approval
 * @author Matthew Burr
 * @author Damon Simpkinson
 * @since 7/10/2017
 */

public class SubmissionListFragment extends ListFragment {
    private static final String TAG = "SubmissionListFrag";
    private Context _context;
    private Catalog _catalog;
    private CatalogAdapter _adapter;
    private DatabaseReference _pending = FirebaseDatabase.getInstance().getReference("PendingRequests");


    /**
     * Initializes the SubmissionList and its view
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        _context = getActivity();
        _catalog = new Catalog(_pending);
        _adapter = new CatalogAdapter(_context);
        setListAdapter(_adapter);

        _catalog.addCatalogListener(new CatalogEventListener() {
            @Override
            public void onCatalogReloaded() {
                _adapter.clear();
                _adapter.addAll(_catalog.getSongs());
            }

            @Override
            public void onCatalogSongAdded(Song song) {

            }
        });
    }


    @Override
    public void onStart() {
        Log.d(TAG, "onStart starts.");
        super.onStart();
        _catalog.load();
        Log.d(TAG, "onStart ends.");
    }


    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
        Song item = (Song) getListAdapter().getItem(position);

        // Serialize the song to Json
        Gson gson = new Gson();
        String jsonSong = gson.toJson(item);

        // Create intent to display song details
        Intent pendingSongDetail = new Intent(_context, PendingSongDetailActivity.class);

        // Add song to intent
        pendingSongDetail.putExtra(PendingSongDetailActivity.SONG_EXTRA, jsonSong);

        // Launch the intent
        startActivity(pendingSongDetail);
    }
}
