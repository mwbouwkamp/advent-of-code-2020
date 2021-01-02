package day23;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day23 {
    public static void main(String[] args) {
//        List<Integer> circle = Arrays.stream("389125467".split(""))
//                .mapToInt(Integer::parseInt)
//                .boxed()
//                .collect(Collectors.toList());
        List<Integer> circle = Arrays.stream("974618352".split(""))
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());
        int minValue = circle.stream()
                .min(Integer::compareTo)
                .orElse(-1);
        int pointer = 0;
        for (int i = 0; i < 100; i++) {
            int current = circle.get(pointer);
            List<Integer> selection = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                selection.add(circle.get((pointer + j + 1) % circle.size()));
            }
            circle.removeAll(selection);

            int nextpointerValue = circle.get((circle.indexOf(current) + 1) % circle.size());

            int target = circle.stream()
                    .filter(n -> n != current)
                    .mapToInt(n -> current - n)
                    .filter(n -> n > 0)
                    .min()
                    .orElse(circle.stream().max(Integer::compareTo).get());
            if (target < current) {
                target = current - target;
            }
            int indexOfTarget = circle.indexOf(target);
            circle.addAll(indexOfTarget + 1, selection);
            System.out.println(circle.toString());
            pointer = circle.indexOf(nextpointerValue);
        }
        int indexOfOne = circle.indexOf(1);
        String result = circle.subList(indexOfOne + 1, circle.size()).toString() + circle.subList(0, indexOfOne).toString();
        result = Arrays.stream(result.replaceAll("[\\[\\]]", "")
                .split(", "))
                .reduce((a, b) -> a + b)
                .orElse("");
        System.out.println("Result " + result);


    }
}
