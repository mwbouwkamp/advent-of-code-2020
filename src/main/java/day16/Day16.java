package day16;

import Utils.FileUtils;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day16 {
    public static void main(String[] args) throws FileNotFoundException {
        List<String> input = FileUtils.getLinesFromInput("input16.txt");
        List<Rule> rules = new ArrayList<>();
        List<Ticket> tickets = new ArrayList<>();
        Ticket myTicket = processInput(input, rules, tickets);

        System.out.println(solveDay16a(rules, tickets));
        System.out.println(solveDay16b(rules, tickets, myTicket));
    }

    private static int solveDay16a(List<Rule> rules, List<Ticket> tickets) {
        return tickets.stream()
                .map(t -> t.getMismatchingNumbers(rules))
                .flatMap(List::stream)
                .reduce(Integer::sum)
                .orElse(-1);
    }

    private static long solveDay16b(List<Rule> rules, List<Ticket> tickets, Ticket myTicket) {
        List<Ticket> validTickets = tickets.stream()
                .filter(t -> !t.hasMismatchingNumber(rules))
                .collect(Collectors.toList());
        Map<Integer, List<Rule>> ticketLayouts = new HashMap<>();
        for (int i = 0; i < rules.size(); i++) {
            int finalI = i;
            List<Rule> matchingRules = rules.stream()
                    .filter(r -> validTickets.stream().allMatch(t -> r.matchesRule(t.getValue(finalI))))
                    .collect(Collectors.toList());
            ticketLayouts.put(i, matchingRules);
        }
        Map<Integer, Rule> processedTicketLayouts = new HashMap<>();
        while (ticketLayouts.size() > 0) {
            System.out.println("Not processed: " + ticketLayouts.size() + "; processed: " + processedTicketLayouts.size());
            List<Integer> processingTicketKeys = ticketLayouts.keySet().stream()
                    .filter(k -> ticketLayouts.get(k).size() == 1)
                    .collect(Collectors.toList());
            for (int processingTicketKey: processingTicketKeys) {
                Rule processingTicketRule = ticketLayouts.get(processingTicketKey).get(0);
                ticketLayouts.remove(processingTicketKey);
                processedTicketLayouts.put(processingTicketKey, processingTicketRule);
                for (int ticketLayoutKey: ticketLayouts.keySet()) {
                    List<Rule> modifiedRuleList = ticketLayouts.get(ticketLayoutKey);
                    modifiedRuleList.remove(processingTicketRule);
                    ticketLayouts.put(ticketLayoutKey, modifiedRuleList);
                }
            }
        }

        return processedTicketLayouts.keySet().stream()
                .filter(k -> processedTicketLayouts.get(k).getLabel().startsWith("departure"))
                .mapToLong(myTicket::getValue)
                .reduce((a, b) -> a * b)
                .orElse(-1L);
    }

    private static Ticket processInput(List<String> input, List<Rule> rules, List<Ticket> tickets) {
        Ticket myTicket = null;
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
        return myTicket;
    }
}
