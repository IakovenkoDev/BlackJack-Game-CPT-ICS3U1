/* This is the card class which deals with all the things the card needs:
    - The card needs to be assembled
    - The card needs to know how it looks (CardFace + CardSuit)
    - card needs to know how many points it is (cardValue) */

public class Card {
    private final CardFace cardFace;
    private final Integer cardValue;
    private final String faceValue;
    private final CardSuit cardSuit;

    // This is the constructor, the constructor creates a new card instance. Display values and card values are also set in this constructor.
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

    // toString method converts the built card as a String.
    public String toString() {
        return this.faceValue + this.getSuitValue();
    }

    public boolean isAce(){
        return cardFace == CardFace.Ace;
    }

    // getSuitValue method converts the enum CardSuit into Strings so that information can be displayed to the user.
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

    // getCardValue method is a public method that allows other classes to get the card value.
    public Integer getCardValue() {
        return this.cardValue;
    }
}
