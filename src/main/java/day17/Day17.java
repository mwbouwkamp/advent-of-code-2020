package day17;

import Utils.FileUtils;

import java.io.FileNotFoundException;
import java.util.List;

public class Day17 {
    public static void main(String[] args) throws FileNotFoundException {
        List<String> input = FileUtils.getLinesFromInput("input17.txt");
        Space3D space3D = evolveSpace3D(input);
        System.out.println(space3D.countActive());
        Space4D space4D = evolveSpace4D(input);
        System.out.println(space4D.countActive());
    }

    private static Space3D evolveSpace3D(List<String> input) {
        Space3D space3D = new Space3D(input);
        for (int i = 0; i < 6; i++) {
            space3D = space3D.getChild();
        }
        return space3D;
    }

    private static Space4D evolveSpace4D(List<String> input) {
        Space4D space4D = new Space4D(input);
        for (int i = 0; i < 6; i++) {
            System.out.println(space4D.countActive());
            space4D = space4D.getChild();
        }
        return space4D;
    }
}
