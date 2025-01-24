import java.util.ArrayList;

 /* This is the PlayerHand class which deals with all operations related to the hand of a generic player meaning it could be the dealer or the user.
    Such operations include:
        - Adding a card to the player's hand
        - Calculating the hand total with Ace logic
        - Checking for Bust
        - Checking for Blackjack
        - System.output.printLn dealerHand with the getHiddenHand()
        - System.output.printLn playerHand with the toString() */

public class PlayerHand {
    private final ArrayList <Card> playerHand;
    public static final int BLACKJACK = 21;

    // Constructor initialising the instance of the playerHand
    public PlayerHand(){
        this.playerHand = new ArrayList<>();
    }

    // Method for adding a card to the player's hand
    public void addCard(Card addedCard) {
        this.playerHand.add(addedCard);
    }

    // Method that calculates the total number of points in a player's hand
    public int playerHandTotal(){
        /* The logic for this method works in the following way:
            1. For loop loops through the players hand
            2. Each iteration of the loop checks if the card is an Ace and counts how many aces are in the player's hand using an accumulator.
            3. Counts the number of total points of all cards that are not Aces
            4. If there are no Aces present, it returns the calculated point total
            5. If there is more than one Ace in the players hand, all Aces except for one are given point total of 1.
            6. For the remaining Ace
                a. If adding 11 points results in a bust (> BLACKJACK) then the remaining ace is given a value of one
                b. If adding 11 points doesn't result in a bust (< BLACKJACK) then the remaining ace is given a value of 11.  */

        int numAces = 0;
        int totalPoints = 0;
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

    // This method checks for a bust
    public boolean isBust(){
        return playerHandTotal() > BLACKJACK;
    }

    // This method checks for Blackjack
    public boolean isBlackjack(){
        return playerHandTotal() == BLACKJACK && playerHand.size() == 2;
    }

    // This method is responsible for printing out the hidden dealer hand
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

    // This method converts the hand to a string
    public String toString(){
        String hand = "";
        for (int i = 0; i < playerHand.size(); i++){
            hand += playerHand.get(i) + "  ";
        }
        hand += "\n" + "Hand total: " + playerHandTotal() + " points.";
        return hand;
    }
}
