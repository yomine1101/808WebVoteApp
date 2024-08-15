package com.example.webvoteapp.repository;

import com.example.webvoteapp.model.VoteItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteItemRepository extends JpaRepository<VoteItem, Integer> {

    @Query(value = "CALL GetAllVoteItems()", nativeQuery = true)
    List<VoteItem> getAllVoteItems();

    @Modifying
    @Query(value = "CALL InsertVoteItem(:p_name)", nativeQuery = true)
    void insertVoteItem(@Param("p_name") String name);

    @Modifying
    @Query(value = "CALL UpdateVoteItem(:p_id, :p_name)", nativeQuery = true)
    void updateVoteItem(@Param("p_id") int id, @Param("p_name") String name);

    @Modifying
    @Query(value = "CALL DeleteVoteItem(:p_id)", nativeQuery = true)
    void deleteVoteItem(@Param("p_id") int id);
}
