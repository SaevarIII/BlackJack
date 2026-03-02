package com.example.blackjack;

import com.example.blackjack.Vinnsla.GameModel;
import javafx.fxml.FXML;

public class BlackjackController {

    // @FXML UI fields go here

    private GameModel model;

    @FXML
    private void initialize() {
        model = new GameModel();
        // refreshUI();
    }

    @FXML
    private void onHit() { }

    @FXML
    private void onStand() { }

    private void refreshUI() { }
}