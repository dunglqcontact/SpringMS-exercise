package com.dunglq.ums.services.implement;

import com.dunglq.ums.services.CardRestTemplateService;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Date;

@Service
public class CardRestTemplateServiceImplement implements CardRestTemplateService {

    RestTemplate restTemplate = new RestTemplate();
    private static String  account_Url = "http://localhost:8888/api/v1/cards";

    @Override
    public ResponseEntity<String> getAllCard() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        ResponseEntity<String> response = response = restTemplate.exchange(account_Url, HttpMethod.GET,entity, String.class);
        return response;
    }

    @Override
    public ResponseEntity<String> getCardByCardId(long cardId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        String newURL = account_Url + "/" + cardId;;
        ResponseEntity<String> response = response = restTemplate.exchange(newURL, HttpMethod.GET,entity, String.class);
        return response;
    }

    @Override
    public ResponseEntity<String> getCardByUserId(long userId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        String newURL = account_Url + "/user-card/" + userId;
        ResponseEntity<String> response = response = restTemplate.exchange(newURL, HttpMethod.GET,entity, String.class);
        return response;
    }


}
