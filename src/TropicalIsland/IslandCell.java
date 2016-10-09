package TropicalIsland;

/**
 * Created by Skaiol on 09.10.2016.
 */
public class IslandCell {
    private final int _initialHeight;
    private int _height;

    public IslandCell(int initialHeight) {
        _initialHeight = initialHeight;
        _height = initialHeight;
    }

    public void increaseHeight(int value) {
        if (_height >= value)
            return;
        _height = value;
    }

    public int getWaterVolume() {
        return _height - _initialHeight;
    }
}
