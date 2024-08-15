package com.example.webvoteapp.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "vote_items")
@Getter
@Setter
public class VoteItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany(mappedBy = "voteItem", cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<Vote> votes;

    // 計算票數
    @Transient
    private int voteCount;

}
