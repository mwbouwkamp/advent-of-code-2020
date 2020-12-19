package day16;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Rule {
    private String label;
    private List<Range> ranges;

    public Rule(String input) {
        String[] parts = input.split(": ");
        this.label = parts[0];
        this.ranges = Arrays.stream(parts[1].split(" or "))
                .map(this::createRange)
                .collect(Collectors.toList());
    }

    private Range createRange(String stringRep) {
        String[] parts = stringRep.split("-");
        return new Range(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
    }

    public boolean matchesRule(int number) {
        return ranges.stream()
                .anyMatch(r -> number >= r.min && number <= r.max);
    }

    public String getLabel() {
        return label;
    }

    private class Range {
        int min;
        int max;

        public Range(int min, int max) {
            this.min = min;
            this.max = max;
        }

    }
}
