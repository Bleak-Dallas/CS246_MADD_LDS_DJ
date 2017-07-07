package edu.byui.maddldsdj;

import android.os.Process;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Tests for the Catalog class
 * @author Matthew Burr
 * @since 2017-06-10
 * @version 1.0
 */

@RunWith(MockitoJUnitRunner.class)
public class CatalogTest {
    /*

    @Mock
    static FirebaseDatabase _server;
    */
    @Mock
    static DatabaseReference _db;

    static List<Song> _mockDb;

    Catalog c;

    @BeforeClass
    public static void SetUp() {
        _mockDb = new ArrayList<>();
    }

    @Before
    public void BeforeTest() {
        c = new Catalog(_db);
        //c = new Catalog();
        when(_db.push()).thenReturn(_db);
        when(_db.setValue(any())).thenReturn(null);
    }

    @Test
    public void DefaultConstructorCreatesEmptyCatalog() {
        assertEquals("Default constructor for Catalog should create an empty Catalog.",
                0, c.size());
    }

    @Test
    public void AddingASongToTheCatalogIncreasesItsSizeByOne() throws Exception {
        int initialSize = c.size();

        Song s = new Song("", "", "", "", true, true);
        c.add(s);
        assertEquals("Adding a Song to the catalog should increase its size by 1.",
                initialSize + 1, c.size());

    }

    @Test
    public void AddingASongThatAlreadyExistsDoesNotReaddTheSongToTheCatalog()
    {
        Song alreadyThere = new Song("Test", "Test", "", "");
        c.add(alreadyThere);
        int expected = c.size();
        Song duplicate = new Song("Test", "Test", "", "");
        c.add(duplicate);
        assertEquals("If you add a Song that is already there, it should not be added again.",
                expected, c.size());
    }

    @Test
    public void FindReturnsNullIfSongNotFound() {
        Song sought = new Song("Test", "Test", "", "");
        assertNull("Find should return null if a song isn't found",
                c.find(sought));
    }

    @Test
    public void FindReturnsAMatchingSongFromTheCatalog()
    {
        Song expected = new Song("Test", "Test", "", "");
        c.add(expected);
        Song sought = new Song("Test", "Test", "", "");
        assertSame("The sought song should return a matching song from the catalog",
                expected, c.find(sought));
    }

    @Test
    public void RemovingASongFromTheCatalogDecreasesItsSizeByOne() throws Exception {
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
        c.remove(new Song());
        assertEquals("If the Catalog is empty, removing a song should leave the size at 0.",
                0, c.size());
    }

    // TODO: If you remove a song from the catalog, it's no longer there
    // TODO: You can get the list of songs??

}
