package com.example.webvoteapp.service;

import com.example.webvoteapp.model.VoteItem;
import com.example.webvoteapp.repository.VoteItemRepository;
import com.example.webvoteapp.repository.VoteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class VoteItemService {
    @Autowired
    private VoteItemRepository voteItemRepository;
    @Autowired
    private VoteRepository voteRepository;

    public List<VoteItem> getAllVoteItems() {
        try {
            return voteItemRepository.getAllVoteItems();
        } catch (DataAccessException e) {
            throw new RuntimeException("Database error occurred while retrieving vote items", e);
        }
    }

    public void createVoteItem(VoteItem voteItem) {
        try {
            voteItemRepository.insertVoteItem(voteItem.getName());
        } catch (DataAccessException e) {
            throw new RuntimeException("Database error occurred while creating vote item", e);
        }
    }

    public void updateVoteItem(int id, String name) {
        try {
            voteItemRepository.updateVoteItem(id, name);
        } catch (DataAccessException e) {
            throw new RuntimeException("Database error occurred while updating vote item", e);
        }
    }
    public void deleteVoteItem(int id) {
        try {
            voteRepository.deleteByVoteItemId(id);
            voteItemRepository.deleteVoteItem(id);
        } catch (DataAccessException e) {
            throw new RuntimeException("Database error occurred while deleting vote item", e);
        }
    }
}
