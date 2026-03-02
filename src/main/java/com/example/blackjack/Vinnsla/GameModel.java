package com.example.blackjack.Vinnsla;

public class GameModel {
    private Deck deck;
    private final Player player;
    private final Dealer dealer;
    private boolean roundOver = false;
    private Outcome outcome = Outcome.IN_PROGRESS;
    private int currentBet;
    private final Rules rules = new Rules();

    public GameModel() {
        this.deck = new Deck();
        this.player = new Player();
        this.dealer = new Dealer();
    }



    public int getCurrentBet(){
        return currentBet;
    }

    public boolean newRound() {
        if(!validBet()){
            return false;
        }
        roundOver = false;
        outcome = Outcome.IN_PROGRESS;
        player.clearHand();
        dealer.clearHand();
        if(deck.size() < 4){
            deck.newDeck();
        }
        deck.shuffle();
        player.receive(deck.dealCard());
        dealer.receive(deck.dealCard());
        player.receive(deck.dealCard());
        dealer.receive(deck.dealCard());
        return true;

    }

    public boolean validBet(){
        return currentBet > 0 && currentBet <= player.getMoney();
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

    private void updateMoney() {
        boolean blackjack = rules.playerHasNaturalBlackjack(player,dealer);

        int delta = rules.calculatePayout(outcome, blackjack, currentBet);

        if (delta > 0) player.addMoney(delta);
        else if (delta < 0) player.takeMoney(-delta);
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


    public boolean setBet(int bet){
        if(bet >0 && player.getMoney()>= bet){
            this.currentBet = bet;
            return true;
        }
        return false;
    }


    public void playerDouble(){
        if(roundOver) return;
        if(!rules.canDouble(player)) return;
        if(player.getMoney() < currentBet) return;
        currentBet *=2;
        player.receive(deck.dealCard());
        if(player.isBust()){
            endRound();
            return;
        }
        dealerTurn();
        endRound();
    }





}