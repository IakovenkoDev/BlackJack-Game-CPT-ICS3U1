import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        ArrayList<GameResult> gameResults = new ArrayList<GameResult>();
        do {
            GameRound game = new GameRound(getInitialBet());

            //GameRound round2 = new GameRound(2);
            CardDeck cardDeck = new CardDeck();
            cardDeck.shuffle();
            cardDeck.printDeck();
            PlayerHand hello = new PlayerHand();
            hello.addCard(cardDeck.drawCard());
            hello.addCard(cardDeck.drawCard());
            System.out.println(hello.toString());

        } while(promptContinueGame());
    }

    static double getInitialBet() {
        System.out.println("Enter how much you want to bet: ");
        return input.nextDouble();
    }

    static boolean promptContinueGame() {
        return false;
    }
    static void displayStatistics(ArrayList<GameResult> gameResults){

    }
}
