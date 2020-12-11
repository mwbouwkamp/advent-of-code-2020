package day11;

import java.util.ArrayList;
import java.util.List;

public class SeatingMap {
    private final String[][] map;
    private final int width;
    private final int height;

    public SeatingMap(List<String> input) {
        this.width = input.get(0).length();
        this.height = input.size();
        this.map = new String[height][width];
        for (int i = 0; i < input.size(); i++) {
            map[i] = input.get(i).split("");
        }
    }

    public SeatingMap(int width, int height) {
        this.width = width;
        this.height = height;
        this.map = new String[height][width];
    }

    public Long getNumAdjacentA(int x, int y) {
        List<String> adjacent = new ArrayList<>();
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i != 0 || j != 0) {
                    try {
                        adjacent.add(map[y + j][x + i]);
                    } catch (IndexOutOfBoundsException ignored) {}
                }
            }
        }
        return adjacent.stream().filter(s -> s.equals("#")).count();
    }

    public Long getNumAdjacentB(int x, int y) {
        Long adjacent = 0L;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i != 0 || j != 0) {
                    boolean continueSearch = true;
                    int distance = 1;
                    while (continueSearch) {
                        int checkX = x + i * distance;
                        int checkY = y + j * distance;
                        if (checkX >= 0 && checkY >= 0 && checkX < width && checkY < height) {
                            if (map[checkY][checkX].equals("#")) {
                                adjacent++;
                                continueSearch = false;
                            } else if (map[checkY][checkX].equals("L")) {
                                continueSearch = false;
                            } else {
                                distance++;
                            }
                        } else {
                            continueSearch = false;
                        }
                    }
                }
            }
        }
        return adjacent;
    }

    public SeatingMap getNextMap(long tolerance, char mode) {
        SeatingMap newMap = new SeatingMap(this.width, this.height);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                String state = map[y][x];
                Long adjacentOccupied = mode == 'a' ? getNumAdjacentA(x, y) : getNumAdjacentB(x, y);
                if (state.equals("L") && adjacentOccupied == 0L) {
                    newMap.setSeat(x, y, "#");
                } else if (state.equals("#") && adjacentOccupied >= tolerance) {
                    newMap.setSeat(x, y, "L");
                } else {
                    newMap.setSeat(x, y, this.getSeat(x, y));
                }
            }
        }
        return newMap;
    }

    public void setSeat(int x, int y, String value) {
        map[y][x] = value;
    }

    public String getSeat(int x, int y) {
        return map[y][x];
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        return obj.toString().equals(this.toString());
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                builder.append(map[y][x]);
            }
            builder.append("\n");
        }
        return builder.toString();
    }
}
