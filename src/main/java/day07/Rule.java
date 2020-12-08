package day07;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Rule {
    private final String container;
    private final Map<String, Integer> containees;

    public Rule(String rule) {
        String[] parts = rule.split(" ");
        container = parts[0] + " " + parts[1];
        containees = new HashMap<>();
        for (int i = 4; i < parts.length; i += 4) {
            if (!("no").equals(parts[i])) {
                containees.put(parts[i + 1] + " " + parts[i + 2], Integer.parseInt(parts[i]));
            }
        }
    }

    public int getQuantityForContainee(String containee) {
        return containees.get(containee);
    }

    public boolean hasContainees() {
        return containees.size() > 0;
    }

    public Set<String> getContainees() {
        return containees.keySet();
    }

    public String getContainer() {
        return container;
    }

    public boolean containsAtLeastOneOfBags(List<String> bags) {
        return bags.stream()
                .anyMatch(containees::containsKey);
    }

    @Override
    public String toString() {
        return "--- " + container + " --- " + containees.toString();
    }
}
