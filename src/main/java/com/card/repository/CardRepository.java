package com.card.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.card.models.CardModel;

public interface CardRepository extends JpaRepository<CardModel, Long>{
    
}
