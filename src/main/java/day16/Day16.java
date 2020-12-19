package day16;

import Utils.FileUtils;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Day16 {
    public static void main(String[] args) throws FileNotFoundException {
        List<String> input = FileUtils.getLinesFromInput("input16.txt");
        List<Rule> rules = new ArrayList<>();
        Ticket myTicket;
        List<Ticket> tickets = new ArrayList<>();
        processInput(input, rules, tickets);

        System.out.println(solveDay16a(rules, tickets));
    }

    private static int solveDay16a(List<Rule> rules, List<Ticket> tickets) {
        return tickets.stream()
                .map(t -> t.getMismatchingNumbers(rules))
                .flatMap(List::stream)
                .reduce(Integer::sum)
                .orElse(-1);
    }

    private static void processInput(List<String> input, List<Rule> rules, List<Ticket> tickets) {
        Ticket myTicket;
        int block = 0;
        for (int i = 0; i < input.size(); i++) {
            if (input.get(i).equals("")) {
                block++;
                i++;
            } else {
                switch (block) {
                    case 0:
                        rules.add(new Rule(input.get(i)));
                        break;
                    case 1:
                        myTicket = new Ticket(input.get(i));
                        break;
                    case 2:
                        tickets.add(new Ticket(input.get(i)));
                        break;
                    default:
                        break;
                }
            }
        }
    }
}
