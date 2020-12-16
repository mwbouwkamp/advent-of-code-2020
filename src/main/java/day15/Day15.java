package day15;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day15 {
    public static void main(String[] args) {
        Map<Integer, Integer> lastVisited = new HashMap<>();
        lastVisited.put(18,0);
        lastVisited.put(8,1);
        lastVisited.put(0,2);
        lastVisited.put(5,3);
        lastVisited.put(4,4);
        lastVisited.put(1,5);

        int last = 20;
        for (int i = lastVisited.size(); i < 30000000 - 1; i++) {
            int newLast;
            if (!lastVisited.containsKey(last)) {
                newLast = 0;
            } else {
                newLast = i - lastVisited.get(last);
            }
            lastVisited.put(last, i);
            last = newLast;
        }
        System.out.println("\n" + last);

    }
}
