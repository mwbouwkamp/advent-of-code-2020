package day13;

import Utils.FileUtils;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Day13 {
    public static void main(String[] args) throws FileNotFoundException {
        List<String> input = FileUtils.getLinesFromInput("input13.txt");
        int earliest = Integer.parseInt(input.get(0));
        String[] busses = input.get(1).split(",");
        System.out.println(solveDay13a(earliest, busses));
        System.out.println(solveDay13b(busses, 100000000000000L));
    }

    private static int solveDay13a(int earliest, String[] busses) {
        List<Bus> busList = Arrays.stream(busses)
                .filter(s -> !s.equals("x"))
                .map(s -> new Bus(s, Integer.parseInt(s) - earliest % Integer.parseInt(s)))
                .sorted(Bus::compareTo)
                .collect(Collectors.toList());
        return busList.get(0).getWaitingTime() * busList.get(0).getNumber();
    }

    private static long solveDay13b(String[] busses, long start) {
        Map<Integer, Integer> busMap = new HashMap<>();
        for (int i = 0; i < busses.length; i++) {
            try {
                busMap.put(i, Integer.parseInt(busses[i]));
            } catch (IllegalArgumentException ignore) {}
        }
        long timeStamp = start;
        Set<Long> maxDeltaList = new HashSet<>();
        long maxDelta = 1;
        while (maxDeltaList.size() < busses.length) {
            boolean match = true;
            for (int key: busMap.keySet()) {
                if ((timeStamp + key) % busMap.get(key) != 0) {
                    match = false;
                } else {
                    if (!maxDeltaList.contains((long) busMap.get(key))) {
                        maxDelta *= busMap.get(key);
                        maxDeltaList.add((long) busMap.get(key));
                    }
                }
            }
            if (match) {
                return timeStamp;
            }
            timeStamp += maxDelta;
        }
        return -1;
    }
}
