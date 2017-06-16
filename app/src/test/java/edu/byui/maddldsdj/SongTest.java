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
}
