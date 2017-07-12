package edu.byui.maddldsdj;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Handles the presentation in a list of songs in the Playlist.
 * See also {@link=CatalogAdapter}
 * @author Dallas Bleak, Adam Shumway
 * @since 6/20/2017.
 */

public class PlayListAdapter extends ArrayAdapter{
    private final static String TAG = "PlayListAdapter";
    private final Context _context;
    private final List<Song> _songs;

    static class ViewHolder {
        public TextView title;
        public TextView artist;
        public TextView votes;
    }

    /**
     * Creates a new instance of the Adapter with a given context and a
     * List of Songs
     * @param context The context for the Adapter
     * @param songs The List of Songs
     */
    public PlayListAdapter(Context context, List<Song> songs) {
        super(context, R.layout.playlistrow, songs);
        this._context = context;
        this._songs = songs;
    }

    /**
     * Assigns data from the Song class to a row in the ListView.  Performs
     *  an initial set of sorts for artist and voteCount
     * @param position
     * @param convertView
     * @param parent
     * @return
     */

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        Log.v(TAG, "View rowView = " + rowView);

        if (rowView == null) {
            // use an Inflator to set the view with song, artist, and votes
            LayoutInflater inflater = (LayoutInflater) _context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            rowView = inflater.inflate(R.layout.playlistrow, parent, false);

            ViewHolder holder = new ViewHolder();
            holder.title = (TextView) rowView.findViewById(R.id.title);
            holder.artist = (TextView) rowView.findViewById(R.id.artist);
            holder.votes = (TextView) rowView.findViewById(R.id.votes);

            rowView.setTag(holder);
        }

        //sort the given list by artist asc
        Collections.sort(_songs, new Comparator<Song>() {
            @Override
            public int compare(Song o1, Song o2) {
                return o1.getArtist().compareToIgnoreCase(o2.getArtist());
            }
        });

        //sort the given list by vote count desc
        Collections.sort(_songs, new Comparator<Song>() {
            @Override
            public int compare(Song o1, Song o2) {
                return Integer.valueOf(o2.getVoteCount()).compareTo(o1.getVoteCount());
            }
        });

        ViewHolder holder = (ViewHolder) rowView.getTag();
        Song song = _songs.get(position);
        holder.title.setText(song.getTitle());
        holder.artist.setText(song.getArtist());
        holder.votes.setText(String.valueOf(song.getVoteCount()));

        return rowView;
    }

    @Override
    public Song getItem(int position) {
        return _songs.get(position);
    }
}