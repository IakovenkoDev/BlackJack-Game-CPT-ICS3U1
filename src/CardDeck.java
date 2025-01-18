import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class CardDeck {
    Card[] cards;
    public static final int cardDeckSize = 52;
    private int topCardIndex;

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

    public void shuffle() {
        Card[] shuffledDeck = new Card[cardDeckSize];
        ArrayList<Card> shuffleHelperDeck = new ArrayList<Card>();
        Collections.addAll(shuffleHelperDeck, cards);
        for(int i = 0; i < cardDeckSize; i++){
            int randomIndex = (int)Math.floor(Math.random() * shuffleHelperDeck.size());
            shuffledDeck[i] = shuffleHelperDeck.get(randomIndex);
            shuffleHelperDeck.remove(randomIndex);
        }
        cards = shuffledDeck;
        topCardIndex = 0;
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
