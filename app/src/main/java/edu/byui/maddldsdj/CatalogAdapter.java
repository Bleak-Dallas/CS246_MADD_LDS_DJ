package edu.byui.maddldsdj;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
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

public class CatalogAdapter extends ArrayAdapter<String> {
    private final Context _context;
    private final List<String> _songs;

    static class ViewHolder{
        public TextView song;
    }

    public CatalogAdapter(Context context, List<String> songs){
        super(context, R.layout.activity_catalog, songs);
        this._context = context;
        this._songs = songs;
    }
}
    /*
    private final Context _context;
    private final List<Song> _songs;

    static class ViewHolder {
        public TextView title;
        public TextView artist;
    }

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
}*/
