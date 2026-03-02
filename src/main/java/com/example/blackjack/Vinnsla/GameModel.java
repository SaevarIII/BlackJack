package com.example.blackjack.Vinnsla;

public class GameModel {
    private final Deck deck;
    private final Player player;
    private final Dealer dealer;
    private boolean roundOver = false;
    private Outcome outcome = Outcome.IN_PROGRESS;
    private int currentbet;

    public GameModel() {
        this.deck = new Deck();
        this.player = new Player();
        this.dealer = new Dealer();
    }

    public void setCurrentbet(int bet) {
        this.currentbet = bet;
    }

    public int getCurrentbet(){
        return currentbet;
    }

    public void newRound() {
        roundOver = false;
        outcome = outcome.IN_PROGRESS;
        player.clearHand();
        dealer.clearHand();
        deck.shuffle();

        player.receive(deck.dealCard());
        dealer.receive(deck.dealCard());
        player.receive(deck.dealCard());
        dealer.receive(deck.dealCard());
    }

    public void playerHit() {
        if (roundOver) return;
        player.receive(deck.dealCard());
        if(player.isBust())
            endRound();

    }

    public void playerStand() {
        if(roundOver) return;
        dealerTurn();
        endRound();
    }

    private void dealerTurn() {
        while (dealer.shouldHit()) {
            dealer.receive(deck.dealCard());
        }
    }
        public Player getPlayer() {
            return player;
        }

        public Dealer getDealer () {
            return dealer;
        }

        private void endRound(){
        if(roundOver) return;
        roundOver = true;
        outcome = whoWon();
        updateMoney();
        }

        private void updateMoney(){
            switch (outcome) {
                case PLAYER_WIN:
                    player.addMoney(currentbet);
                    break;
                case DEALER_WIN:
                    player.takeMoney(currentbet);
                    break;
                case PUSH:
                    break;
                default:
                    break;

            }

        }



        private Outcome whoWon(){
        if(player.isBust()){
            return Outcome.DEALER_WIN;
        }
        if(dealer.isBust()){
            return Outcome.PLAYER_WIN;
        }
        int playerValue = player.handValue();
        int dealerValue = dealer.handValue();

        if(playerValue > dealerValue){
            return Outcome.PLAYER_WIN;
        }
        if(playerValue < dealerValue){
            return Outcome.DEALER_WIN;
        }
            return Outcome.PUSH;
        }
    public boolean isRoundOver() {
        return roundOver;
    }
    public Outcome getOutcome() {
        return outcome;
    }


}