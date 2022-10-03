package com.dunglq.ums.controllers;


import com.dunglq.ums.services.CardRestTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class CardRestTemplateController {
    @Autowired
    private CardRestTemplateService restTemplateService;

    @GetMapping("api/v1/card-restemplate")
    public ResponseEntity<String> getAllCard(){
        return restTemplateService.getAllCard();
    }

    @GetMapping("api/v1/card-restemplate/{cardId}")
    public ResponseEntity<String> getCardByCardId(@PathVariable long cardId){
        return restTemplateService.getCardByCardId(cardId);
    }

    @GetMapping("api/v1/card-restemplate/user-card/{userId}")
    public ResponseEntity<String> getCardByUserId(@PathVariable long userId){
        return restTemplateService.getCardByUserId(userId);
    }
}
