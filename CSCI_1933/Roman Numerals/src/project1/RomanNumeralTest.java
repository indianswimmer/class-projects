package project1;

/**
 * Created by User on 2/13/2016.
 */
public class RomanNumeralTest {

    public static void main(String[] args) {
        RomanNumeral r1 = new RomanNumeral(66);
        RomanNumeral r2 = new RomanNumeral(199);
        RomanNumeral r3 = new RomanNumeral(2499);
        r1.compareTo(r2);
        r3.compareTo(r1);
        r2.compareTo(r2);
        r1.toString();
        r2.toString();
        r3.toString();
        r1.toInt();
        r2.toInt();
        r3.toInt();
    }

}