import java.util.Scanner;

public class CardDeck {
    Card[] cards;
    public static final int cardDeckSize = 52;
    private int topCardIndex;

    public CardDeck() {
        this.cards = new Card[CardDeck.cardDeckSize];
        this.topCardIndex = 0;
        for(int i = 0; i < this.cards.length; i++) {
            this.cards[i] = new Card(0, CardSuit.Clubs);
        }
        //use a loop to create the cards:
        //Loop 1: loop through the suits
        //Inner Loop 2: Loop through the cards in a suit (0-12) and pass to create a card
    }

    public void shuffle() {
        //TODO: shuffle the deck
    }

    public void printDeck() {
        System.out.println(this.toString());
    }

    public Card drawCard() {
        if(this.topCardIndex >= this.cards.length) {
            return null;
        }
        Card nextCard = this.cards[this.topCardIndex];
        this.topCardIndex ++;
        return nextCard;
    }

    public String toString() {
        String cardDeck = "";
        for (int i = 0; i < this.cards.length; i++){
            cardDeck += this.cards[i].toString() + "\n" ;
        }
        return cardDeck;
    }
}
