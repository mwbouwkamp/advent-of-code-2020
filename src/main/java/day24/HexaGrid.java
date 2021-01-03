package day24;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HexaGrid {
    Map<String, Character> hexaGrid;

    public HexaGrid() {
        this.hexaGrid = new HashMap<>();
    }

    public void flipTile(String navigation) {
        int startX = 0;
        int startY = 0;
        int pointer = 0;
        while (pointer < navigation.length()) {
            String direction;
            if (pointer == navigation.length() - 1) {
                direction = navigation.substring(pointer, pointer + 1);
            } else {
                direction = navigation.substring(pointer, pointer + 2);
                if (direction.charAt(0) == 'e' || direction.charAt(0) == 'w') {
                    direction = direction.substring(0, 1);
                }
            }
            switch (direction) {
                case "e":
                    startX += 2;
                    break;
                case "w":
                    startX -= 2;
                    break;
                case "ne":
                    startX++;
                    startY--;
                    break;
                case "nw":
                    startX--;
                    startY--;
                    break;
                case "se":
                    startX++;
                    startY++;
                    break;
                case "sw":
                    startX--;
                    startY++;
                    break;
                default:
                    throw new IllegalArgumentException("Direction not valid: " + direction);
            }
            pointer += direction.length();
        }
        String coordinate = startX + "," + startY;
        hexaGrid.put(coordinate, null == hexaGrid.get(coordinate) || hexaGrid.get(coordinate) == 'w' ? 'b' : 'w');
    }

    public void nextDay() {
        hexaGrid = expandHexaGrid();
        hexaGrid = evolveHexaGrid();
    }

    private Map<String, Character> evolveHexaGrid() {
        Map<String, Character> newHexaGrid = new HashMap<>();
        for (String coordinatesString: hexaGrid.keySet()) {
            List<String> neighbourCoordinates = getNeighbourCoordinates(coordinatesString);
            long blackNeighbors = neighbourCoordinates.stream().filter(s -> null != hexaGrid.get(s) && hexaGrid.get(s) == 'b').count();
            if (hexaGrid.get(coordinatesString) == 'w' && blackNeighbors == 2) {
                newHexaGrid.put(coordinatesString, 'b');
            } else if (hexaGrid.get(coordinatesString) == 'b' && (blackNeighbors == 0 || blackNeighbors > 2)) {
                newHexaGrid.put(coordinatesString, 'w');
            } else {
                newHexaGrid.put(coordinatesString, hexaGrid.get(coordinatesString));
            }
        }
        return newHexaGrid;
    }

    private Map<String, Character> expandHexaGrid() {
        Map<String, Character> newHexaGrid = new HashMap<>();
        for (String coordinatesString: hexaGrid.keySet()) {
            newHexaGrid.put(coordinatesString, hexaGrid.get(coordinatesString));
            List<String> neighbourCoordinates = getNeighbourCoordinates(coordinatesString);
            for (String neighbourCoordinate : neighbourCoordinates) {
                if (hexaGrid.get(neighbourCoordinate) == null) {
                    newHexaGrid.put(neighbourCoordinate, 'w');
                }
            }
        }
        return newHexaGrid;
    }

    @NotNull
    private List<String> getNeighbourCoordinates(String coordinatesString) {
        String[] coordinates = coordinatesString.split(",");
        int coordX = Integer.parseInt(coordinates[0]);
        int coordY = Integer.parseInt(coordinates[1]);
        String e = (coordX + 2) + "," + coordY;
        String w = (coordX - 2) + "," + coordY;
        String se = (coordX + 1) + "," + (coordY + 1);
        String sw = (coordX - 1) + "," + (coordY + 1);
        String ne = (coordX + 1) + "," + (coordY - 1);
        String nw = (coordX - 1) + "," + (coordY - 1);
        return Arrays.asList(e, w, se, sw, ne, nw);
    }

    public long countBlack() {
        return hexaGrid.values().stream()
                .filter(c -> c == 'b')
                .count();
    }
}
