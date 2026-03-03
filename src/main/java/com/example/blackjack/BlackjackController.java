package com.example.blackjack;

import com.example.blackjack.Vinnsla.GameModel;
import javafx.fxml.FXML;
import com.example.blackjack.Vinnsla.Player;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class BlackjackController {

    @FXML private Label fxMoneyLabel;
    @FXML private Label fxStatusLabel;
    private boolean fxDealerAnimating = false;

    private Timeline fxDealerTimeline;

    @FXML private Label fxPlayerHandLabel;
    @FXML private Label fxDealerHandLabel;

    @FXML private Label fxPlayerValueLabel;
    @FXML private Label fxDealerValueLabel;

    @FXML private TextField fxBetField;

    @FXML private Button fxHitBtn;
    @FXML private Button fxStandBtn;
    @FXML private Button fxDoubleBtn;
    @FXML private Button fxNewRoundBtn;

    private GameModel fxModel;

    @FXML
    private void initialize() {
        fxModel = new GameModel();
        fxStatusLabel.setText("");
        refreshUI();
    }

    @FXML
    private void onNewRound() {
        if (fxDealerTimeline != null) fxDealerTimeline.stop();
        fxDealerAnimating = false;

        int bet = parseBet();


        if (!fxModel.setBet(bet) || !fxModel.newRound()) {
            fxStatusLabel.setText("Invalid bet");
            refreshUI();
            return;
        }

        fxStatusLabel.setText("");
        refreshUI();
    }

    @FXML
    private void onHit() {
        fxModel.playerHit();
        refreshUI();
    }


    @FXML
    private void onStand() {


        if (fxModel.isRoundOver() || fxDealerAnimating) return;

        fxModel.playerStand();     // should NOT run dealer loop
        fxDealerAnimating = true;

        startDealerAnimation();
        refreshUI();



    }

    private void startDealerAnimation() {
        // If an old animation exists, stop it
        if (fxDealerTimeline != null) {
            fxDealerTimeline.stop();
        }
        fxDealerTimeline = new Timeline(
                new KeyFrame(Duration.seconds(0.8), e -> {
                    // Deal ONE card at a time if needed
                    if (fxModel.dealerNeedsHit()) {
                        fxModel.dealerHitOnce();
                        refreshUI(); // show the new card immediately
                    } else {
                        // Dealer is done -> end round once
                        fxDealerTimeline.stop();
                        fxModel.finishRound();
                        fxDealerAnimating = false;
                        refreshUI();
                    }
                })
        );
        fxDealerTimeline.setCycleCount(Timeline.INDEFINITE);
        fxDealerTimeline.play();
    }

    @FXML
    private void onDouble() {
        fxModel.playerDouble();
        refreshUI();
    }



    private int parseBet() {
        try {
            return Integer.parseInt(fxBetField.getText().trim());
        } catch (Exception e) {
            return -1;
        }
    }

    private void refreshUI() {
        fxMoneyLabel.setText(String.valueOf(fxModel.getPlayer().getMoney()));

        fxPlayerHandLabel.setText(handToString(fxModel.getPlayer(), false));
        fxPlayerValueLabel.setText(String.valueOf(fxModel.getPlayer().handValue()));

        boolean hideDealer = !fxModel.isRoundOver();
        fxDealerHandLabel.setText(handToString(fxModel.getDealer(), hideDealer));
        fxDealerValueLabel.setText(hideDealer ? "?" : String.valueOf(fxModel.getDealer().handValue()));

        if (fxModel.isRoundOver()) {
            fxStatusLabel.setText(fxModel.getOutcome().toString());
        }

        boolean roundOver = fxModel.isRoundOver();


        fxHitBtn.setDisable(roundOver || fxDealerAnimating);
        fxStandBtn.setDisable(roundOver || fxDealerAnimating);
        fxDoubleBtn.setDisable(roundOver || fxDealerAnimating || fxModel.getPlayer().getHand().size() != 2);
        fxNewRoundBtn.setDisable(fxDealerAnimating);
    }

    private String handToString(Player p, boolean hideFirst) {
        var hand = p.getHand();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < hand.size(); i++) {
            if (i == 0 && hideFirst) {
                sb.append("?? ");
            } else {
                sb.append(hand.get(i).toString()).append(" ");
            }
        }

        return sb.toString().trim();
    }
}