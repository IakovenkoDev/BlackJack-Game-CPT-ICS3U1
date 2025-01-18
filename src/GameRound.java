import java.util.Scanner;

public class GameRound {
    static Scanner input = new Scanner(System.in);
    public int initialBet;
    public CardDeck cardDeck;

    public GameRound(int initialBet) {
        this.initialBet = initialBet;
        this.cardDeck = new CardDeck();
        this.cardDeck.shuffle();
    }


}
