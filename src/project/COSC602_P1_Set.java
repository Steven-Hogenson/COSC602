package project;

/**
 * @author Steven Hogenson on 3/15/2022
 * @project StevenHogenson_Java
 */

import structure.MySet;

import java.util.ArrayList;
import java.util.List;
public class COSC602_P1_Set {
    public static void test() {
        System.out.println("COSC602 Project1 Set testing started ======================>>\n\n");
        MySet fibonacciNumSet = new MySet();
        MySet primeNumSet = new MySet();

        for (int i = 0; i < 30; i++) {
            fibonacciNumSet.add(fibonacciList().get(i));
            primeNumSet.add(primeList().get(i));
        }
        System.out.println("SET OF THE FIRST 30 (only 29 since 1 is duplicated) FIBONACCI NUMBERS:\n" + fibonacciNumSet);
        System.out.println("SET OF THE FIRST 30 POSITIVE PRIME NUMBERS:\n" + primeNumSet);

        System.out.println("INTERSECTION SET:\n" + fibonacciNumSet.intersection(primeNumSet));
        System.out.println("SYMMETRIC DIFFERENCE SET:\n" + fibonacciNumSet.symmetricDifference(primeNumSet));
        System.out.println("UNION SET:\n" + fibonacciNumSet.union(primeNumSet));

        System.out.println("\nCOSC602 Project1 Set testing finished ======================||");

    }

    /**
     * Generates first 30 fibonacci numbers
     *
     * @return list containing the first 30 fibonacci numbers
     */
    private static List<Integer> fibonacciList() {
        List<Integer> fib = new ArrayList<>();
        fib.add(0);
        fib.add(1);
        for (int i = 2; i < 30; i++) {
            fib.add(fib.get(i - 1) + fib.get(i - 2));
        }
        return fib;
    }

    /**
     * Generates first 30 positive prime numbers
     *
     * @return list containing first 30 positive prime numbers
     */
    private static List<Integer> primeList() {
        List<Integer> prime = new ArrayList<>();

        int i = 2;
        while (prime.size() < 30) {
            boolean isPrime = true;
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                prime.add(i);
            }
            i++;
        }
        return prime;

    }

}
