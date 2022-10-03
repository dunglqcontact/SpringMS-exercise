package com.dunglq.cms.repositories;

import com.dunglq.cms.entities.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CardRepository extends JpaRepository<Card,Long> {
    //@Query("FROM Card WHERE userId = ?1")
    List<Card> findByUserId(Long userId);
}
