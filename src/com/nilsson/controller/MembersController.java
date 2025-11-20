package com.nilsson.controller;

import com.nilsson.model.Member;
import com.nilsson.repository.MemberRepository;
import com.nilsson.view.MembersView;
import javafx.beans.binding.BooleanBinding;
import javafx.scene.control.SelectionModel;

public class MembersController {

    public MembersController(MembersView view) {

        // Load existing members from CSV
        view.setMembers(MemberRepository.loadAll());

        BooleanBinding isFormIncomplete = view.firstNameField.textProperty().isEmpty().or(view.lastNameField.textProperty().isEmpty()).or(view.phoneField.textProperty().isEmpty()).or(view.addressField.textProperty().isEmpty());

        view.saveButton.disableProperty().bind(isFormIncomplete);

        view.saveButton.setOnAction(e -> {
            Member member = new Member(view.firstNameField.getText(), view.lastNameField.getText(), view.phoneField.getText(), view.addressField.getText());
            MemberRepository.save(member);
            view.addMember(member);
            view.clearForm();
            System.out.println("Member added and saved: " + member.getFirstName() + " " + member.getLastName());
        });
        view.saveButton.setDefaultButton(true);

        view.removeButton.setOnAction(e -> {
            SelectionModel<Member> selectionModel = view.tableView.getSelectionModel();
            Member selectedMember = selectionModel.getSelectedItem();

            if (selectedMember != null) {
                MemberRepository.remove(selectedMember);

                view.removeMember(selectedMember);
                System.out.println("Member removed: " + selectedMember.getFirstName() + " " + selectedMember.getLastName());

            } else {
                System.out.println("Please select a member to remove.");
            }
        });
    }
}