package com.example.webvoteapp.service;

import com.example.webvoteapp.model.Vote;
import com.example.webvoteapp.model.VoteItem;
import com.example.webvoteapp.repository.VoteItemRepository;
import com.example.webvoteapp.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoteService {

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private VoteItemRepository voteItemRepository;

    public List<VoteItem> getVoteItemsWithCounts() {
        List<VoteItem> voteItems = voteItemRepository.findAll();
        for (VoteItem item : voteItems) {
            int count = voteRepository.countByVoteItem(item);
            item.setVoteCount(count);
        }
        return voteItems;
    }

    public Vote createVote(Vote vote) {
        return voteRepository.save(vote);
    }

}
