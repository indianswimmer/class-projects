package project1;

public class PrimeInst {
	//Part 2: Primes Step 3
	// Place your methods in here
    int current;

    public PrimeInst(){
        int current = 1;
    }
    public boolean isPrime(int number) {
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
    public int getPrime() {
        while (true) {

            this.current ++;
            if (isPrime(current)) {
                return current;}
        }
    }

    public void reset(int n) {
        current = n - 1;
    }

    public int sumPrimes(int n) {
        int sum = 0;
        for (int i = 0; i < 3; i ++) {
            sum += getPrime();
        }
        return sum;
    }
}
