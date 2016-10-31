import java.util.Scanner;

/**
 * Created by Adi on 3/2/2016.
 */
public class Game {
    public static boolean gameOver = false;
    public static int bestScore = 99999;
    public static boolean playAgain = true;

    public static void main(String[] args) {
        while (playAgain) {
            Scanner input = new Scanner(System.in);
            int rows = -1;
            int cols = -1;
            while (rows < 3 || rows > 10) {
                System.out.println("Please enter row size (integer between 3 and 10): ");
                rows = input.nextInt();
            }
            while (cols < 3 || cols > 10) {
                System.out.println("Please enter column size (integer between 3 and 10): ");
                cols = input.nextInt();
            }
            BattleShipBoard gameBoard = new BattleShipBoard(rows, cols);

            //Uncomment this loop to see the board. [Debugging mode]
            /*for (int r = 0; r < gameBoard.board.length; r++) {
                for (int c = 0; c < gameBoard.board[r].length; c++) {
                    System.out.print(gameBoard.board[r][c]);
                }System.out.println("");

            }*/

            //At this point, the game is initialized and ready to be played
            System.out.println("------------------------");
            if (bestScore != 99999) {
                System.out.println("Current High Score: " + bestScore);
            } else {
                System.out.println("Current High Score: Not Set");
            }
            System.out.println("Upper left coordinates are 0,0");
            System.out.println("There are " + gameBoard.numShips + " ship(s) and " + gameBoard.numMines + " mines. Good luck!");

            //Game begins
            while (!gameOver) {
                gameBoard.takeTurn();
                if (gameBoard.hitsRemaining == 0) {gameOver = true;}
            }
            System.out.println("You found all the ships. Your score is: " + gameBoard.turns);
            if (gameBoard.turns < bestScore) {System.out.println("You got a high score!"); bestScore = gameBoard.turns;}
            System.out.println("Want to play again? 1 for yes, 2 for no.");
            if (input.nextInt() == 1) {playAgain = true;}
            else {playAgain = false;}

        }
    }
}
