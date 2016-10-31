package project1;

public class RomanNumeral {
	//Part 1: Roman Numeral
	//place your methods in here

    public int number = 100;
    public String roman;
    public static String UNDEFINED = "-100";

    public static void main(String[] args) {
        /*
        RomanNumeral test = new RomanNumeral(2332);
        System.out.println(test.getNumber());
        System.out.println(test.getRoman());
        */
        //Main testing
    }

    public RomanNumeral (int integerValue) {
        //constructor
        if (integerValue > 2500 || integerValue <= 0){
            System.out.println("Value outside range.");
        }
        number = integerValue;
        roman = convertString(number);
    }

    public static String convertString(int num) {
        //takes an integer 1-2500 to it's roman numeral
        String rom = "";
        //What to do if number inputted is outside range
        if (num > 2500 || num <= 0) {return UNDEFINED;}
        else {return convertString1(num, rom);}
        }
    public static String convertString1(int num, String rom ) {
        //recursive function that finds the roman numeral version, tail recursion
        if (num >= 1000) {rom = rom + "M"; num-= 1000;}
        else if (num >= 900) {rom = rom + "CM"; num-= 900;}
        else if (num >= 500) {rom = rom + "D"; num -= 500;}
        else if (num >= 400) {rom = rom + "CD"; num -= 400;}
        else if (num >= 100){rom = rom + "C"; num -= 100;}
        else if (num >= 90){rom = rom + "XC"; num -= 90;}
        else if (num >= 50){rom = rom + "L"; num -= 50;}
        else if (num >= 40){rom = rom + "XL"; num -= 40;}
        else if (num >= 10){rom = rom + "X"; num -= 10;}
        else if (num >= 9){rom = rom + "IX"; num -= 9;}
        else if (num >= 5){rom = rom + "V"; num -= 5;}
        else if (num >= 4){rom = rom + "IV"; num -= 4;}
        else if (num >= 1){rom = rom + "I"; num -= 1;}
        else if (num == 0) {return rom;}
        return convertString1(num, rom);
    }

    public int compareTo (RomanNumeral r1){
        if (r1.getNumber() > number) {
            return -1;
        }
        else if (r1.getNumber() < number) {
            return 1;
        }
        return 0;
    }

    public String toString () {
        return convertString(number);
    }

    public int toInt(){
        return number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getRoman() {
        return roman;
    }

    public void setRoman(String roman) {
        this.roman = roman;
    }
}
