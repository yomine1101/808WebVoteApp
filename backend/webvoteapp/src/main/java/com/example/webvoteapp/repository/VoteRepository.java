package com.example.webvoteapp.repository;

import com.example.webvoteapp.model.Vote;
import com.example.webvoteapp.model.VoteItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VoteRepository extends JpaRepository<Vote, Integer> {

    int countByVoteItem(VoteItem voteItem);

    // delete and remote all vote record associated with the VoteItem ID
    void deleteByVoteItemId(int voteItemId);

}
