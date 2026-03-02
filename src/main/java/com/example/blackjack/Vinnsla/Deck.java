package com.example.blackjack.Vinnsla;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> cards;

    public Deck(){
        cards = new ArrayList<>();
        newDeck();
    }

    public void shuffle(){
        Collections.shuffle(cards);

    }
    public Card dealCard(){
        if(cards.isEmpty()){
            throw new IllegalStateException("Deck empty");
        }
        return cards.removeLast();
    }

    public int size(){
        return cards.size();
    }

    public void newDeck() {
        cards.clear();
        for(Suit suit : Suit.values()) {
            for(Rank rank: Rank.values()){
                cards.add(new Card(rank, suit));
            }
        }

    }
}
