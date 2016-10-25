package TropicalIsland;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Skaiol on 08.10.2016.
 */
public class TropicalIsland {
    private final HashMap<Point, IslandCell> _cells;
    private final ArrayList<IslandCell> _cellsIsNotCoast;
    private final int _width;
    private final int _height;

    public TropicalIsland(int[][] cells) {
        _height = cells.length;
        _width = cells[0].length;

        _cellsIsNotCoast = new ArrayList<>();
        _cells = new HashMap<Point, IslandCell>();
        for (int i = 0; i < _height; i++) {
            for (int j = 0; j < _width; j++) {
                int height = cells[i][j];
                IslandCell cell = new IslandCell(height, j, i);
                _cells.put(cell.getLocation(), cell);

                if (isCoast(i, j))
                    cell.setConnectionWithOcean();
                else
                    _cellsIsNotCoast.add(cell);
            }
        }
    }

    public void rain() {
        boolean haveChanges = true;
        while (haveChanges) {
            haveChanges = false;
            for (IslandCell cell: _cellsIsNotCoast) {
                boolean canGrowth = true;
                ArrayList<IslandCell> neighbors = getNeighbors(cell);

                for (IslandCell neighbor: neighbors) {
                    if (cell.getHeight() >= neighbor.getHeight() && neighbor.isConnectedWithOcean()) {
                        if (cell.setConnectionWithOcean()) {
                            haveChanges = true;
                        }
                        break;
                    }

                    if (cell.getHeight() > neighbor.getHeight())
                        canGrowth = false;
                }

                if (cell.isConnectedWithOcean()) {
                    int minHeight = neighbors.stream()
                            .filter(x -> x.isConnectedWithOcean())
                            .map(x -> x.getHeight())
                            .sorted()
                            .findFirst()
                            .get();

                    if (cell.setHeight(minHeight)) {
                        haveChanges = true;
                    }
                    continue;
                }

                if (canGrowth) {
                    cell.incrementHeight();
                    haveChanges = true;
                }
            }
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

    private boolean isCoast(int i, int j) {
        int iMax = _height - 1;
        int jMax = _width - 1;
        return (i == 0 || i == iMax || j == 0 || j == jMax);
    }
}
