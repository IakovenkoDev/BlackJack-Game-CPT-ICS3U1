import java.util.Scanner;

public class Card {
    CardFace cardFace;
    private Integer cardValue;
    String faceValue;
    CardSuit cardSuit;

    public Card(Integer cardIndex, CardSuit cardSuit) {
        this.cardSuit = cardSuit;
        this.cardFace = CardFace.values()[cardIndex];
        switch(this.cardFace) {
            case CardFace.Ace:
               this.cardValue = 11;
               this.faceValue = "A";
               break;
            case CardFace.King:
                this.cardValue = 10;
                this.faceValue = "K";
                break;
            case CardFace.Queen:
                this.cardValue = 10;
                this.faceValue = "Q";
                break;
            case CardFace.Jack:
                this.cardValue = 10;
                this.faceValue = "J";
                break;
            default:
                this.cardValue = cardIndex + 2;
                this.faceValue = this.cardValue.toString();
        }
    }

    public String toString() {
        return this.faceValue + this.getSuitValue();
    }

    public String getSuitValue() {
        switch (this.cardSuit){
            case Clubs:
               return "♣";
            case Hearts:
                return "❤";
            case Spades:
                return "♠";
            case Diamonds:
                return "♦";
        }
        return "?";
    }

    public Integer getCardValue() {
        return this.cardValue;
    }
}
