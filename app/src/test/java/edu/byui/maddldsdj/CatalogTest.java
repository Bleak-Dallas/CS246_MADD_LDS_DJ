package edu.byui.maddldsdj;

import org.junit.Test;
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
    public void AddingASongToTheCatalogIncreasesItsSizeByOne() {
        Catalog c = new Catalog();
        int initialSize = c.size();

        c.add(new Song());
        assertEquals("Adding a Song to the catalog should increase its size by 1.",
                initialSize + 1, c.size());

    }

    @Test
    public void RemovingASongFromTheCatalogDecreasesItsSizeByOne() {
        Catalog c = new Catalog();
        Song s = new Song();
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

}
