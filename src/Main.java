import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        do {
            //GameRound game = new GameRound(getInitialBet());
            //GameRound round2 = new GameRound(2);
            CardDeck cardDeck = new CardDeck();
            System.out.println(cardDeck.toString());
        } while(promptContinueGame());
    }

    static int getInitialBet() {
        return 10;
    }

    static boolean promptContinueGame() {
        return false;
    }
}
