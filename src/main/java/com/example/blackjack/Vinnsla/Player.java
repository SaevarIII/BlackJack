package com.example.blackjack.Vinnsla;
import java.util.ArrayList;
import java.util.List;

public class Player {
    private final List<Card> hand = new ArrayList<>();
    private int money = 100;

    public void clearHand() {
        hand.clear();
    }

    public void receive(Card card) {
        hand.add(card);

    }

    public List<Card> getHand() {
        return List.copyOf(hand);
    }

    public int handValue() {
        int total = 0;
        int aceCount = 0;

        for (Card card : hand) {
            total += card.getValue();
            if (card.getRank() == Rank.ACE) {
                aceCount++;
            }

        }
        while (total > 21 && aceCount > 0) {
            total -= 10;
            aceCount--;
        }
        return total;

    }
    public int getMoney(){
        return money;
    }

    public void addMoney(int amount){
         money+=amount;
    }

    public void takeMoney(int amount ){
         money-=amount;
    }

    public boolean isBust() {
        return handValue() > 21;
    }


}
