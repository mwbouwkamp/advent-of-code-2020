package day19;

import Utils.FileUtils;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day19 {
    public static void main(String[] args) throws FileNotFoundException {
        List<String> input = FileUtils.getLinesFromInput("input19test.txt");
        int i = 0;
        Map<String, Rule> rules = new HashMap<>();
        for (; i < input.size(); i++) {
            if (input.get(i).equals("")) {
                break;
            } else {
                String[] ruleInput = input.get(i).split(": ");
                Rule newRule = new Rule(Integer.parseInt(ruleInput[0]), ruleInput[1].replaceAll("[\"]", ""));
                rules.put(ruleInput[0], newRule);
            }
        }
        List<String> messages = new ArrayList<>();
        for (; i < input.size(); i++) {
            messages.add(input.get(i));
        }

        String zeroRule = getZeroRule(rules);
        System.out.println(zeroRule);

        Set<Integer> validRules = new HashSet<>();
        List<String> processingRules = new ArrayList<>();
        processingRules.add(zeroRule);
        int counter = 0;
        int sum = 0;
        int block = 10000000;
        while (processingRules.size() > 0) {
            if (counter == block) {
                counter = 0;
                System.out.println(sum / block + " " + validRules.size());
                sum = 0;
            } else {
                counter++;
                sum += processingRules.size();
            }
            String processingRule = processingRules.remove(processingRules.size() - 1);
            if (!processingRule.contains("|")) {
                processingRule = processingRule
                        .replaceAll("\\(", "")
                        .replaceAll("\\)", "")
                        .replaceAll("a",  "0")
                        .replaceAll("b", "1");
                validRules.add(Integer.parseInt(processingRule, 2));
            } else {
                List<String> newRules = reduceRule(processingRule);
                for (String newRule: newRules) {
                    if (!processingRules.contains(newRule)) {
                        processingRules.add(newRule);
                    }
                }
            }
        }

        System.out.println(validRules.toString());
        System.out.println(messages.stream()
                .mapToInt(m -> Integer.parseInt(m
                        .replaceAll("a", "0")
                        .replaceAll("b", "1"), 2))
                .filter(validRules::contains)
                .count());
    }

    private static String getZeroRule(Map<String, Rule> rules) {
        String zeroRule = rules.get("0").rule;
        while (true) {
            Pattern pattern = Pattern.compile("\\d+");
            Matcher matcher = pattern.matcher(zeroRule);
            int indexStart = matcher.find() ? matcher.start() : -1;
            if (indexStart == -1) {
                break;
            }
            int indexEnd = indexStart + 1;
            while (indexEnd < zeroRule.length()) {
                if (zeroRule.substring(indexEnd).charAt(0) >= '0' && zeroRule.substring(indexEnd).charAt(0) <= '9' ) {
                    indexEnd++;
                } else {
                    break;
                }
            }
            String original = zeroRule.substring(indexStart, indexEnd);
            zeroRule = zeroRule.substring(0, indexStart) +
                    " ( " +
                    rules.get(original).rule +
                    " ) " +
                    zeroRule.substring(indexEnd);
        }
        zeroRule = zeroRule
                .replaceAll("\\s", "")
                .replaceAll("\\(a\\)", "a")
                .replaceAll("\\(b\\)", "b");
        return zeroRule;
    }

    private static List<String> reduceRule(String rule) {
        List<String> reducedRules = new ArrayList<>();
        int positionOr = rule.lastIndexOf('|');
        if (positionOr == -1) {
            return reducedRules;
        } else {
            int position = positionOr;
            int numBrackets = 0;
            int positionOpening= -1;
            while (position > 0) {
                if (rule.charAt(position) == ')') {
                    numBrackets++;
                } else if (rule.charAt(position) == '(') {
                    numBrackets--;
                }
                if (numBrackets == -1) {
                    positionOpening = position;
                    break;
                }
                position--;
            }
            position = positionOr;
            numBrackets = 0;
            int positionClosing = -1;
            while (position < rule.length()) {
                if (rule.charAt(position) == '(') {
                    numBrackets++;
                } else if (rule.charAt(position) == ')') {
                    numBrackets--;
                }
                if (numBrackets == -1) {
                    positionClosing = position;
                    break;
                }
                position++;
            }
            String beforeBrackets = positionOpening == -1 ? "" : rule.substring(0, positionOpening + 1);
            String afterBrackets = positionClosing == -1 ? "" : rule.substring(positionClosing);
            String beforeOr = rule.substring(positionOpening + 1, positionOr);
            String afterOr = rule.substring(positionOr + 1, positionClosing);
            reducedRules.add(beforeBrackets + beforeOr + afterBrackets);
            reducedRules.add(beforeBrackets + afterOr + afterBrackets);
        }
        return reducedRules;
    }
}
