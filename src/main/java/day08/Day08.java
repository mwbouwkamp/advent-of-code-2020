package day08;

import Utils.FileUtils;
import org.jetbrains.annotations.NotNull;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day08 {

    public static void main(String[] args) throws FileNotFoundException {
        List<String> input = FileUtils.getLinesFromInput("input08.txt");
        System.out.println(solveDay08a(input));
        System.out.println(solveDay08b(input));
    }

    private static String solveDay08a(List<String> input) {
        return solve(input);
    }

    private static int solveDay08b(List<String> input) {
        for (int i = 0; i < input.size(); i ++) {
            List<String> inputCopy = new ArrayList<>(input);
            String[] instructions = inputCopy.get(i).split(" ");
            boolean check = false;
            if ("jmp".equals(instructions[0])) {
                inputCopy.set(i, "nop " + instructions[1]);
                check = true;
            } else if ("nop".equals(instructions[0])) {
                inputCopy.set(i, "jmp " + instructions[1]);
                check = true;
            }
            if (check) {
                String[] answer = solve(inputCopy).split(" ");
                if ("Finished:".equals(answer[0])) {
                    return Integer.parseInt(answer[1]);
                }
            }
        }
        return -1;
    }

    @NotNull
    private static String solve(List<String> input) {
        int index = 0;
        int acc = 0;
        Set<Integer> visited = new HashSet<>();
        while (!visited.contains(index)) {
            visited.add(index);
            String[] instructions = input.get(index).split(" ");
            switch (instructions[0]) {
                case "acc":
                    index++;
                    acc += Integer.parseInt(instructions[1]);
                    break;
                case "jmp":
                    index += Integer.parseInt(instructions[1]);
                    break;
                case "nop":
                    index++;
                    break;
                default:
                    throw new IllegalArgumentException("Instruction not found: " + instructions[0]);
            }
            if (index == input.size() - 1) {
                return "Finished: " + acc;
            }
        }
        return "Loop: " + acc;
    }
}
