package day19;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Rule {
    boolean processed;
    boolean active;
    String rule;
    int index;

    public Rule(int index, String ruleString) {
        this.index = index;
        this.rule = ruleString;
        this.processed = false;
        this.active = true;
    }

    public List<Rule> processOr() {
        List<Rule> newRules = new ArrayList<>();
        String[] orParts = rule.split(" \\| ");
        if (orParts.length > 1) {
            newRules.add(new Rule(index, orParts[0]));
            newRules.add(new Rule(index, orParts[1]));
        } else {
            newRules.add(this);
        }
        return newRules;
    }

//    public boolean processAnd(Map<Integer, List<Rule>> processedRules) {
//        rule = Arrays.stream(rule.split(" \\| "))
//                .map(op -> Arrays.stream(op.split(" "))
//                        .map(ap -> !ap.matches("[a-b]+") && processedRules.containsKey(Integer.parseInt(ap)) ? processedRules.get(Integer.parseInt(ap)).rule : ap)
//                        .peek(s -> System.out.println("inner" + s))
//                        .reduce((a, b) -> a.matches("[a-b]+") && b.matches("[a-b]+") ? a + b : a + " " + b)
//                        .orElse(""))
//                .reduce((a, b) -> a + " | " + b)
//                .orElse("");
//        if (rule.matches("[a-b]+")) {
//            processed = true;
//        }
//        return processed;
//    }

}