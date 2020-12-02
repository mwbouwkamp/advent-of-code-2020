package Day02;

import Utils.FileUtils;

import java.io.FileNotFoundException;
import java.util.List;

public class Day02 {
    public static void main(String[] args) throws FileNotFoundException {
        List<String> passwords = FileUtils.getLinesFromInput("input02.txt");
        System.out.println(solveDay02a(passwords));
        System.out.println(solveDay02b(passwords));
    }

    private static long solveDay02a(List<String> passwords) {
        return passwords.stream()
                .filter(p -> isValidPasswordDay02a(p))
                .count();
    }

    private static long solveDay02b(List<String> passwords) {
        return passwords.stream()
                .filter(p -> isValidPasswordDay02b(p))
                .count();
    }

    private static boolean isValidPasswordDay02a(String password) {
        String[] passwordSections = password.split(" ");
        char letter = passwordSections[1].charAt(0);
        long letterOccurance = passwordSections[2].chars()
                .filter(c -> c == letter)
                .count();
        String[] upperLower = passwordSections[0].split("-");
        return (letterOccurance >= Integer.parseInt(upperLower[0]) && letterOccurance <= Integer.parseInt(upperLower[1]));
    }

    private static boolean isValidPasswordDay02b(String password) {
        String[] passwordSections = password.split(" ");
        char letter = passwordSections[1].charAt(0);
        String[] firstSecond = passwordSections[0].split("-");
        int countMatch = 0;
        try {
            if (passwordSections[2].charAt(Integer.parseInt(firstSecond[0]) - 1) == letter) {
                countMatch++;
            }
            if (passwordSections[2].charAt(Integer.parseInt(firstSecond[1]) - 1) == letter) {
                countMatch++;
            }
            return countMatch == 1;
        } catch (Exception e) {
            return false;
        }
    }

}
