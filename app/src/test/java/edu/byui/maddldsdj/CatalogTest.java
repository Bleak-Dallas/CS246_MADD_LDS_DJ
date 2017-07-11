package edu.byui.maddldsdj;

import android.os.Process;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        when(_db.getKey()).thenReturn("TestKey");
        when(_db.child(anyString())).thenReturn(_db);
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
    public void AddingASongThatIsntInTheCatalogButHasAKeyAddsTheSongUnderThatKey() {
        Song notThere = new Song("Test", "Test", "", "");
        notThere.setKey("ThisTestKey");
        c.add(notThere);
        Song actual = c.find(notThere);
        assertEquals("The key should be the same if we add a song that has a key.",
                "ThisTestKey", actual.getKey());
    }

    @Test
    public void AddingASongThatAlreadyExistsUpdatesTheSongInstead() {
        Song alreadyThere = new Song("Test", "Test", "", "");
        c.add(alreadyThere);
        int expected = c.size();
        Song duplicate = new Song("Test", "Test", "", "");
        c.add(duplicate);
        verify(_db, times(1)).updateChildren(anyMap());
    }

    @Test
    public void UpdatingASongCommitsItToTheFirebase() {
        Song s = new Song("Test", "Test", "", "");
        c.add(s);
        s.setVoteCount(4);
        c.update(s);
        verify(_db, times(1)).updateChildren(anyMap());
    }

    @Test
    public void UpdatingASongAddsANewSongIfTheSongToBeUpdatedDoesNotExistAtAll() {
        Song newSong = new Song("Test", "Test", "", "");
        c.update(newSong);
        verify(_db, times(0)).updateChildren(anyMap());
        verify(_db, times(1)).push();
        verify(_db, times(1)).setValue(newSong);
    }

    @Test
    public void UpdatingASongThatDoesntHaveAKeyFindsAMatchingSongThatDoes() {
        Song matchingSong = new Song("Test", "Test", "", "");
        matchingSong.setKey("a");
        c.add(matchingSong);
        Song nonKeySong = new Song("Test", "Test", "", "");
        c.update(nonKeySong);
        verify(_db, times(1)).updateChildren(anyMap());
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

    @Test
    public void RemovingASongRemovesItFromFirebase() {
        Song songToRemove = new Song("Test", "Test", "", "");
        c.add(songToRemove);
        verify(_db, times(0)).removeValue();
        c.remove(songToRemove);
        verify(_db, times(1)).removeValue();
    }

    @Test
    public void RemovingASongRemovesItIfThereIsASongWithMatchingTitleAndArtist() {
        Song songInCatalog = new Song("Test", "Test", "", "");
        c.add(songInCatalog);
        int size = c.size();
        Song songToRemove = new Song("Test", "Test", "", "");
        c.remove(songToRemove);
        assertEquals(size - 1, c.size());
    }

    @Test
    public void RemovingASongDoesNothingIfTheSongIsNotInTheCatalog() {
        Song inCatalog = new Song("Test", "Test", "", "");
        c.add(inCatalog);
        Song notInCatalog = new Song("Other", "Test", "", "");
        c.remove(notInCatalog);
        verify(_db, times(0)).removeValue();
    }
    // TODO: If you remove a song from the catalog, it's no longer there
    // TODO: You can get the list of songs??

}
