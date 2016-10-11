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

    public void setHeight(int value) {
        if (value < _initialHeight)
            return;
        _height = value;
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

    public void setConnectionWithOcean() {
        this._connectedWithOcean = true;
    }
}
