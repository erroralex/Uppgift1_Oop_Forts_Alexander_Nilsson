package com.nilsson.model;

import java.util.Objects;

public class Member {

    private String firstName;
    private String lastName;
    private String phone;
    private String address;

    public Member() {

    }

    public Member(String firstName, String lastName, String phone, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;

        return Objects.equals(firstName, member.firstName) && Objects.equals(lastName, member.lastName)
                && Objects.equals(phone, member.phone) && Objects.equals(address, member.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, phone, address);
    }
}