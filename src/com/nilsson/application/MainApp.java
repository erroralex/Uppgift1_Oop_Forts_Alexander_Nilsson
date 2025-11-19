package com.nilsson.application;

import com.nilsson.controller.MembersController;
import com.nilsson.controller.StopwatchController;
import com.nilsson.view.MenuView;
import com.nilsson.view.MembersView;
import com.nilsson.view.StopwatchView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
        window.getIcons().add(new javafx.scene.image.Image(
                getClass().getResource("/com/nilsson/styling/icon.png").toExternalForm()
        ));
        window.setScene(menuScene);
        window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}