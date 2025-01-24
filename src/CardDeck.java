import java.util.ArrayList;
import java.util.Collections;

/* This is the cardDeck class which essentially creates the deck and does actions with the deck such as:
        - Shuffling the deck (this was done manually instead of using the .shuffle() command to show understanding of concepts)
        - Converting the deck to a String to allow the user to keep the deck if they win or get a blackjack at the end of the round */

public class CardDeck {
    private Card[] cards;
    public static final int cardDeckSize = 52;
    private int topCardIndex;

    /* This is the constructor of the method which essentially creates the deck.
            - An outer for loop is used to loop through the suits (which get converted from an enum to an array) and attach a suit to a card
            - An inner for loop is used to loop through the FaceValues and attach a face value to a card
            - This created card is then saved in the cards array   */
    public CardDeck() {
        this.cards = new Card[CardDeck.cardDeckSize];
        this.topCardIndex = 0;
        CardSuit [] allsuits = CardSuit.values();
        int counter = 0;
        for(int i = 0; i < allsuits.length; i++) {
            CardSuit suit = allsuits[i];
            for (int j = 0; j < CardFace.values().length; j++) {
                this.cards[counter] = new Card(j, suit);
                counter ++;
            }
        }
    }

    /* This is the shuffle method that shuffles the cards after the card deck is created. It does this in the following way:
            - Converts the cardDeck into an ArrayList so that elements can be removed from the array.
            - Shuffling
                a. Gets a random index of a card from the ArrayList
                b. Gets the card from the unshuffled deck of that index
                c. Stores the card it got from the unshuffled deck in the shuffled deck at the smallest available index in the shuffled cards array.
                d. Removes the card it got from the unshuffled helper deck to prevent issues
             This repeats over and over again until 52 cards are present and then saves the shuffled card deck into the cards instance variable. */
    public void shuffle() {
        Card[] shuffledDeck = new Card[cardDeckSize];
        ArrayList<Card> shuffleHelperDeck = new ArrayList<>();
        Collections.addAll(shuffleHelperDeck, cards);
        for(int i = 0; i < cardDeckSize; i++){
            int randomIndex = (int)Math.floor(Math.random() * shuffleHelperDeck.size());
            shuffledDeck[i] = shuffleHelperDeck.get(randomIndex);
            shuffleHelperDeck.remove(randomIndex);
        }
        cards = shuffledDeck;
        topCardIndex = 0;
    }

    // method that allows other classes to print the deck if the user wants to keep it.
    public void printDeck() {
        System.out.print(this.toString());
    }

    // Gets a card from the card deck and returns it for use in the GameRound class.
    public Card drawCard() {
        if(this.topCardIndex >= this.cards.length) {
            return null;
        }
        Card nextCard = this.cards[this.topCardIndex];
        this.topCardIndex ++;
        return nextCard;
    }

    // This toString method traverses through the card deck array and prints out the card at each instance essentially printing out the deck. Used in the method printDeck()
    public String toString() {
        String cardDeck = "";
        for (int i = 0; i < this.cards.length; i++){
            cardDeck += this.cards[i].toString() + "\n";
        }
        return cardDeck;
    }
}
