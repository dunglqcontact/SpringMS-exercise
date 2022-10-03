package com.dunglq.cms.services.implement;

import com.dunglq.cms.entities.Card;
import com.dunglq.cms.exceptions.BaseException;
import com.dunglq.cms.repositories.CardRepository;
import com.dunglq.cms.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardServiceImplement  implements CardService {
    @Autowired
    private CardRepository cardRepository;

    @Override
    public List<Card> getCards() {
        return cardRepository.findAll();
    }

    @Override
    public Card getCardByCardId(long cardId) throws BaseException {
        return cardRepository.findById(cardId).orElseThrow(() ->
                new BaseException(1003, "Can't find User", HttpStatus.BAD_REQUEST));
    }
    @Override
    public List<Card> getCardByUserId(long userId) throws BaseException {
        return cardRepository.findByUserId(userId);
    }
}
