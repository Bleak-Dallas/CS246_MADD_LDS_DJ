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
 * Handles the presentation in a list of songs in the Catalog
 * @author Matthew Burr
 * @since 6/16/2017.
 */

public class CatalogAdapter extends ArrayAdapter<Song> {
    private final Context _context;
    private final List<Song> _songs;

    static class ViewHolder {
        public TextView title;
        public TextView artist;
    }

    /**
     * Creates a new instance of the Adapter
     * @param context The view in which the list resides
     * @param songs A list of songs to present in the view
     */
    public CatalogAdapter(Context context, List<Song> songs) {
        super(context, R.layout.catalogrow, songs);
        this._context = context;
        this._songs = songs;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;

        if (rowView == null) {
            LayoutInflater inflater = (LayoutInflater) _context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            rowView = inflater.inflate(R.layout.catalogrow, parent, false);

            ViewHolder holder = new ViewHolder();
            holder.title = (TextView) rowView.findViewById(R.id.title);
            holder.artist = (TextView) rowView.findViewById(R.id.artist);

            rowView.setTag(holder);
        }

        ViewHolder holder = (ViewHolder) rowView.getTag();
        Song song = _songs.get(position);
        holder.title.setText(song.getTitle());
        holder.artist.setText(song.getArtist());

        return rowView;
    }

    @Override
    public Song getItem(int position) {
        return _songs.get(position);
    }
}
