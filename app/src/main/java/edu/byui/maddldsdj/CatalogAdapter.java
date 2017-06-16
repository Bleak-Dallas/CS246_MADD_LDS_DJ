package edu.byui.maddldsdj;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Matthew on 6/16/2017.
 */

public class CatalogAdapter extends ArrayAdapter<Song> {
    private final Context _context;
    private final List<Song> _songs;

    public CatalogAdapter(Context context, List<Song> songs) {
        super(context, R.layout.catalogrow, songs);
        this._context = context;
        this._songs = songs;
    }

    @Override
    public View getView(int position, View contentView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) _context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.catalogrow, parent, false);
        TextView title = (TextView) rowView.findViewById(R.id.title);
        TextView artist = (TextView) rowView.findViewById(R.id.artist);
        Song song = _songs.get(position);
        title.setText(song.getTitle());
        artist.setText(song.getArtist());

        return rowView;
    }
}
