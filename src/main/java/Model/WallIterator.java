package Model;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * The wall iterator iterates through a list of walls.
 */
public class WallIterator implements Iterator {
    private ArrayList<Walls> walls;
    private int position;

    /**
     * Construct an iterator of the list walls and starting at position 0.
     * @param walls the list of walls that will e iterated.
     */
    public WallIterator(ArrayList<Walls> walls) {
        this.walls = walls;
        position = 0;
    }

    @Override
    public Walls next() {
        Walls returnCell = walls.get(position);
        position++;
        return returnCell;
    }

    @Override
    public boolean hasNext() {
        if ((position >= walls.size()) || (walls.get(position) == null)) {
            return false;
        }
        return true;
    }

}
