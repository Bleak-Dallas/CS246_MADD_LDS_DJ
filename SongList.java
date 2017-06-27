package edu.byui.maddldsdj;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

/**
 * Created by damon on 6/26/17.
 */

public class SongList extends ArrayAdapter<Song> {
    private Activity context;
    private List<Song> songList;
    DatabaseReference databaseSongs;

    public SongList(Activity context, List<Song> songList){
        super(context, R.layout.catalog_list_layout, songList);
        databaseSongs = FirebaseDatabase.getInstance().getReference("catalog");
        this.context = context;
        this.songList = songList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.catalog_list_layout, null, true);

        TextView textViewTitle = (TextView) listViewItem.findViewById(R.id.textViewSongTitle);
        TextView textViewArtist = (TextView) listViewItem.findViewById(R.id.textViewSongArtist);

        Song song = songList.get(position);

        textViewTitle.setText(song.getTitle());
        textViewArtist.setText(song.getArtist());

        return listViewItem;
    }
}
