package day06;

import Utils.FileUtils;
import org.jetbrains.annotations.NotNull;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day06 {
    public static void main(String[] args) throws FileNotFoundException {
        List<String> input = FileUtils.getLinesFromInput("input06.txt");
        System.out.println(solveDay06a(input));
        System.out.println(solveDay06b(input));
    }

    private static long solveDay06a(List<String> input) {
        List<String> answers = processInput(input, "");
        return answers.stream()
                .map(Day06::countAnswersDay06a)
                .reduce(Long::sum)
                .orElse(-1L);
    }

    private static long solveDay06b(List<String> input) {
        List<String> answers = processInput(input, ",");
        return answers.stream()
                .map(Day06::countAnswersDay06b)
                .reduce(Long::sum)
                .orElse(-1L);
    }

    private static List<String> processInput(List<String> input, String deliminator) {
        List<String> answers = new ArrayList<>();
        StringBuilder builder = new StringBuilder(input.get(0));
        for (int i = 1; i < input.size(); i++) {
            if (input.get(i).equals("")) {
                answers.add(builder.toString());
                builder = new StringBuilder();
            } else {
                builder.append(deliminator);
                builder.append(input.get(i));
            }
        }
        builder.append(deliminator);
        answers.add(builder.toString());
        return answers;
    }

    private static long countAnswersDay06a(String answer) {
        return answer.chars()
                .distinct()
                .count();
    }

    private static long countAnswersDay06b(String answer) {
        if (answer.charAt(0) == ',') {
            answer = answer.substring(1);
        }
        String[] individualAnswers = answer.split(",");
        List<Character> yesAnswers = new ArrayList<>(stringToCharacterList(individualAnswers[0]));
        for (String individualAnswer: individualAnswers) {
            yesAnswers.retainAll(stringToCharacterList(individualAnswer));
        }
        return yesAnswers.size();
    }

    @NotNull
    private static List<Character> stringToCharacterList(String individualAnswer) {
        return individualAnswer
                .chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toList());
    }
}
