package com.example.webvoteapp.controller;

import com.example.webvoteapp.model.VoteItem;
import com.example.webvoteapp.service.VoteItemService;
import com.example.webvoteapp.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vote-items")
@CrossOrigin(origins = "http://localhost:8081")
public class VoteItemController {
    @Autowired
    private VoteItemService voteItemService;

    @Autowired
    private VoteService voteService;

    @GetMapping
    public List<VoteItem> getAllVoteItems() {
        return voteItemService.getAllVoteItems();
    }

    @GetMapping("/vote-counts")
    public List<VoteItem> getAllVoteItemsWithCounts() {
        return voteService.getVoteItemsWithCounts();
    }

    @PostMapping
    public ResponseEntity<String> createVoteItem(@RequestBody VoteItem voteItem) {
        try {
            voteItemService.createVoteItem(voteItem);
            return ResponseEntity.status(HttpStatus.CREATED).body("VoteItem created successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create vote item: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateVoteItem(@PathVariable int id, @RequestBody VoteItem voteItem) {
        try {
            voteItemService.updateVoteItem(id, voteItem.getName());
            return ResponseEntity.ok("VoteItem updated successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating VoteItem");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVoteItem(@PathVariable int id) {
        try {
            voteItemService.deleteVoteItem(id);
            return ResponseEntity.ok("Vote item deleted successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while deleting vote item");
        }
    }
}
