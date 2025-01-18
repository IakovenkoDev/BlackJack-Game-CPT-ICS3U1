import java.util.Scanner;

public class GameRound {
    static Scanner input = new Scanner(System.in);
    public double initialBet;
    public CardDeck cardDeck;
    public PlayerHand playerHand;
    public PlayerHand dealerHand;

    public GameRound(double initialBet) {
        this.initialBet = initialBet;
        this.cardDeck = new CardDeck();
        this.cardDeck.shuffle();
        this.playerHand = new PlayerHand();
        this.dealerHand = new PlayerHand();
    }

    public void playGame(){

    }

    public double totalRecieved(){
        return 2.00;
    }

    public GameResult getGameResult(){
        return GameResult.Win;
    }

}
