package edu.byui.maddldsdj;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by Matthew on 6/15/2017.
 */

public class SongTest {

    @Test
    public void ToStringReturnsTitleOfSong() {
        Song s = new Song("TestSong", "", "", "", true, true);
        assertEquals("ToString should return the title of the Song.", "TestSong", s.toString());
    }

    @Test
    public void EqualsIsTrueIfSongTitleAndArtistMatch() {
        Song aSong = new Song("Title", "Artist", "", "");
        Song bSong = new Song("Title", "Artist", "", "");
        assertTrue("Equals should be true if the songs have same title and artist.",
                aSong.equals(bSong));
    }

    @Test
    public void EqualsIsFalseIfSongTitleOrArtistDoesNotMatch() {
        Song aSong = new Song("Title", "Artist", "", "");
        Song bSong = new Song("Wrong Title", "Artist", "", "");
        assertFalse("Equals should be false if the song titles don't match",
                aSong.equals(bSong));
        Song cSong = new Song("Title", "Wrong Artist", "", "");
        assertFalse("Equals should be false if the song artists don't match",
                aSong.equals(cSong));
        Song dSong = new Song("Wrong Title", "Wrong Artist", "", "");
        assertFalse("Equals should be false if both the title and artist don't match",
                aSong.equals(dSong));
    }

    @Test
    public void EqualsReturnsFalseIfTheParameterIsNotASong() {
        Song aSong = new Song("Title", "Artist", "", "");
        assertFalse("Equals should be false if the parameter is not a Song",
                aSong.equals(new Object()));
    }
}
