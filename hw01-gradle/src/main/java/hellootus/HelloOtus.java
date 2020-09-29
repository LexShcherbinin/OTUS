package hellootus;

import static com.google.common.math.DoubleMath.factorial;

public class HelloOtus {
    public static void main(String[] args) {
        int number = 5;
        Double value = factorial(number);

        System.out.println("Hello, OTUS!");
        System.out.println("Factorial of " + number + " is " + value);
    }
}