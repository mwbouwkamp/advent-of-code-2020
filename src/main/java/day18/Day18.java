package day18;

import Utils.FileUtils;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

public class Day18 {
    public static void main(String[] args) throws FileNotFoundException {
        List<String> input = FileUtils.getLinesFromInput("input18.txt");
        System.out.println(input.stream()
                .mapToLong( s -> solveDay18(s.replaceAll(" ", ""), 'a'))
                .sum());
        System.out.println(input.stream()
                .mapToLong( s -> solveDay18(s.replaceAll(" ", ""), 'b'))
                .sum());
    }


    // 27946895835820 low

    private static long solveDay18(String expression, char day) {
        int openingBracket = expression.indexOf('(');
        if (openingBracket < 0) {
            if (day =='a') {
                String[] numbersString = expression.split("[+*]");
                int[] numbers = Arrays.stream(numbersString)
                        .mapToInt(Integer::parseInt)
                        .toArray();
                int n = 0;
                int s = numbersString[n].length();
                long result = numbers[n];
                while (n < numbers.length - 1) {
                    n++;
                    char operator = expression.charAt(s);
                    result = operator == '+' ? result + numbers[n] : result * numbers[n];
                    s += numbersString[n].length() + 1;
                }
                return result;
            } else {
                int plus = expression.indexOf('+');
                while (plus > 0) {
                    int beforePlusIndex = expression.substring(0, plus).lastIndexOf('*');
                    String beforePlus = beforePlusIndex > 0 ? expression.substring(0, beforePlusIndex + 1) : "";
                    int afterPlusIndexPlus = expression.indexOf('+', plus + 1);
                    int afterPlusIndexMult = expression.indexOf('*', plus + 1);
                    if (afterPlusIndexPlus == -1) {
                        afterPlusIndexPlus = expression.length();
                    }
                    if (afterPlusIndexMult == -1) {
                        afterPlusIndexMult = expression.length();
                    }
                    int afterPlusIndex = Math.min(afterPlusIndexPlus, afterPlusIndexMult);
                    String afterPlus = expression.substring(afterPlusIndex);
                    String[] plusString = expression.substring(beforePlusIndex + 1, afterPlusIndex).split("[+]");
                    expression = beforePlus + (Long.parseLong(plusString[0]) + Long.parseLong(plusString[1])) + afterPlus;
                    plus = expression.indexOf('+');
                }
                String[] numbersString = expression.split("[*]");
                long[] numbers = Arrays.stream(numbersString)
                        .mapToLong(Long::parseLong)
                        .toArray();
                int n = 0;
                int s = numbersString[n].length();
                long result = numbers[n];
                if (numbers.length == 1) {
                    return numbers[0];
                }
                while (n < numbers.length - 1) {
                    n++;
                    char operator = expression.charAt(s);
                    result *= numbers[n];
                    s += numbersString[n].length() + 1;
                }
                return result;
            }
        } else {
            int closingBracket = openingBracket;
            int numBrackets = 0;
            do {
                if (expression.charAt(closingBracket) == '(') {
                    numBrackets++;
                } else if (expression.charAt(closingBracket) == ')') {
                    numBrackets--;
                }
                closingBracket++;
            } while (numBrackets > 0);
            String preBracket = expression.substring(0, openingBracket);
            String postBracket = expression.substring(closingBracket);
            String betweenBrackets = expression.substring(openingBracket + 1, closingBracket - 1);
            return solveDay18(preBracket + solveDay18(betweenBrackets, day) + postBracket, day);
        }
    }
}
