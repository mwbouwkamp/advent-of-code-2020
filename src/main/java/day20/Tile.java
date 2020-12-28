package day20;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tile {
    private char[][] tile;
    private int index;
    private Map<Integer, String> edges;

    public Tile(int index, List<String> inputLines) {
        this.index = index;
        this.tile = new char[10][10];
        StringBuilder rightEdgeBuilder = new StringBuilder();
        StringBuilder leftEdgeBuilder = new StringBuilder();
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                tile[y][x] = inputLines.get(y).charAt(x);
            }
            rightEdgeBuilder.append(inputLines.get(y).charAt(9));
            leftEdgeBuilder.append(inputLines.get(y).charAt(0));
        }
        this.edges = new HashMap<>();
        this.edges.put(0, inputLines.get(0));
        this.edges.put(1, rightEdgeBuilder.toString());
        this.edges.put(2, inputLines.get(9));
        this.edges.put(3, leftEdgeBuilder.toString());
    }

    public int getIndex() {
        return this.index;
    }

    public List<Tile> getRotatedTiles() {
        int width = tile.length;
        List<String> tile90 = new ArrayList<>();
        List<String> tile180 = new ArrayList<>();
        List<String> tile270 = new ArrayList<>();
        for (int y = 0; y < width; y++) {
            StringBuilder line90 = new StringBuilder();
            StringBuilder line180 = new StringBuilder();
            StringBuilder line270 = new StringBuilder();
            for (int x = 0; x < width; x++) {
                line90.append(tile[width - x - 1][y]);
                line180.append(tile[width - y - 1][width - x - 1]);
                line270.append(tile[x][width - y - 1]);
            }
            tile90.add(line90.toString());
            tile180.add(line180.toString());
            tile270.add(line270.toString());
        }
        List<Tile> rotatedTiles = new ArrayList<>();
        rotatedTiles.add(new Tile(this.index, tile90));
        rotatedTiles.add(new Tile(this.index, tile180));
        rotatedTiles.add(new Tile(this.index, tile270));
        return rotatedTiles;
    }

    public boolean isValidTile(Map<Integer, String> constraints) {
        return ((constraints.get(0) == null || this.edges.get(0).equals(constraints.get(0)))
                && (constraints.get(3) == null || this.edges.get(3).equals(constraints.get(3))));
    }

    public String getEdge(int key) {
        return this.edges.get(key);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (char[] row : tile) {
            builder.append("\n");
            for (int x = 0; x < tile.length; x++) {
                builder.append(row[x]);
            }
        }
        return builder.toString();
    }
}
