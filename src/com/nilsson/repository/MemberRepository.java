package com.nilsson.repository;

import com.nilsson.model.Member;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MemberRepository {

    private static final String FILE_PATH = "members.csv";

    public static void save(Member member) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(member.getFirstName() + "," + member.getLastName() + "," + member.getPhone() + "," + member.getAddress());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Member> loadAll() {
        List<Member> list = new ArrayList<>();
        File file = new File(FILE_PATH);
        if (!file.exists()) return list;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] t = line.split(",");
                if (t.length < 4) continue;
                list.add(new Member(t[0], t[1], t[2], t[3]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    public static void remove(Member memberToRemove) {
        List<Member> allMembers = loadAll();

        List<Member> updatedMembers = allMembers.stream().filter(m -> !m.equals(memberToRemove)).collect(Collectors.toList());

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, false))) {
            for (Member member : updatedMembers) {
                writer.write(member.getFirstName() + "," + member.getLastName() + "," + member.getPhone() + "," + member.getAddress());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}