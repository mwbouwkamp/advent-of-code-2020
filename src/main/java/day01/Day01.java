package day01;

import Utils.FileUtils;

import java.io.FileNotFoundException;
import java.util.List;

public class Day01 {
    public static void main(String[] args) throws FileNotFoundException {
        List<Integer> integerList = FileUtils.getIntegersFromInput("input01.txt");
        integerList.sort(Integer::compareTo);
        System.out.println(solveDay01a(integerList));
        System.out.println(solveDay01b(integerList));
    }

    private static int solveDay01a(List<Integer> integerList) {
        for (int i = 0; i < integerList.size(); i++) {
            for (int j = integerList.size() - 1; j >= 0 ; j--) {
                if (integerList.get(i) + integerList.get(j) == 2020) {
                    return integerList.get(i) * integerList.get(j);
                } else if (integerList.get(i) + integerList.get(i) > 2020) {
                    break;
                }
            }
        }
        return -1;
    }

    private static int solveDay01b(List<Integer> integerList) {
        for (int i = 0; i < integerList.size(); i++) {
            for (int j = integerList.size() - 1; j >= 0 ; j--) {
                if (integerList.get(i) + integerList.get(j) > 2020) {
                    continue;
                }
                for (int k = integerList.size() - 1; k >= 0 ; k--) {
                    if (integerList.get(i) + integerList.get(i) + integerList.get(k)> 2020) {
                        continue;
                    }
                    if (integerList.get(i) + integerList.get(j) + integerList.get(k) == 2020) {
                        return integerList.get(i) * integerList.get(j) * integerList.get(k);
                    }
                }
            }
        }
        return -1;
    }
}
