package edu.byui.maddldsdj;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * The PlayListAdapter class 
 * <p>
 * @author Dallas Bleak
 * @version 1.0
 * @since 2017-06-22
 */
public class PlayListAdapter extends ArrayAdapter{
    private final Context _context;
    private final List<Song> _songs;

    static class ViewHolder {
        public TextView title;
        public TextView artist;
        public TextView votes;
    }

    public PlayListAdapter(Context context, List<Song> songs) {
        super(context, R.layout.playlistrow, songs);
        this._context = context;
        this._songs = songs;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;

        if (rowView == null) {
            LayoutInflater inflater = (LayoutInflater) _context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            rowView = inflater.inflate(R.layout.playlistrow, parent, false);

            ViewHolder holder = new ViewHolder();
            holder.title = (TextView) rowView.findViewById(R.id.title);
            holder.artist = (TextView) rowView.findViewById(R.id.artist);
            holder.votes = (TextView) rowView.findViewById(R.id.votes);

            rowView.setTag(holder);
        }

        ViewHolder holder = (ViewHolder) rowView.getTag();
        Song song = _songs.get(position);
        holder.title.setText(song.getTitle());
        holder.artist.setText(song.getArtist());
        holder.votes.setText("2");  // change this to DB when ready

        return rowView;
    }

    @Override
    public Song getItem(int position) {
        return _songs.get(position);
    }
}