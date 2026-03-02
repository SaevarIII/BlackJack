package com.example.blackjack;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("BlackJack.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);

        var css = HelloApplication.class.getResource("/css/Layout.css");
        if (css == null) throw new IllegalStateException("Missing /css/Layout.css");
        scene.getStylesheets().add(css.toExternalForm());

        stage.setScene(scene);
        stage.show();
    }

}

