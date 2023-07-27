package com.giyong.community.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Member {
    @Id
    public String name;
    public int age;

    public Member(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
