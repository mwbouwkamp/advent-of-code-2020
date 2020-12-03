package Day03;

import java.awt.*;
import java.util.List;

public class Day03Map {

    private char[][] map;
    private int width;
    private int height;

    public Day03Map(List<String> data) {
        this.width = data.get(0).length();
        this.height = data.size();
        this.map = new char[width][height];
        for (int y = 0; y < height; y ++) {
            for (int x = 0; x < width; x++) {
                map[x][y] = data.get(y).charAt(x);
            }
        }
    }

    public char getCharAtLocation(int x, int y) {
        return map[x][y];
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return height;
    }
}
