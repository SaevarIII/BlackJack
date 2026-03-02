package com.example.blackjack.Vinnsla;

public class Dealer extends Player{
    public boolean shouldHit() {
        return handValue()<17;
    }
}
