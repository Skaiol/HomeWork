package TropicalIsland;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Skaiol on 08.10.2016.
 */
public class TropicalIsland {
    private final HashMap<Point, IslandCell> _cells;
    private final ArrayList<IslandCell> _cellsStillNotConnected;
    private final int _width;
    private final int _height;

    public TropicalIsland(int[][] cells) {
        _height = cells.length;
        _width = cells[0].length;

        _cellsStillNotConnected = new ArrayList<>();
        _cells = new HashMap<Point, IslandCell>();
        for (int i = 0; i < _height; i++) {
            for (int j = 0; j < _width; j++) {
                int height = cells[i][j];
                IslandCell cell = new IslandCell(height, j, i);
                _cells.put(cell.getLocation(), cell);

                if (isBeach(i, j))
                    cell.setConnectionWithOcean();
                else
                    _cellsStillNotConnected.add(cell);
            }
        }
    }

    public void rain() {
        while (!_cellsStillNotConnected.isEmpty()) {
            for (IslandCell cell: _cellsStillNotConnected) {
                boolean canGrowth = true;
                ArrayList<IslandCell> neighbors = getNeighbors(cell);

                for (IslandCell neighbor: neighbors) {
                    if (cell.getHeight() >= neighbor.getHeight()) {
                        if (neighbor.isConnectedWithOcean()) {
                            cell.setConnectionWithOcean();
                            cell.setHeight(neighbor.getHeight());
                            break;
                        }
                    }

                    if (cell.getHeight() > neighbor.getHeight())
                        canGrowth = false;
                }

                if (cell.isConnectedWithOcean())
                    continue;

                if (canGrowth)
                    cell.incrementHeight();
            }

            _cellsStillNotConnected.removeIf(cell -> cell.isConnectedWithOcean());
        }
    }

    public int getFullWaterVolume() {
        if (_height <= 2 || _width <= 2) {
            return 0;
        }

        int result = 0;
        for (IslandCell cell: _cells.values()) {
            result += cell.getWaterVolume();
        }
        return result;
    }

    private ArrayList<IslandCell> getNeighbors(IslandCell cell) {
        Point location = cell.getLocation();

        ArrayList<IslandCell> neighbors = new ArrayList<IslandCell>();
        neighbors.add(_cells.get(new Point(location.x + 1, location.y)));
        neighbors.add(_cells.get(new Point(location.x - 1, location.y)));
        neighbors.add(_cells.get(new Point(location.x, location.y + 1)));
        neighbors.add(_cells.get(new Point(location.x, location.y - 1)));
        return neighbors;
    }

    private boolean isBeach(int i, int j) {
        int iMax = _height - 1;
        int jMax = _width - 1;
        return (i == 0 || i == iMax || j == 0 || j == jMax);
    }
}
