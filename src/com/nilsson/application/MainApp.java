package com.nilsson.application;

import com.nilsson.controller.MembersController;
import com.nilsson.controller.StopwatchController;
import com.nilsson.view.MenuView;
import com.nilsson.view.MembersView;
import com.nilsson.view.StopwatchView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Uppgift: Medlemsformulär med tidtagarur (JavaFX, utan FXML)
 * <p>
 * Skapa en enkel JavaFX-applikation där användaren kan:
 * <p>
 * 1. Mata in uppgifter om en medlem
 * o Förnamn
 * o Efternamn
 * o Telefonnummer
 * o Adress
 * <p>
 * Visa fälten i en tydlig layout (t.ex. GridPane eller VBox) tillsammans med en knapp, t.ex.
 * ”Spara medlem”.
 * <p>
 * När knappen trycks ska uppgifterna:
 * o antingen skrivas ut i ett textfält/ruta, eller
 * o visas i en Label längst ner i fönstret (t.ex. ”Sparad: Tomas Wigell,
 * 0730972488, Svala 216 B, 86294, Njurunda”).
 * <p>
 * 2. Starta och stoppa ett tidtagarur
 * o Lägg till en Label som visar tiden, t.ex. i formatet 00:00:00
 * (timme:minut:sekund).
 * o Lägg till två knappar: ”Start” och ”Stop”.
 * o När användaren trycker på Start ska tidtagaruret börja räkna upp.
 * o När användaren trycker på Stop ska tidtagaruret stanna.
 * <p>
 * Krav:
 * • Allt ska göras i ren Java-kod (ingen FXML).
 */

public class MainApp extends Application {

    private Stage window;
    private Scene menuScene, membersScene, stopwatchScene;

    @Override
    public void start(Stage stage) {
        window = stage;

        // Menu
        MenuView menuView = new MenuView();
        menuScene = new Scene(menuView.getRoot(), 400, 350);

        // Members screen
        MembersView membersView = new MembersView();
        new MembersController(membersView);
        membersScene = new Scene(membersView, 750, 750);

        // Stopwatch screen
        StopwatchView stopwatchView = new StopwatchView();
        new StopwatchController(stopwatchView);
        stopwatchScene = new Scene(stopwatchView.getRoot(), 600, 550);

        // Apply CSS
        String css = getClass().getResource("/com/nilsson/styling/styles.css").toExternalForm();
        menuScene.getStylesheets().add(css);
        membersScene.getStylesheets().add(css);
        stopwatchScene.getStylesheets().add(css);

        // Menu buttons
        menuView.membersButton.setOnAction(e -> window.setScene(membersScene));
        menuView.timerButton.setOnAction(e -> window.setScene(stopwatchScene));
        menuView.exitButton.setOnAction(e -> window.close());

        // Back buttons
        membersView.backButton.setOnAction(e -> window.setScene(menuScene));
        stopwatchView.backButton.setOnAction(e -> window.setScene(menuScene));

        window.setTitle("Members & Stopwatch");
        window.getIcons().add(new javafx.scene.image.Image(getClass().getResource("/com/nilsson/styling/icon.png").toExternalForm()));
        window.setScene(menuScene);
        window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}