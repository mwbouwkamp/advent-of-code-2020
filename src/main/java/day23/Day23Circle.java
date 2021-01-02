package day23;

public class Day23Circle {

    public static void main(String[] args) {
        Circle circle = new Circle("974618352");
        for (int i = 0; i < 10000000; i++) {
            circle.cycle();
            if (i % 1000 == 0) {
                System.out.println(i);
            }
        }
        System.out.println(circle.getResult());

        // 2147011073 too low

    }
}
