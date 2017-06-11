package edu.byui.maddldsdj;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Tests for the Catalog class
 * @author Matthew Burr
 * @since 2017-06-10
 * @version 1.0
 */

public class CatalogTest {
    @Test
    public void DefaultConstructorCreatesEmptyCatalog() {
        Catalog c = new Catalog();
        assertEquals("Default constructor for Catalog should create an empty Catalog.",
                0, c.size());
    }

    @Test
    public void AddingASongToTheCatalogIncreasesItsSizeByOne() throws Exception {
        Catalog c = new Catalog();
        int initialSize = c.size();

        c.add(new Song("", "", "", "", true, true));
        assertEquals("Adding a Song to the catalog should increase its size by 1.",
                initialSize + 1, c.size());

    }

    @Test
    public void RemovingASongFromTheCatalogDecreasesItsSizeByOne() throws Exception {
        Catalog c = new Catalog();
        Song s = new Song();
        s.setApproved(true);
        c.add(s);
        int initialSize = c.size();
        c.remove(s);
        assertEquals("Removing a Song from the catalog should decrease its size by 1.",
                initialSize - 1, c.size());
    }

    @Test
    public void RemovingASongFromAnEmptyCatalogLeavesSizeAtZero() {
        Catalog c= new Catalog();
        c.remove(new Song());
        assertEquals("If the Catalog is empty, removing a song should leave the size at 0.",
                0, c.size());
    }

    @Test(expected = Exception.class)
    public void YouCannotAddANonApprovedSongToTheCatalog() throws Exception {
        Song s = new Song();
        s.setApproved(false);
        Catalog c = new Catalog();
        c.add(s);
    }

    // TODO: If you remove a song from the catalog, it's no longer there
    // TODO: You can get the list of songs??

}
