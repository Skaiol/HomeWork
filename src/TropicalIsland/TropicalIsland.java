package TropicalIsland;

/**
 * Created by Skaiol on 08.10.2016.
 */
public class TropicalIsland {
    private final IslandCell[][] _cells;
    private int _minHeightBorder;

    public TropicalIsland(int[][] cells) {
        _cells = new IslandCell[cells.length][cells[0].length];
        _minHeightBorder = cells[1][0];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                int height = cells[i][j];
                _cells[i][j] = new IslandCell(height);

                if (isBorder(i, j) && height < _minHeightBorder) {
                    _minHeightBorder = height;
                }
            }
        }
    }

    public void rain() {
        for (int i = 1; i < _cells.length - 1; i++) {
            for (int j = 1; j < _cells[0].length - 1; j++) {
                _cells[i][j].increaseHeight(_minHeightBorder);
            }
        }
    }

    public int getFullWaterVolume() {
        if (_cells.length <= 2 || _cells[0].length <= 2) {
            return 0;
        }

        int result = 0;
        for (int i = 1; i < _cells.length - 1; i++) {
            for (int j = 1; j < _cells[0].length - 1; j++) {
                result += _cells[i][j].getWaterVolume();
            }
        }
        return result;
    }

    private boolean isBorder(int i, int j) {
        int iMax = _cells.length - 1;
        int jMax = _cells[0].length - 1;
        return (i == 0 || i == iMax || j == 0 || j == jMax)
                && i != j
                && !(i == 0 && j == jMax)
                && !(i == iMax && j == 0);
    }
}
