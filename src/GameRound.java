import java.util.Scanner;

public class GameRound {
    static Scanner input = new Scanner(System.in);
    public double initialBet;
    public CardDeck cardDeck;
    public PlayerHand playerHand;
    public PlayerHand dealerHand;
    private boolean gameRunning;
    public static final String HIT = "H";
    public static final String STAND = "S";
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
        if(showAllCards == false){
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
            endGame(GameResult.Push);
        }
        else if(dealerHand.isBlackjack()){
            showCards(true);
            endGame(GameResult.Lose);
        }
        else if(playerHand.isBlackjack()){
            showCards(true);
            endGame(GameResult.PlayerBlackjack);
        }
    }

    private void playerTurn(){
        boolean correctInput = true;
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
        while(dealerHand.playerHandTotal() < 17){
            Card newCard = cardDeck.drawCard();
            dealerHand.addCard(newCard);
            System.out.println("Dealer drew a " + newCard);
            if (dealerHand.isBust()){
                System.out.println("The dealer BUSTED!");
                showCards(true);
                endGame(GameResult.Win);
            }
        }
    }

    private GameResult calculateResult(){
        //TODO

        return GameResult.Win;
    }
    private void endGame(GameResult result){
        System.out.println("The game has ended, final game calculations are taking place...");
        gameRunning = false;
        gameResult = result;
    }

    public double totalRecieved(){
        return 2.00;
    }

    public GameResult getGameResult(){
        return GameResult.Win;
    }

    public void printGame(){
        cardDeck.printDeck();
        System.out.println("Player hand: " + playerHand.toString());
        System.out.println("Dealer Hand: " + dealerHand.toString());
    }

}
