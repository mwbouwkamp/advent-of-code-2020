package day23;

import java.util.*;
import java.util.stream.Collectors;

public class Circle {

    private Node current;
    private Map<Integer, Node> nodeMap;
    private int maxValue = 1000000;
    private int minValue = 1;

    public Circle(String input) {
        nodeMap = new HashMap<>();
        List<Integer> inputList = Arrays.stream(input.split(""))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        for (int i = 10; i <= 1000000; i ++) {
            inputList.add(i);
        }
        for (Integer integer : inputList) {
            nodeMap.put(integer, new Node(integer));
        }
        for (int i = 0; i < inputList.size(); i++) {
            nodeMap.get(inputList.get(i)).setNextNode(nodeMap.get(inputList.get((i + 1) % inputList.size())));
        }
        current = nodeMap.get(inputList.get(0));
    }

    public long getResult() {
        Node first = nodeMap.get(1).nextNode;
        Node second = first.getNextNode();
        return (long) first.getValue() * (long) second.getValue();
    }

    public void cycle() {
        Node firstOfSelection = current.getNextNode();
        Node secondOfSelection = firstOfSelection.getNextNode();
        Node thirdOfSelection = secondOfSelection.getNextNode();
        Node nextCurrentNode = thirdOfSelection.getNextNode();
        int targetValue = current.getValue() - 1;
        targetValue = correctTargetValue(firstOfSelection, secondOfSelection, thirdOfSelection, targetValue);
        if (targetValue < minValue) {
            targetValue = maxValue;
            targetValue = correctTargetValue(firstOfSelection, secondOfSelection, thirdOfSelection, targetValue);
        }
        Node targetNode = nodeMap.get(targetValue);
        current.setNextNode(nextCurrentNode);
        Node afterTargetNode = targetNode.getNextNode();
        targetNode.setNextNode(firstOfSelection);
        thirdOfSelection.setNextNode(afterTargetNode);
        current = nextCurrentNode;
    }

    private int correctTargetValue(Node firstOfSelection, Node secondOfSelection, Node thirdOfSelection, int targetValue) {
        List<Integer> selection = new ArrayList<>();
        selection.add(firstOfSelection.getValue());
        selection.add(secondOfSelection.getValue());
        selection.add(thirdOfSelection.getValue());
        selection.sort((a, b) -> b - a);
        for (int number: selection) {
            if (number == targetValue) {
                targetValue--;
            }
        }
        return targetValue;
    }

    public Map<Integer, Node> getNodeMap() {
        return nodeMap;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        Node pointer = current;
        for (int i = 0; i < nodeMap.size(); i++) {
            builder.append(pointer.value);
            pointer = pointer.getNextNode();
        }
        return builder.toString();
    }
    public class Node {

        private Node nextNode;
        private int value;

        public Node(int value) {
            this.value = value;
        }

        public void setNextNode(Node nextNode) {
            this.nextNode = nextNode;
        }

        public Node getNextNode() {
            return nextNode;
        }

        public int getValue() {
            return value;
        }

        @Override
        public String toString() {
            return Integer.toString(value);
        }
    }
}
