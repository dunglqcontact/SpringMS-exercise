package com.dunglq.cms.controllers;


import com.dunglq.cms.dto.ResponseDTO;
import com.dunglq.cms.entities.Card;
import com.dunglq.cms.exceptions.BaseException;
import com.dunglq.cms.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@ResponseBody
public class CardController {
    @Autowired
    private CardService cardService;
    @GetMapping("api/v1/cards")
    public ResponseEntity<ResponseDTO> getCards() {
        ResponseDTO responseDTO = ResponseDTO.success();
        List<Card> cards = cardService.getCards();
        responseDTO.setData(cards);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("api/v1/cards/{cardId}")
    public ResponseEntity<ResponseDTO> getCardById(@PathVariable long cardId) throws BaseException {
        ResponseDTO responseDTO = ResponseDTO.success();
        Card card = cardService.getCardByCardId(cardId);
        responseDTO.setData(card);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("api/v1/cards/user-card/{userId}")
    public ResponseEntity<ResponseDTO> getCardByUserId(@PathVariable long userId) throws BaseException {
        ResponseDTO responseDTO = ResponseDTO.success();
        List<Card> card = cardService.getCardByUserId(userId);
        responseDTO.setData(card);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
