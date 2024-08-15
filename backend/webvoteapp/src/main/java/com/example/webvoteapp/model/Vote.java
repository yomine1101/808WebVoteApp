package com.example.webvoteapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "votes")
@Getter
@Setter
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "voter")
    private String username;

    // FK
    @ManyToOne
    @JoinColumn(name = "vote_item_id")
    @JsonBackReference
    private VoteItem voteItem;

//    @Override
//    public String toString() {
//        return "Vote{id=" + id +
//                ", voter='" + username + '\'' +
//                ", voteItem=" + (voteItem != null ? voteItem.getId() : "null") +
//                '}';
//    }
}
