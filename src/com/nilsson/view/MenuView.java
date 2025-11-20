package com.nilsson.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class MenuView {

    private final VBox root = new VBox(25);
    public Button membersButton = new Button("Members");
    public Button timerButton = new Button("Timer");
    public Button exitButton = new Button("Exit");

    public MenuView() {
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(40));

        Label header = new Label("Main Menu");
        header.getStyleClass().add("menu-title");

        membersButton.setMaxWidth(220);
        membersButton.setPrefHeight(45);
        timerButton.setMaxWidth(220);
        timerButton.setPrefHeight(45);
        exitButton.setMaxWidth(220);
        exitButton.setPrefHeight(45);

        exitButton.getStyleClass().add("red-button");

        root.getChildren().addAll(header, membersButton, timerButton, exitButton);
    }

    public VBox getRoot() {
        return root;
    }
}