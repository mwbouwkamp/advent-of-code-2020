package Day04;

import Utils.FileUtils;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Day04 {
    public static void main(String[] args) throws FileNotFoundException {
        List<String> input = FileUtils.getLinesFromInput("input04.txt");
        System.out.println(solveDay04(input, 'a'));
        System.out.println(solveDay04(input, 'b'));
    }

    private static int solveDay04(List<String> input, char part) {
        List<List<String>> passportsStrings = new ArrayList<>();
        int counter = 0;
        for (String line: input) {
            if (line.equals("")) {
                counter++;
            } else {
                try {
                    passportsStrings.get(counter).addAll(new ArrayList<>(Arrays.asList(line.split(" "))));
                } catch (Exception e) {
                    passportsStrings.add(new ArrayList<>(Arrays.asList(line.split(" "))));
                }
            }
        }
        int counterA = 0;
        int counterB = 0;
        for (List<String> passportString: passportsStrings) {
            Map<String, String> passportFields = passportString.stream()
                    .collect(Collectors.toMap(s -> s.split(":")[0], s -> s.split(":")[1]));
            Passport passport = new Passport(
                    passportFields.get("byr"),
                    passportFields.get("iyr"),
                    passportFields.get("eyr"),
                    passportFields.get("hgt"),
                    passportFields.get("hcl"),
                    passportFields.get("ecl"),
                    passportFields.get("pid"),
                    passportFields.get("cid"));
            if (passport.hasAllRequiredFields()) {
                counterA++;
            }
            if (passport.isValid()) {
                counterB++;
            }
        }
        return part == 'a' ? counterA : part == 'b' ? counterB : -1;
    }
}
