package project1;

/**
 * Created by User on 2/13/2016.
 */
public class PrimeInstTest {
    PrimeInst p = new PrimeInst();
    PrimeInst p2 = new PrimeInst();

    public static void main(String[] args) {

        PrimeInst p = new PrimeInst();
        PrimeInst p2 = new PrimeInst();

        System.out.println("getPrime 1st run: p= " + p.getPrime() + ", p2 = " + p2.getPrime());
        p2.getPrime();
        p2.sumPrimes(4);
        System.out.println("getPrime 2nd run: p = " + p.getPrime() + ", p2= " + p2.getPrime());
    }
}
