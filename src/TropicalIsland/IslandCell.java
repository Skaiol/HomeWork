package TropicalIsland;

import java.awt.*;

/**
 * Created by Skaiol on 09.10.2016.
 */
public class IslandCell {
    private final int _initialHeight;
    private final Point _location;
    private int _height;
    private boolean _connectedWithOcean;

    public IslandCell(int initialHeight, int x, int y) {
        _initialHeight = initialHeight;
        _height = initialHeight;
        _location = new Point(x, y);
    }

    public boolean setHeight(int value) {
        int prev = _height;
        _height = value <= _initialHeight ? _initialHeight : value;
        return prev != _height;
    }

    public int getHeight() {
        return _height;
    }

    public void incrementHeight() {
        _height++;
    }

    public int getWaterVolume() {
        return _height - _initialHeight;
    }

    public Point getLocation() { return _location; }

    public boolean isConnectedWithOcean() {
        return _connectedWithOcean;
    }

    public boolean setConnectionWithOcean() {
        boolean prev = _connectedWithOcean;
        _connectedWithOcean = true;
        return prev != _connectedWithOcean;
    }
}
