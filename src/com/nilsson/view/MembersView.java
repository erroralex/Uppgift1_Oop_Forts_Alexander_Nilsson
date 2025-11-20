package com.nilsson.view;

import com.nilsson.model.Member;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import java.util.ArrayList;
import java.util.List;

public class MembersView extends BorderPane {

    public TextField firstNameField = new TextField();
    public TextField lastNameField = new TextField();
    public TextField phoneField = new TextField();
    public TextField addressField = new TextField();

    public Button saveButton = new Button("Save");
    public Button backButton = new Button("Back");
    public Button removeButton = new Button("Remove Selected");

    public TableView<Member> tableView = new TableView<>();
    private ObservableList<Member> memberList;

    public MembersView() {

        // Top Bar
        Label header = new Label("Add Member");

        GridPane formGrid = new GridPane();
        formGrid.setHgap(15);
        formGrid.setVgap(10);

        GridPane.setHgrow(firstNameField, Priority.ALWAYS);
        GridPane.setHgrow(lastNameField, Priority.ALWAYS);
        GridPane.setHgrow(phoneField, Priority.ALWAYS);
        GridPane.setHgrow(addressField, Priority.ALWAYS);

        firstNameField.setPromptText("First Name");
        lastNameField.setPromptText("Last Name");
        phoneField.setPromptText("Phone");
        addressField.setPromptText("Address");

        formGrid.add(firstNameField, 0, 0);
        formGrid.add(lastNameField, 1, 0);
        formGrid.add(phoneField, 0, 1);
        formGrid.add(addressField, 1, 1);

        // --- Button Group for Save and Remove ---
        HBox buttonBar = new HBox(10, saveButton, removeButton);
        buttonBar.setAlignment(Pos.CENTER);

        HBox.setHgrow(saveButton, Priority.ALWAYS);
        HBox.setHgrow(removeButton, Priority.ALWAYS);
        removeButton.getStyleClass().add("red-button");

        VBox formCard = new VBox(20, header, formGrid, buttonBar);
        formCard.setAlignment(Pos.TOP_CENTER);
        formCard.getStyleClass().add("form-card");

        VBox formWrapper = new VBox(formCard);
        formWrapper.setAlignment(Pos.TOP_CENTER);
        formWrapper.setPadding(new Insets(20, 0, 20, 0));

        // --- TABLE SETUP ---
        TableColumn<Member, String> firstCol = new TableColumn<>("First Name");
        firstCol.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("firstName"));

        TableColumn<Member, String> lastCol = new TableColumn<>("Last Name");
        lastCol.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("lastName"));

        TableColumn<Member, String> phoneCol = new TableColumn<>("Phone");
        phoneCol.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("phone"));

        TableColumn<Member, String> addressCol = new TableColumn<>("Address");
        addressCol.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("address"));

        tableView.getColumns().addAll(firstCol, lastCol, phoneCol, addressCol);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableView.getStyleClass().add("table-view");

        // Bottom Bar
        BorderPane bottom = new BorderPane();
        backButton.getStyleClass().add("red-button");
        bottom.setLeft(backButton);
        bottom.setPadding(new Insets(10, 20, 10, 20));

        this.setTop(formWrapper);
        this.setCenter(tableView);
        this.setBottom(bottom);

        BorderPane.setMargin(tableView, new Insets(0, 20, 0, 20));

        this.memberList = FXCollections.observableArrayList(new ArrayList<>());
        tableView.setItems(this.memberList);
    }

    public void setMembers(List<Member> members) {
        this.memberList.setAll(members);
    }

    public void addMember(Member member) {
        this.memberList.add(member);
    }

    public void removeMember(Member member) {
        this.memberList.remove(member);
    }

    public void clearForm() {
        firstNameField.clear();
        lastNameField.clear();
        phoneField.clear();
        addressField.clear();
    }
}