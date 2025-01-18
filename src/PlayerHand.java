import java.util.ArrayList;

public class PlayerHand {
    ArrayList <Card> playerHand;
    private int playerHandTotal;

    public PlayerHand(){
        this.playerHand = new ArrayList<Card>();
        this.playerHandTotal = 0;
    }

    public void addCard(Card addedCard) {
        this.playerHand.add(addedCard);
        playerHandTotal += addedCard.getCardValue();
    }
    public int playerHandTotal(){
        return playerHandTotal;
    }
    public boolean isBlackjack(){

        return false;
    }
    public String toString(){
        String hand = "";
        for (int i = 0; i < playerHand.size(); i++){
            hand += playerHand.get(i) + "  ";
        }
        hand += "\n" + playerHandTotal();
        return hand;
    }
}
