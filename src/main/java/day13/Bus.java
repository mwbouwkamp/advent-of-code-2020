package day13;

import org.jetbrains.annotations.NotNull;

public class Bus implements Comparable<Bus>{
    private final int number;
    private final int waitingTime;

    public Bus(String number, int waitingTime) {
        this.number = Integer.parseInt(number);
        this.waitingTime = waitingTime;
    }

    public int getNumber() {
        return number;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    @Override
    public int compareTo(@NotNull Bus o) {
        return this.waitingTime - o.waitingTime;
    }

}
