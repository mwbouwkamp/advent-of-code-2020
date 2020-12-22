package day17;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Space3D {

    Map<String, Boolean> coordinates;

    public Space3D(List<String> input) {
        List<String> modiefiedInput = modifyInput(input);
        this.coordinates = new HashMap<>();
        for (int y = 0; y < modiefiedInput.size(); y++) {
            for (int x = 0; x < modiefiedInput.get(0).length(); x++) {
                coordinates.put(x + "|" + y + "|" + 0, modiefiedInput.get(y).charAt(x) == '#');
                coordinates.put(x + "|" + y + "|" + 1, false);
                coordinates.put(x + "|" + y + "|" + -1, false);
            }
        }
    }

    public Space3D(Map<String, Boolean> coordinates) {
        this.coordinates = coordinates;
    }

    public Space3D getChild() {
        Map<String, Boolean> newCoordinates = new HashMap<>();
        for (String key: coordinates.keySet()) {
            String[] keyXYZ = key.split("\\|");
            int x = Integer.parseInt(keyXYZ[0]);
            int y = Integer.parseInt(keyXYZ[1]);
            int z = Integer.parseInt(keyXYZ[2]);
            int neighbors = getNumberOfActiveNeighbors(x, y, z);
            if (coordinates.get(key)) {
                if (neighbors == 2 || neighbors == 3) {
                    newCoordinates.put(key, true);
                } else {
                    newCoordinates.put(key, false);
                }
            } else {
                if (neighbors == 3) {
                    newCoordinates.put(key, true);
                    addMissingNeighbors(newCoordinates, x, y, z);
                } else {
                    newCoordinates.put(key, false);
                }
            }
        }
        return new Space3D(newCoordinates);
    }

    public void addMissingNeighbors(Map<String, Boolean> newCoordinates, int x, int y, int z) {
        for (int x1 = x - 1; x1 <= x + 1; x1++) {
            for (int y1 = y - 1; y1 <= y + 1; y1++) {
                for (int z1 = z - 1; z1 <= z + 1; z1++) {
                    if (null == coordinates.get(x1 + "|" + y1 + "|" + z1)) {
                        newCoordinates.put(x1 + "|" + y1 + "|" + z1, false);
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

    public int getNumberOfActiveNeighbors(int x, int y, int z) {
        int neighbors = 0;
        for (int x1 = x - 1; x1 <= x + 1; x1++) {
            for (int y1 = y - 1; y1 <= y + 1; y1++) {
                for (int z1 = z - 1; z1 <= z + 1; z1++) {
                    if (x1 == x && y1 == y && z1 == z) {
                        continue;
                    }
                    String keyXYZ = x1 + "|" + y1 + "|" + z1;
                    if (coordinates.containsKey(keyXYZ) && coordinates.get(x1 + "|" + y1 + "|" + z1)) {
                        neighbors++;
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
