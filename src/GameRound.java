import java.util.Scanner;

public class GameRound {
    private static final Scanner input = new Scanner(System.in);
    private final double initialBet;
    private final CardDeck cardDeck;
    private final PlayerHand playerHand;
    private final PlayerHand dealerHand;
    private boolean gameRunning;
    private static final String HIT = "H";
    private static final String STAND = "S";
    private GameResult gameResult = null;

    public GameRound(double initialBet) {
        this.initialBet = initialBet;
        this.cardDeck = new CardDeck();
        this.cardDeck.shuffle();
        this.playerHand = new PlayerHand();
        this.dealerHand = new PlayerHand();
        this.gameRunning = false;
    }

    public void playGame(){
        gameRunning = true;
        initialHandout();
        checkForBlackjack();
        while(gameRunning) {
            playerTurn();
        }
        if(gameResult == null) {
            dealerTurn();
        }
        if(gameResult == null) {
            endGame(calculateResult());
        }
    }

    private void initialHandout(){
        for (int i = 0; i < 2; i++) {
            playerHand.addCard(cardDeck.drawCard());
            dealerHand.addCard(cardDeck.drawCard());
        }
    }

    private void showCards(boolean showAllCards){
        if(!showAllCards){
            System.out.println("These are the dealer's cards: " + dealerHand.getHiddenHand());
        }
        else {
            System.out.println("These are the dealer's cards: " + dealerHand.toString());
        }
        System.out.println("These are your cards: \n" + playerHand.toString());
    }

    private void checkForBlackjack(){
        if(dealerHand.isBlackjack() && playerHand.isBlackjack()){
            showCards(true);
            System.out.println("Both you and the dealer had a blackjack!");
            endGame(GameResult.Push);

        }
        else if(dealerHand.isBlackjack()){
            showCards(true);
            System.out.println("Dealer had a blackjack!");
            endGame(GameResult.Lose);

        }
        else if(playerHand.isBlackjack()){
            showCards(true);
            System.out.println("!!!!!!!!!!!Blackjack!!!!!!!!!!!");
            endGame(GameResult.PlayerBlackjack);
        }
    }

    private void playerTurn(){
        boolean correctInput;
        showCards(false);
        System.out.println("\nYou may proceed to enter your action: \n1.\t If you want to HIT press the [H] key \n2. \t If you want to STAND press the [S] key");
        do {
            String playerInput = input.nextLine();
            switch (playerInput) {
                case HIT:
                    hit();
                    correctInput = true;
                    break;
                case STAND:
                    stand();
                    correctInput = true;
                    break;
                default:
                    System.out.println("Instruction were not followed correctly please try again!");
                    correctInput = false;
            }
        } while(!correctInput);
    }

    private void hit(){
        Card newCard = cardDeck.drawCard();
        playerHand.addCard(newCard);
        System.out.println("Your new card is: " + newCard.toString() + "\n");
        if (playerHand.isBust()){
            System.out.println(playerHand.toString());
            System.out.println("You have BUSTED!");
            endGame(GameResult.Bust);
        }
    }

    private void stand(){
        System.out.println("Turn complete, it is now the dealer's turn...");
        gameRunning = false;
    }

    private void dealerTurn(){
        while (dealerHand.playerHandTotal() < 17) {
            Card newCard = cardDeck.drawCard();
            dealerHand.addCard(newCard);
            System.out.println("Dealer drew a " + newCard);
            System.out.println("These are the dealer's cards: " + dealerHand.getHiddenHand() + "\n");
            if (dealerHand.isBust()) {
                System.out.println("The dealer BUSTED!");
                System.out.println(dealerHand.toString() + "\n");
                endGame(GameResult.Win);
                return;
            }
        }
        System.out.println("Dealer does not need to take any more cards.");
        System.out.println("These are the dealer's cards: " + dealerHand.toString() + "\n");
    }

    private GameResult calculateResult(){
        if (playerHand.playerHandTotal() > dealerHand.playerHandTotal()){
            return GameResult.Win;
        }
        else if(playerHand.playerHandTotal() < dealerHand.playerHandTotal()){
            return GameResult.Lose;
        }
        else if(playerHand.playerHandTotal() == dealerHand.playerHandTotal()){
            return GameResult.Push;
        }
        return null;
    }

    private void endGame(GameResult result){
        System.out.println("The game has ended, final game calculations are taking place...\n");
        gameRunning = false;
        gameResult = result;
        String totalReceived = String.format("%.2f", totalReceived());
        showCards(true);
        System.out.println(" ");
        switch (gameResult){
            case Win:
                System.out.println("Congratulations you won the game!!!");
                System.out.println("You won: $" + totalReceived);
                System.out.println("Your bet was returned.");
                promptKeepDeck();
                break;
            case Lose:
            case Bust:
                System.out.println("You lost: $" + String.format("%.2f", totalReceived() * -1));
                System.out.println("Your bet was lost.");
                break;
            case PlayerBlackjack:
                System.out.println("Congratulations, you had a BLACKJACK!!!!!!\nYou will now receive special compensation:\n");
                System.out.println("Special Bonus: $" + totalReceived);
                System.out.println("Your bet was returned.");
                promptKeepDeck();
                break;
            case Push:
                System.out.println("You have a Push (tie) with the dealer.");
                System.out.println("Your bet was returned.");
                break;
        }
    }

    public double totalReceived(){
        switch (gameResult){
            case PlayerBlackjack:
                return (initialBet/2) * 3;
            case Win:
                return initialBet;
            case Lose:
            case Bust:
                return -1 * initialBet;
            case Push:
                return 0;
        }
        return 0;
    }

    public GameResult getGameResult(){
        return gameResult;
    }

    // Test method
    public void printGame() {
        cardDeck.printDeck();
        System.out.println("Player hand: " + playerHand.toString());
        System.out.println("Dealer Hand: " + dealerHand.toString());
    }

    private void promptKeepDeck() {
        System.out.println("Congratulations!! Do you want to keep the lucky card deck?");
        System.out.println("1. \t For YES [Y]. \n2. \t Any other key to continue.");
        String inputStr = input.nextLine();
        if(inputStr.equals("Y")) {
            cardDeck.printDeck();
        }
    }

}
