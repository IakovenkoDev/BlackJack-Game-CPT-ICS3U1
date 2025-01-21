import java.util.ArrayList;

public class PlayerHand {
    ArrayList <Card> playerHand;
    private int playerHandTotal;
    public static final int BLACKJACK = 21;

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
    public boolean isBust(){
        return playerHandTotal > BLACKJACK;
    }
    public boolean isBlackjack(){
        return playerHandTotal == BLACKJACK && playerHand.size() == 2;
    }

    public String getHiddenHand(){
        String hand = "";
        for (int i = 0; i < playerHand.size(); i++){
            if (i ==1){
                hand += "??  ";
            }
            else {
                hand += playerHand.get(i) + "  ";
            }
        }
        return hand;
    }

    public String toString(){
        String hand = "";
        for (int i = 0; i < playerHand.size(); i++){
            hand += playerHand.get(i) + "  ";
        }
        hand += "\n" + "The total number of points your hand holds is: " + playerHandTotal();
        return hand;
    }
}
