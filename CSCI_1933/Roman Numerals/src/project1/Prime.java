package project1;

public class Prime {
    //Part 2: Primes
    //Place your methods in here
    public static int current = 1;

    public static void main(String[] args) {
        reset(1);
        System.out.println("Sum is: "+ sumPrimes(3));
    }

    public static boolean isPrime(int number) {
        int i = 2;
        if (number < 2) {
            return false;
        }
        while (i < number) {
            if ((number % i) == 0) {
                return false;
            }
            i += 1;
        }
        return true;
    }
    public static int getPrime() {
        while (true) {

            current ++;
            if (isPrime(current)) {
                return current;}
        }
    }

    public static void reset(int n) {
        current = n - 1;
    }

    public static int sumPrimes(int n) {
        int sum = 0;
        for (int i = 0; i < 3; i ++) {
            sum += getPrime();
        }
        return sum;
    }

}
