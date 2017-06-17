package edu.byui.maddldsdj;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class CatalogActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Catalog c = new Catalog();
        c.load();
        ArrayAdapter<Song> adapter = new CatalogAdapter(this, c.getSongs());
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView listView, View view, int position, long id) {
        Song item = (Song) getListAdapter().getItem(position);
        Toast.makeText(this, item.toString() + " selected", Toast.LENGTH_LONG).show();
    }
}
