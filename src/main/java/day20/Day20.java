package day20;

import Utils.FileUtils;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Day20 {
    public static void main(String[] args) throws FileNotFoundException {
        List<String> input = FileUtils.getLinesFromInput("input20.txt");
        List<Tile> tiles = new ArrayList<>();
        List<Tile> originalTiles = new ArrayList<>();
        for (int i = 0; i < input.size(); i++) {
            String indexInput = input.get(i);
            int index = Integer.parseInt(indexInput.substring(5, 9));
            List<String> tileInput = new ArrayList<>();
            List<String> tileInputXMirror = new ArrayList<>();
            List<String> tileInputYMirror = new ArrayList<>();
            i++;
            for (int j = 0; j < 10; j++) {
                String line = input.get(i + j);
                tileInput.add(line);
                tileInputXMirror.add(new StringBuilder(line).reverse().toString());
                tileInputYMirror.add(0, line);
            }
            i += 10;
            Tile originalTile = new Tile(index, tileInput);
            tiles.add(originalTile);
            tiles.add(new Tile(index, tileInputXMirror));
            tiles.add(new Tile(index, tileInputYMirror));
            tiles.addAll(originalTile.getRotatedTiles());
        }
        Board firstBoard = new Board(tiles);
        List<Board> fringe = new ArrayList<>();
        fringe.add(firstBoard);
        int counter = 0;
        while (fringe.size() > 0) {
            Board toCheck = fringe.remove(fringe.size() - 1);
            System.out.println(counter++ + " - fringe size: " + fringe.size() + "; tiles: " + toCheck.countTiles());
            if (toCheck.fullBoard()) {
                System.out.println("Success: " + toCheck.getResult());
                System.out.println(toCheck);
            } else {
                List<Board> children = toCheck.getChildren();
                if (children!= null) {
                    fringe.addAll(children);
                }

            }
        }



    }
}
