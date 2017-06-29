package edu.byui.maddldsdj;

/**
 * An interface for listeners of events raised by the Catalog class
 * @author Matthew Burr
 * @since 6/21/2017.
 */

public interface CatalogEventListener {
    /**
     * Listens for the Catalog's data having been reloaded from its source
     */
    public void onCatalogReloaded();

    /**
     * Listens for a new song being added to the Catalog
     * @param song The song that was added to the Catalog
     */
    public void onCatalogSongAdded(Song song);
}
