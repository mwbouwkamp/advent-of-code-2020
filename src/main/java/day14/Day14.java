package day14;

import Utils.FileUtils;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day14 {
    public static void main(String[] args) throws FileNotFoundException {
        List<String> input = FileUtils.getLinesFromInput("input14.txt");
        Map<Integer, Long> mem = new HashMap<>();
        String mask = "";
        for (String line: input) {
            if (line.startsWith("mask")) {
                mask = input.get(0).substring(7);
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
        long count = mem.values().stream().reduce(Long::sum).orElse(-1L);
        System.out.println(count);

    }

    //11209153579931 too low
    //11209081950107
    //25124823351451 too high
}
