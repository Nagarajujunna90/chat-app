package com.nr.chatapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

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
    @JsonIgnore
    private String password;
    private String emailId;
    private String userName;
    @OneToMany(mappedBy = "user",targetEntity = Messages.class,fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Messages> messages;


}
