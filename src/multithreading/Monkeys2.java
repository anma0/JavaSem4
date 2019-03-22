package multithreading;

public class Monkeys2 {
    //Задание: использовать AtomicInteger вместо int для bananas, он позволяет за одно действие сделать проверку на 0 и вычесть 1.
    public static void main(String[] args) {
        new Monkeys2();
    }

    private int bananas = 1_000_000;

    //private int total = 0;
    private Monkeys2() {

        Object monitor = new Object();

        Runnable monkeyAction = () -> {
            int eaten = 0;

            while (bananas > 0) {

                synchronized (monitor) {
                    if (bananas > 0) {
                        bananas--;
                        eaten++;
                    }
                }
            }

            System.out.println(String.format("A monkey ate %,d bananas", eaten));
        };

        Thread monkey1 = new Thread(monkeyAction);
        Thread monkey2 = new Thread(monkeyAction);

        monkey1.start();
        monkey2.start();
    }
}
