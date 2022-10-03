package com.dunglq.cms.services;

import com.dunglq.cms.entities.Card;
import com.dunglq.cms.exceptions.BaseException;
import com.fasterxml.jackson.databind.ser.Serializers;

import java.util.List;

public interface CardService {
    List<Card> getCards();
    Card getCardByCardId (long cardId) throws BaseException;
    List<Card> getCardByUserId (long userId) throws BaseException;
}
