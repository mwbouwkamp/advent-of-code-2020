package day16;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Ticket {
    private List<Integer> values;

    public Ticket(String stringRep) {
        this.values = Arrays.stream(stringRep.split(","))
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());
    }

    public List<Integer> getMismatchingNumbers(List<Rule> rules) {
        return values.stream()
                .filter(v -> rules.stream().noneMatch(r -> r.matchesRule(v)))
                .collect(Collectors.toList());
    }
}
