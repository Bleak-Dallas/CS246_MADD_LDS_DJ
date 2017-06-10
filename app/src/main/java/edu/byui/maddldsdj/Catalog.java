package edu.byui.maddldsdj;

/**
 * A collection of approved Songs
 * @author Matthew Burr
 * @since 2017-06-10
 * @version 1.0
 */

public class Catalog {

    private int _size = 0;

    public int size() {
        return _size;
    }

    public void add(Song song) {
        _size += 1;
    }

    public void remove(Song song) {
        if (_size > 0)
            _size -= 1;
    }
}
