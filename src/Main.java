import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        ArrayList<GameResult> gameResults = new ArrayList<GameResult>();
        do {
            GameRound game = new GameRound(getInitialBet());

            //GameRound round2 = new GameRound(2);
            game.playGame();
            game.printGame();

        } while(promptContinueGame());
    }

    static double getInitialBet() {
        boolean correctInput = false;
        while (!correctInput) {
            System.out.println("Enter how much you want to bet: ");
            String inputStr = input.nextLine();
            try {
                double inputDouble = Double.parseDouble(inputStr);
                correctInput = true;
                return inputDouble;
            } catch (Exception e) {
                System.out.println("WTF");
            }
        }
        return 0;
    }

    static boolean promptContinueGame() {
        return false;
    }
    static void displayStatistics(ArrayList<GameResult> gameResults){

    }
}
