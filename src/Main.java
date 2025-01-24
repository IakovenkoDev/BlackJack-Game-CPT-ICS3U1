import java.util.ArrayList;
import java.util.Scanner;

// Main class --> take everything together and runs the game
public class Main {
    private static final Scanner input = new Scanner(System.in);
    private static double playerMoney;
    private static final double  maxBetPossible = 10000000.00;

    public static void main(String[] args) {
        ArrayList<GameResult> gameResults = new ArrayList<>();
        System.out.println("Welcome to Blackjack! Please make sure you are aware of the rules before playing. Rules can be found at: https://docs.google.com/document/d/17PGTygHBYQAtfCN8Jyo0l2eMeb0-ThB47iaNVS8qvHI/edit?tab=t.0");
        double originalMoney = getPlayerMoneyInput();
        playerMoney = originalMoney;
        System.out.println("The game is now starting...");
        // while loop allows for continuous gameplay until you lose all your money and are forced to leave or you decide to leave.
        do {
            GameRound game = new GameRound(getInitialBet());
            game.playGame();
            gameResults.add(game.getGameResult());
            playerMoney += game.totalReceived();
            printAccountValue();
        } while(promptContinueGame());
        displayStatistics(gameResults);
        displayWinnings(playerMoney - originalMoney);
        displayMoneyOnExit();
        System.out.println("Thank you for playing Blackjack!");
        System.out.println("Hope to see you again soon");
    }

    // Method that formats player money into a string with a rounded money amount
    static String getPlayerMoneyFormatted() {
        return "$" + String.format("%.2f", playerMoney);
    }

    // Method that prints the account value in formatted player money form
    static void printAccountValue() {
        System.out.println("\nYour account balance is: " + getPlayerMoneyFormatted());
    }

    // Method that gets initial bet, manages incorrect player inputs to prevent crashes
    static double getInitialBet() {
        printAccountValue();
        while (true) {
            System.out.println("Enter how much you want to bet: ");
            String inputStr = input.nextLine();
            try {
                double inputDouble = Double.parseDouble(inputStr);
                if (inputDouble > 0) {
                    if (inputDouble > playerMoney) {
                        System.out.println("Whoops, you don't have enough money to make this bet! You have " + getPlayerMoneyFormatted());
                    } else {
                        return ((double)Math.round(inputDouble * 100))/100;
                    }
                } else if (inputDouble == 0) {
                    System.out.println("You can't bet nothing. Sorry, betting doesn't work this way :)");
                } else {
                    System.out.println("You can't bet a negative number! That would mean I'm paying you to play :)");
                }
            } catch (Exception e) {
                System.out.println("Incorrect input. Please try again and enter a number this time!");
            }
        }
    }

    // Method that gets the amount of money the player essentially "brings" to the casino, manages incorrect player inputs to prevent crashes
    static double getPlayerMoneyInput() {
        while (true) {
            System.out.println("How much money are you playing with?");
            String inputStr = input.nextLine();
            try {
                double inputDouble = Double.parseDouble(inputStr);
                if(inputDouble > maxBetPossible){
                    System.out.println("This casino only accepts bets upto a maximum of $" + String.format("%.2f", maxBetPossible) + ". Please enter a bet that is less.");
                } else if (inputDouble > 0) {
                    return ((double)Math.round(inputDouble * 100))/100;
                } else {
                    System.out.println("Please enter a positive number to start the game.");
                }
            } catch (Exception e) {
                System.out.println("Incorrect input. Please try again and enter a number this time!");
            }
        }
    }

    // Method that asks the player if they would like to play a new round. If the player has no more money they are booted from the "casino"
    static boolean promptContinueGame() {
        if(playerMoney == 0) {
            System.out.println("You gambled away all your money! You were kicked out of the casino :(");
            return false;
        }
        do {
            System.out.println("Do you want to start a new round and continue the game?\n1. \t For YES [Y] \n2. \t For NO [N]");
            String playerInput = input.nextLine();
            switch (playerInput) {
                case "Y":
                    return true;
                case "N":
                    return false;
                default:
                    System.out.println("Incorrect input, please read the instruction carefully!");
            }
        } while (true);
    }

    // Method that displays the game stats such as % games won, number of games played and number of games won
    static void displayStatistics(ArrayList<GameResult> gameResults) {
        int numGamesWon = 0;
        System.out.println("Total games played: " + gameResults.size());
        for (int i = 0; i < gameResults.size(); i++) {
            if (gameResults.get(i) == GameResult.Win || gameResults.get(i) == GameResult.PlayerBlackjack){
                numGamesWon += 1;
            }
        }
        System.out.println("Total number of games won: " + numGamesWon);
        double winPercentage = ((double)numGamesWon/gameResults.size()) * 100;
        System.out.println("The win percentage is: " + String.format("%.2f", winPercentage) + "%");
    }

    // Method that displays how much was lost/won, also endgame stats.
    static void displayWinnings(double netWinnings) {
        if (netWinnings > 0) {
            System.out.println("Total won: $" + String.format("%.2f", netWinnings));
        } else {
            System.out.println("Total lost: $" + String.format("%.2f", netWinnings * -1));
        }
    }

    // Displays the amount of money the user has left
    static void displayMoneyOnExit() {
        if (playerMoney > 0) {
            System.out.println("You left the casino with: $" + getPlayerMoneyFormatted());
        }
    }
}
