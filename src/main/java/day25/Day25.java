package day25;

public class Day25 {
    static long divider = 20201227;

    public static void main(String[] args) {
        long subject = 7;
        long cardPublic = 8421034;
        long doorPublic = 15993936;
        int cardCycles = getCycles(cardPublic, subject);
        System.out.println(cardCycles);
        int doorCycles = getCycles(doorPublic, subject);
        System.out.println(doorCycles);
        System.out.println(performCycles(doorCycles, cardPublic));
    }

    private static long performCycles(int numCycles, long subject) {
        long value = 1;
        for (int i = 0; i < numCycles; i++) {
            value = cycle(value, subject);
        }
        return value;
    }

    private static int getCycles(long publicKey, long subject) {
        long value = 1;
        int cardCycles = 0;
        while (value != publicKey) {
            value = cycle(value, subject);
            cardCycles++;
        }
        return cardCycles;
    }


    private static long cycle(long value, long subject) {
        return value * subject % divider;
    }
}
