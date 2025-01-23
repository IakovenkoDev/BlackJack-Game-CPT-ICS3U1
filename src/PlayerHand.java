import java.util.ArrayList;

public class PlayerHand {
    private final ArrayList <Card> playerHand;
    public static final int BLACKJACK = 21;

    public PlayerHand(){
        this.playerHand = new ArrayList<>();
    }

    public void addCard(Card addedCard) {
        this.playerHand.add(addedCard);
    }

    public int playerHandTotal(){
        //TODO: calculate total based on cards in hand
        //1. loop through playerHand
        //count number of Aces and the total points from all other cards
        //2. if no Aces, return point total
        //if > 1 Ace, use 1 as the point value for Aces 2+
        //for the remaining 1 Ace, check if adding 11 will make the total points > BLACKJACK
        //If no, add 11, otherwise add 1
        int numAces = 0;
        int totalPoints = 0;
        // For every card in tge player hand, do the following (loop explanation)
        Card myAce = null;
        for (Card card : playerHand) {
            if (card.isAce()) {
                myAce = card;
                numAces ++;
            }
            else{
                totalPoints += card.getCardValue();
            }
        }

        if(numAces == 0){
            return totalPoints;
        }
        else if (numAces > 1){
            //add one point for every ace except the first one, because otherwise the total will exceed 21.
            totalPoints += numAces - 1;
        }

        if((totalPoints + myAce.getCardValue()) > BLACKJACK){
            totalPoints += 1;
        }
        else{
            totalPoints += myAce.getCardValue();
        }
        return totalPoints;
    }

    public boolean isBust(){
        return playerHandTotal() > BLACKJACK;
    }

    public boolean isBlackjack(){
        return playerHandTotal() == BLACKJACK && playerHand.size() == 2;
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
        hand += "\n" + "Hand total: " + playerHandTotal() + " points.";
        return hand;
    }
}
