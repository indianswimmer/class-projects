import java.util.Scanner;

/**
 * Created by Adi on 3/2/2016.
 */
public class BattleShipBoard {

    public int numShips;
    public int numMines;
    public String board [][];
    private boolean isVert;
    public int hitsRemaining;
    public int turns = 0;

    public static void main(String[] args) {
        //You can use this to test ship creation. It will print out a map of the board,
        //with their characters. I've used the ones in the instructions, with o for not near,
        // m for mine, and h for hit.
        /*
        int testR;
        int testC;
        Scanner input = new Scanner (System.in);
        System.out.print("Enter row: ");
        testR = input.nextInt();
        System.out.print("Enter column: ");
        testC = input.nextInt();

        BattleShipBoard test = new BattleShipBoard(testR, testC);
        for (int r = 0; r < test.board.length; r++) {
            for (int c = 0; c < test.board[r].length; c++) {
                System.out.print(test.board[r][c]);
            }System.out.println("");
        }*/
    }

    BattleShipBoard(int row, int col) {
        board = new String [row][col];

        //Designates number of ships and mines to be placed on board
        if (row*col >= 9 && row*col <= 16) {numShips = 1;numMines = 1;}
        else if (row*col >= 17 && row*col <= 36){numShips = 2;numMines = 2;}
        else if (row*col >= 36 && row*col <= 100) {numShips = 3;numMines = 3;}
        else {numShips = -1; numMines = -1;}
        hitsRemaining = 3*numShips;

        //Setup ships and near misses
        for (int i = 0; i < numShips; i++){
            isVert = (Math.random() < .5); //assigns vertical/horizontal orientation
            //since all ships are 1x3 or 3x1, I'm checking to see if the middle works
            boolean shipPlaced = false;
            int rowGuess;
            int colGuess;
            int count = 0;
            //Guesses a location for middle of ship, if that position won't work
            //throws exception and tries again
            while (!shipPlaced) {
                try {
                    rowGuess = (int) Math.floor((row * Math.random()));
                    colGuess = (int) Math.floor((row * Math.random()));

                    if (!isVert) {
                        for (int j = -1; j < 2; j++) {
                            if (board[rowGuess][colGuess + j] != null) {;throw new Exception();}
                        }
                        for (int j = -1; j < 2; j++) {board[rowGuess][colGuess + j] = "s";}
                    }
                    else if (isVert) {
                        for (int j = -1; j < 2; j++) {
                            if (board[rowGuess + j][colGuess] != null) {throw new Exception();}
                        }
                        for (int j = -1; j < 2; j++) {board[rowGuess + j][colGuess] = "s";}
                    }
                    shipPlaced = true;
                } catch (Exception e) {}
            }
        }
        //Setup mines
        for (int i = 0; i < numMines; i++) {
            boolean minePlaced = false;
            int rowGuess;
            int colGuess;
            //essentially a duplicate of the ship placing loop, this just has to check a single spot
            while (!minePlaced){
                try {
                    rowGuess = (int) Math.floor((row * Math.random()));
                    colGuess = (int) Math.floor((row * Math.random()));
                    if (board[rowGuess][colGuess] == "s"){throw new Exception();}
                    board[rowGuess][colGuess] = "m";
                    minePlaced = true;
                }catch (Exception e) {}

            }
        }
        //Setup misses
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                if (board[r][c] == null){
                    //I know this is messy, but I have to set up the board and avoid out of
                    //bounds exceptions
                    try{if (board[r-1][c] == "s") {board[r][c] = "a";continue;}}catch (Exception e){}
                    try{if (board[r+1][c] == "s") {board[r][c] = "a";continue;}}catch (Exception e){}
                    try{if (board[r][c-1] == "s") {board[r][c] = "a";continue;}}catch (Exception e){}
                    try{if (board[r][c+1] == "s") {board[r][c] = "a";continue;}}catch (Exception e){}

                    try{if (board[r-2][c] == "s") {board[r][c] = "c";continue;}}catch (Exception e){}
                    try{if (board[r+2][c] == "s") {board[r][c] = "c";continue;}}catch (Exception e){}
                    try{if (board[r][c-2] == "s") {board[r][c] = "c";continue;}}catch (Exception e){}
                    try{if (board[r][c+2] == "s") {board[r][c] = "c";continue;}}catch (Exception e){}

                    board[r][c] = "o";
                }
            }
        }
    }

    public void takeTurn () {
        System.out.println("------------------");
        Scanner input = new Scanner(System.in);
        int rowAttack = -1;
        int colAttack = -1;
        while (rowAttack < 0 || rowAttack > board.length){
            System.out.println("Input row attack coordinate: ");
            rowAttack = input.nextInt();
        }
        while (colAttack < 0 || colAttack > board[0].length){
            System.out.println("Input column attack coordinate: ");
            colAttack = input.nextInt();
        }
        turns ++;
        String value = board[rowAttack][colAttack];
        if (value == "s") {hitsRemaining -= 1; System.out.println("Hit!");}
        else if (value == "a") {System.out.println("A Miss, but Very Close");}
        else if (value == "c") {System.out.println("A Miss, but Close");}
        else if (value == "o") {System.out.println("A Miss");}
        else if (value == "m") {System.out.println("You hit a mine. Lose a turn."); turns ++;}
        else if (value == "h") {System.out.println("You already shot at this spot. Lose a turn"); turns ++;}
        board[rowAttack][colAttack] = "h";
        System.out.println("Your score: " + turns);
        if (turns > 25) {System.out.println("You kinda suck");}
        else if (turns > 50) {System.out.println("How did you screw up this bad?");}
        else if (turns == 666) {
            System.out.println("SATAN LAUGHS AT YOU HAHAHAHAHA");
            for (int r = 0; r < board.length; r++) {
                for (int c = 0; c < board[r].length; c++) {
                    board[r][c] = "m";
                    System.out.println("I would say you're screwed, but you done goofed enough that it doesn't matter");
                }
            }
        }
        else if (turns > 100) {System.out.println("Dovolis seriously needs new TAs.");}


    }
}
