package day24;

import Utils.FileUtils;

import java.io.FileNotFoundException;
import java.util.List;


public class Day24 {

    // 282 too high
    // 188 too low

    public static void main(String[] args) throws FileNotFoundException {
        List<String> input = FileUtils.getLinesFromInput("input24.txt");
        HexaGrid hexaGrid = new HexaGrid();
        for (String line: input) {
            hexaGrid.flipTile(line);
        }
        System.out.println(hexaGrid.countBlack());
        for (int i = 0; i < 100; i++) {
            hexaGrid.nextDay();
            System.out.println(hexaGrid.countBlack());
        }


    }
}
