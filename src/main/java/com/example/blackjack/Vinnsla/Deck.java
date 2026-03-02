package com.example.blackjack.Vinnsla;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> cards;

    public Deck(){
        for(Suit suit : Suit.values()) {
            for(Rank rank: Rank.values()){
                cards.add(new Card(rank, suit));
            }
        }
    }

    public void shuffle(){
        Collections.shuffle(cards);

    }
    public Card dealCard(){
        if(cards.isEmpty()){
            throw new IllegalStateException("Deck empty");
        }
        return cards.remove(cards.size() - 1);
    }

    public int size(){
        return cards.size();
    }
}
