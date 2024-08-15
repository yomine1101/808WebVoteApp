package com.example.webvoteapp.controller;

import com.example.webvoteapp.model.Vote;
import com.example.webvoteapp.model.VoteItem;
import com.example.webvoteapp.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/votes")
@CrossOrigin(origins = "http://localhost:8081")
public class VoteController {
    @Autowired
    private VoteService voteService;

    @GetMapping("/items")
    public List<VoteItem> getVoteItems() {
        return voteService.getVoteItemsWithCounts();
    }

    @PostMapping
    public ResponseEntity<?> createVote(@RequestBody Vote vote) {
        try {
//            System.out.println("Received voter: " + vote.getUsername());
//            System.out.println("Received vote item ID: " + (vote.getVoteItem() != null ? vote.getVoteItem().getId() : "null"));

            Vote savedVote = voteService.createVote(vote);
            return ResponseEntity.ok(savedVote);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save vote: " + e.getMessage());
        }
    }

}
