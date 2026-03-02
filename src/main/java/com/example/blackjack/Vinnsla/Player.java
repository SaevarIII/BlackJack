package com.example.blackjack.Vinnsla;
import java.util.ArrayList;
import java.util.List;

public class Player {
    protected final List<Card> hand = new ArrayList<>();

    public void clearHand() {
        hand.clear();
    }
    public void receive(Card card){
        hand.add(card);

    }
    public List<Card> getHand() {
        return hand;
    }
    public int handValue(){
        return 0;
    }
    public boolean isBust() {
        return false;
    }
}
