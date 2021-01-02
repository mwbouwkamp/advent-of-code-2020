package day22;

import Utils.FileUtils;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Day22 {
    public static void main(String[] args) throws FileNotFoundException {
        List<String> input = FileUtils.getLinesFromInput("input22.txt");
//        List<String> input = FileUtils.getLinesFromInput("input22test.txt");
        List<Integer> playerA = new ArrayList<>();
        List<Integer> playerB = new ArrayList<>();
        for (int i = 1; i < 26; i++) {
            playerA.add(Integer.parseInt(input.get(i)));
            playerB.add(Integer.parseInt(input.get(i + 27)));
        }

//        for (int i = 1; i < 6; i++) {
//            playerA.add(Integer.parseInt(input.get(i)));
//            playerB.add(Integer.parseInt(input.get(i + 7)));
//        }

        List<List<Integer>> results = playGameRecursive(playerA, playerB, 0);
        System.out.println("Recursive game " + calcScoreWinner(results.get(0).size() > 0 ? results.get(0) : results.get(1)));

    }

    // 1259 too low

    private static List<List<Integer>> playGameRecursive(List<Integer> playerA, List<Integer> playerB, int deep) {
        List<String> previousRoundsA = new ArrayList<>();
        List<String> previousRoundsB = new ArrayList<>();
        while (playerA.size() > 0 && playerB.size() > 0) {
            String roundA = playerA.toString();
            String roundB = playerB.toString();
            if (previousRoundsA.contains(roundA) && previousRoundsB.contains(roundB)) {
                List<List<Integer>> result = new ArrayList<>();
                result.add(playerA);
                System.out.println("End game of level " + deep);
                return result;
            }
            previousRoundsA.add(roundA);
            previousRoundsB.add(roundB);
            int cardA = playerA.remove(0);
            int cardB = playerB.remove(0);
            boolean aWins;
            if (playerA.size() == cardA || playerB.size() == cardB) {
                List<List<Integer>> resultsGame = playGameRecursive(new ArrayList<>(playerA), new ArrayList<>(playerB), deep + 1);
                aWins = resultsGame.get(0).size() > 0;
            } else {
                aWins = cardA > cardB;
            }
            if (aWins) {
                playerA.add(cardA);
                playerA.add(cardB);
            } else {
                playerB.add(cardB);
                playerB.add(cardA);
            }
        }
        List<List<Integer>> result = new ArrayList<>();
        result.add(playerA);
        result.add(playerB);
        System.out.println("End game of level " + deep);
        return result;
    }

    private static void playGame(List<Integer> playerA, List<Integer> playerB) {
        while (playerA.size() > 0 && playerB.size() > 0) {
            int cardA = playerA.remove(0);
            int cardB = playerB.remove(0);
            if (cardA > cardB) {
                playerA.add(cardA);
                playerA.add(cardB);
            } else {
                playerB.add(cardB);
                playerB.add(cardA);
            }
        }
        System.out.println("Normal game " + calcScoreWinner(playerA.size() > 0 ? playerA : playerB));
    }

    private static int calcScoreWinner(List<Integer> deck) {
        int score = 0;
        for (int i = 0; i < deck.size(); i++) {
            score += (i + 1) * deck.get(deck.size() - i - 1);
        }
        return score;
    }
}
