package com.nr.chatapp.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "user_tbl")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String language;
    private Integer age;
    private String country;
    private String gender;
    private String password;
    private String emailId;
}
