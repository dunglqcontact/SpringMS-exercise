package com.dunglq.ums.services;

import org.springframework.http.ResponseEntity;

public interface CardRestTemplateService {
    ResponseEntity<String> getAllCard();
    public ResponseEntity<String> getCardByCardId(long cardId);
    ResponseEntity<String> getCardByUserId(long userId);
}
