import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        do {
            //GameRound game = new GameRound(getInitialBet());
            //GameRound round2 = new GameRound(2);
            CardDeck cardDeck = new CardDeck();
            cardDeck.printDeck();
            PlayerHand hello = new PlayerHand();
            hello.addCard(cardDeck.drawCard());
            hello.addCard(cardDeck.drawCard());
            System.out.println(hello.toString());
        } while(promptContinueGame());
    }

    static int getInitialBet() {
        return 10;
    }

    static boolean promptContinueGame() {
        return false;
    }
}
