package edu.byui.maddldsdj;

/**
 * Created by Matthew on 6/21/2017.
 */

public interface CatalogEventListener {
    public void onCatalogReloaded();
    public void onCatalogSongAdded(Song song);
}
