package day14;

import Utils.FileUtils;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day14 {
    public static void main(String[] args) throws FileNotFoundException {
        List<String> input = FileUtils.getLinesFromInput("input14.txt");
        List<String> inputTest = FileUtils.getLinesFromInput("input14.txt");
        System.out.println(solveDay14a(input));
        System.out.println(solveDay14b(inputTest));
    }

    private static long solveDay14b(List<String> input) {
        Map<Long, Long> mem = new HashMap<>();
        String mask = "";
        for (String line: input) {
            if (line.startsWith("mask")) {
                mask = line.substring(7);
            } else {
                String[] parts = line.split(" ");
                int index = Integer.parseInt(parts[0]
                        .replace("mem[", "")
                        .replace("]", ""));
                String binary = Long.toBinaryString(index);
                StringBuilder builder = new StringBuilder();
                int offset = mask.length() - binary.length();
                for (int j = 0; j < mask.length(); j++) {
                    if (mask.charAt(j) == '0') {
                        if (j - offset >= 0) {
                            builder.append(binary.charAt(j - offset));
                        } else {
                            builder.append('0');
                        }
                    } else if (mask.charAt(j) == '1'){
                        builder.append('1');
                    } else {
                        builder.append('X');
                    }
                }
                String result = builder.toString();
                List<StringBuilder> builders = new ArrayList<>();
                StringBuilder builderInitial = new StringBuilder();
                builders.add(builderInitial);
                for (String letter: result.split("")) {
                    List<StringBuilder> newBuilders = new ArrayList<>();
                    if (letter.equals("X")) {
                        for (StringBuilder builder1: builders) {
                            StringBuilder builder2 = new StringBuilder(builder1);
                            builder1.append("0");
                            builder2.append("1");
                            newBuilders.add(builder1);
                            newBuilders.add(builder2);
                        }
                    } else {
                        for (StringBuilder builder1 : builders) {
                            builder1.append(letter);
                            newBuilders.add(builder1);
                        }
                    }
                    builders = newBuilders;
                }
                for (StringBuilder builder1: builders) {
                    mem.put(Long.parseLong(builder1.toString(), 2), Long.parseLong(parts[2]));
                }



            }
        }
        return mem.values().stream().reduce(Long::sum).orElse(-1L);
    }


    private static long solveDay14a(List<String> input) {
        Map<Integer, Long> mem = new HashMap<>();
        String mask = "";
        for (String line: input) {
            if (line.startsWith("mask")) {
                mask = line.substring(7);
            } else {
                String[] parts = line.split(" ");
                int index = Integer.parseInt(parts[0]
                        .replace("mem[", "")
                        .replace("]", ""));
                String binary = Long.toBinaryString(Long.parseLong(parts[2]));
                StringBuilder builder = new StringBuilder();
                int offset = mask.length() - binary.length();
                for (int j = 0; j < mask.length(); j++) {
                    if (mask.charAt(j) == 'X') {
                        if (j - offset >= 0) {
                            builder.append(binary.charAt(j - offset));
                        } else {
                            builder.append('0');
                        }
                    } else {
                        builder.append(mask.charAt(j));
                    }
                }
                mem.put(index, Long.parseLong(builder.toString(), 2));
            }
        }
        return mem.values().stream().reduce(Long::sum).orElse(-1L);
    }

    //11209153579931 too low
    //11209081950107
    //25124823351451 too high
}
