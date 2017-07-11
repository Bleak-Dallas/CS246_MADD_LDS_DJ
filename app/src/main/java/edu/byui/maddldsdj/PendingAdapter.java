package edu.byui.maddldsdj;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by damon on 7/10/17.
 */

public class PendingAdapter extends ArrayAdapter<Song> {
    private final Context _context;

    static class ViewHolder {
        public TextView title;
        public TextView artist;
    }

    /**
     * Creates a new instance of the Adapter with a given context
     * @param context The context for the Adapter
     */
    public PendingAdapter(Context context) {
        super(context, R.layout.catalogrow);
        this._context = context;
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
        Song song = this.getItem(position);

        holder.title.setText(song.getTitle());
        holder.artist.setText(song.getArtist());

        return rowView;
    }
}

