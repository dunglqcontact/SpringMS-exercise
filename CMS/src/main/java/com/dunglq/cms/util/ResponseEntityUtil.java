package com.dunglq.cms.util;

import com.dunglq.cms.dto.ResponseDTO;
import com.dunglq.cms.exceptions.BaseException;
import com.dunglq.cms.validate.BusinessValidationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Slf4j
public class ResponseEntityUtil {

    private static final HttpStatus DEFAULT_ERROR_STATUS_CODE = HttpStatus.INTERNAL_SERVER_ERROR;

    private ResponseEntityUtil() {
    }

    /**
     * Gets response entity.
     *
     * @param throwable    the throwable
     * @param objectMapper the object mapper
     * @return the response entity
     * @throws JsonProcessingException the json processing exception
     */
    public static ResponseEntity<ResponseDTO> toResponseEntity(Throwable throwable, ObjectMapper objectMapper) throws JsonProcessingException {

        //Response internal service error
        if (throwable == null
                || throwable instanceof NullPointerException
                || throwable instanceof NumberFormatException) {
            return ResponseEntity.status(DEFAULT_ERROR_STATUS_CODE).body(ResponseDTO.internalServiceError());
        }

        // Response timeout
        if (throwable instanceof TimeoutException
                || throwable instanceof IOException
                || throwable instanceof InterruptedException) {
            return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body(ResponseDTO.timeOut());
        }

        // Response Bad request
        if (throwable instanceof IllegalArgumentException
                || throwable instanceof HttpMessageNotReadableException
                || throwable instanceof ConstraintViolationException
                || throwable instanceof MethodArgumentTypeMismatchException) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseDTO.badRequest());
        }

        // Exception when call another service via restTemplate
        if (throwable instanceof HttpClientErrorException) {
            HttpClientErrorException exception = (HttpClientErrorException) throwable;
            try {
                ResponseDTO response = objectMapper.readValue(exception.getResponseBodyAsString(), ResponseDTO.class);
                return ResponseEntity.status(exception.getStatusCode()).body(response);
            } catch (Exception e) {
                return ResponseEntity.status(DEFAULT_ERROR_STATUS_CODE).body(ResponseDTO.internalServiceError());
            }
        }

        // For every other Throwable, return an INTERNAL_SERVER_ERROR
        return ResponseEntity.status(DEFAULT_ERROR_STATUS_CODE).body(ResponseDTO.internalServiceError());
    }

    /**
     * To response dto response dto.
     *
     * @param exception the exception
     * @return the response dto
     */
    public static ResponseEntity<ResponseDTO> toResponseEntity(BaseException exception) {
        ResponseDTO responseDTO = ResponseDTO.builder()
                .statusCode(exception.getStatusCode())
                .message(exception.getMessage())
                .build();
        return ResponseEntity.status(exception.getHttpStatus()).body(responseDTO);
    }

    /**
     * To response entity response entity.
     *
     * @param businessValidationException the business validation exception
     * @return the response entity
     */
    public static ResponseEntity<ResponseDTO> toResponseEntity(BusinessValidationException businessValidationException) {
        BaseException exception = businessValidationException.getException();
        return toResponseEntity(exception);
    }
}
