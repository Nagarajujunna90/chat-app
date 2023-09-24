package com.nr.chatapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@Entity
@Table(name="message")
@NoArgsConstructor
@AllArgsConstructor
public class Messages {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String message;
    private MessageTypes messageType;
    private LocalDateTime localDateTime;
    private boolean isSeen;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "receivedUser")
    @JsonBackReference
    private User user;
    private String sender;


}
