package com.nilsson.repository;

import com.nilsson.model.Member;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MemberRepository {

    private static final String FILE_PATH = "members.json";

    private static final ObjectMapper MAPPER = new ObjectMapper();

    static {
        MAPPER.enable(SerializationFeature.INDENT_OUTPUT);
    }

    // Save member
    public static void save(Member member) {
        List<Member> allMembers = loadAll();
        allMembers.add(member);
        rewriteAll(allMembers);
    }

    // Load members
    public static List<Member> loadAll() {
        File dataFile = new File(FILE_PATH);

        // Check if the file exists and has content
        if (!dataFile.exists() || dataFile.length() == 0) {
            return new ArrayList<>();
        }

        try {
            Member[] membersArray = MAPPER.readValue(dataFile, Member[].class);
            return new ArrayList<>(Arrays.asList(membersArray));

        } catch (IOException e) {
            System.err.println("Error loading members from JSON file: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    // Remove member
    public static void remove(Member memberToRemove) {
        List<Member> allMembers = loadAll();
        List<Member> updatedMembers = allMembers.stream().filter(m -> !m.equals(memberToRemove)).collect(Collectors.toList());
        rewriteAll(updatedMembers);
    }

    // Rewrite
    private static void rewriteAll(List<Member> members) {
        File dataFile = new File(FILE_PATH);
        try {
            MAPPER.writeValue(dataFile, members);
        } catch (IOException e) {
            System.err.println("Error writing members to JSON file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}