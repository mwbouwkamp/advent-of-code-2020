package day17;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Space4D {

    Map<String, Boolean> coordinates;

    public Space4D(List<String> input) {
        List<String> modiefiedInput = modifyInput(input);
        this.coordinates = new HashMap<>();
        for (int y = 0; y < modiefiedInput.size(); y++) {
            for (int x = 0; x < modiefiedInput.get(0).length(); x++) {
                coordinates.put(x + "|" + y + "|0|-1", false);
                coordinates.put(x + "|" + y + "|0|0", modiefiedInput.get(y).charAt(x) == '#');
                coordinates.put(x + "|" + y + "|0|1", false);
                coordinates.put(x + "|" + y + "|1|-1", false);
                coordinates.put(x + "|" + y + "|1|0", false);
                coordinates.put(x + "|" + y + "|1|1", false);
                coordinates.put(x + "|" + y + "|-1|-1", false);
                coordinates.put(x + "|" + y + "|-1|0", false);
                coordinates.put(x + "|" + y + "|-1|1", false);
            }
        }
    }

    public Space4D(Map<String, Boolean> coordinates) {
        this.coordinates = coordinates;
    }

    public Space4D getChild() {
        Map<String, Boolean> newCoordinates = new HashMap<>();
        for (String key: coordinates.keySet()) {
            String[] keyXYZ = key.split("\\|");
            int x = Integer.parseInt(keyXYZ[0]);
            int y = Integer.parseInt(keyXYZ[1]);
            int z = Integer.parseInt(keyXYZ[2]);
            int w = Integer.parseInt(keyXYZ[3]);
            int neighbors = getNumberOfActiveNeighbors(x, y, z, w);
            if (coordinates.get(key)) {
                if (neighbors == 2 || neighbors == 3) {
                    newCoordinates.put(key, true);
                } else {
                    newCoordinates.put(key, false);
                }
            } else {
                if (neighbors == 3) {
                    newCoordinates.put(key, true);
                    addMissingNeighbors(newCoordinates, x, y, z, w);
                } else {
                    newCoordinates.put(key, false);
                }
            }
        }
        return new Space4D(newCoordinates);
    }

    public void addMissingNeighbors(Map<String, Boolean> newCoordinates, int x, int y, int z, int w) {
        for (int x1 = x - 1; x1 <= x + 1; x1++) {
            for (int y1 = y - 1; y1 <= y + 1; y1++) {
                for (int z1 = z - 1; z1 <= z + 1; z1++) {
                    for (int w1 = w - 1; w1 <= w + 1; w1++) {
                        String key = x1 + "|" + y1 + "|" + z1 + "|" + w1;
                        if (null == coordinates.get(key)) {
                            newCoordinates.put(key, false);
                        }
                    }
                }
            }
        }
    }

    public long countActive() {
        return coordinates.values().stream()
                .filter(b -> b)
                .count();
    }

    public int getNumberOfActiveNeighbors(int x, int y, int z, int w) {
        int neighbors = 0;
        for (int x1 = x - 1; x1 <= x + 1; x1++) {
            for (int y1 = y - 1; y1 <= y + 1; y1++) {
                for (int z1 = z - 1; z1 <= z + 1; z1++) {
                    for (int w1 = w - 1; w1 <= w + 1; w1++) {
                        if (x1 == x && y1 == y && z1 == z && w1 == w) {
                            continue;
                        }
                        String keyXYZ = x1 + "|" + y1 + "|" + z1 + "|" + w1;
                        if (coordinates.containsKey(keyXYZ) && coordinates.get(keyXYZ)) {
                            neighbors++;
                        }
                    }
                }
            }
        }
        return neighbors;
    }

    private List<String> modifyInput(List<String> input) {
        List<String> modifiedInput = new ArrayList<>();
        String emptyRow = input.get(0).replaceAll("#", ".") + "..";
        modifiedInput.add(emptyRow);
        modifiedInput.addAll(input.stream()
                .map(s -> "." + s + ".")
                .collect(Collectors.toList()));
        modifiedInput.add(emptyRow);
        return modifiedInput;
    }


}
