package com.example.blackjack.Vinnsla;

public class Rules {
    public boolean hasBlackjack(Player p) {
        return p.getHand().size() == 2 && p.handValue() == 21;
    }
    public boolean playerHasNaturalBlackjack(Player player, Dealer dealer) {
        return hasBlackjack(player) && !hasBlackjack(dealer);
    }

    public boolean canDouble(Player player) {
        return player.getHand().size() == 2;
    }




    public int calculatePayout(Outcome outcome, boolean blackjack, int bet){
        if(blackjack){
            return  bet * 3 / 2;
        }
        if(outcome == Outcome.PLAYER_WIN){
            return bet;
        }
        if(outcome == Outcome.DEALER_WIN){
            return -bet;
        }
        return 0;
    }
}
