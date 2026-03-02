package com.example.blackjack.Vinnsla;

public class GameModel {
    private final Deck deck;
    private final Player player;
    private final Dealer dealer;

    public GameModel() {
        this.deck = new Deck();
        this.player = new Player();
        this.dealer = new Dealer();
    }

    public void newRound() { }

    public void playerHit() { }

    public void playerStand() { }

    private void dealerTurn() { }

    public Player getPlayer() { return player; }

    public Dealer getDealer() { return dealer; }
}