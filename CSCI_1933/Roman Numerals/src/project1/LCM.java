package project1;

public class LCM {
    //Part 3: Number with LCM
    //Note: As per the instructions, make sure this class is instantiable
    int number;

    public static void main(String[] args) {
        LCM test = new LCM(16);
        System.out.println(test.getGCD(12));
        System.out.println(test.getLCM(12));

    }

    public LCM(int num){
        number = num;
    }
    public int getLCM(int other) {return (other*number/getGCD(other));}

    public int getGCD (int other){
        int GCD = other;
        for (int i = GCD; i > 1; i -= 1) {
            if (number % i == 0 && other % i == 0) {return i;}
        }
        return GCD;
    }
}
