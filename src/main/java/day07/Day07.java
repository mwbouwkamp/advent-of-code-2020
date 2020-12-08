package day07;

import Utils.FileUtils;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Day07 {
    public static void main(String[] args) throws FileNotFoundException {
        List<String> input = FileUtils.getLinesFromInput("input07.txt");
        List<Rule> rules = input.stream()
                .map(Rule::new)
                .collect(Collectors.toList());
        System.out.println(solveDay07a(rules));
        Map<String, Rule> ruleMap = input.stream()
                .map(Rule::new)
                .collect(Collectors.toMap(Rule::getContainer, r -> r));
        System.out.println(solveDay07b(ruleMap));
    }

    private static int solveDay07a(List<Rule> rules) {
        Set<String> bags = new HashSet<>();
        List<String> newBags = new ArrayList<>();
        newBags.add("shiny gold");
        do {
            List<String> finalNewBags = newBags;
            List<String> bagsToAdd = rules.stream()
                    .filter(r -> r.containsAtLeastOneOfBags(finalNewBags))
                    .map(Rule::getContainer)
                    .collect(Collectors.toList());
            bags.addAll(bagsToAdd);
            newBags = bagsToAdd;
        } while (newBags.size() > 0);
        return bags.size();
    }

    private static long solveDay07b(Map<String, Rule> ruleMap) {
        Rule starter = ruleMap.get("shiny gold");
        return getCount(starter.getContainer(), ruleMap) - 1;
    }

    private static long getCount(String ruleString, Map<String, Rule> ruleMap) {
        Rule rule = ruleMap.get(ruleString);
        if (rule.hasContainees()) {
            return rule.getContainees().stream()
                    .mapToLong(s -> {
                        int factor = rule.getQuantityForContainee(s);
                        return factor * (getCount(s, ruleMap));
                    })
                    .sum() + 1;
        } else {
            return 1;
        }
    }
}
