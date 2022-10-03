package com.dunglq.cms.controllers.advice;

import com.dunglq.cms.dto.ResponseDTO;
import com.dunglq.cms.enums.StatusAndMessageEnum;
import com.dunglq.cms.exceptions.BaseException;
import com.dunglq.cms.util.ResponseEntityUtil;
import com.dunglq.cms.validate.BusinessValidationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class GlobalHandlerExceptionControllerAdvice {
    private final ObjectMapper objectMapper;

    public GlobalHandlerExceptionControllerAdvice(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @ExceptionHandler(value = BaseException.class)
    public ResponseEntity<ResponseDTO> handleCustomRuntimeException(BaseException exception) {
        logError(exception);
        return ResponseEntityUtil.toResponseEntity(exception);
    }

    @ExceptionHandler(value = BusinessValidationException.class)
    public ResponseEntity<ResponseDTO> handleCustomRuntimeException(BusinessValidationException exception) {
        logError(exception);
        return ResponseEntityUtil.toResponseEntity(exception);
    }

    @ExceptionHandler(value = BindException.class)
    public ResponseEntity<ResponseDTO> handleValidationException(BindException exception) {
        logError(exception);
        List<String> errors = exception.getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        ResponseDTO responseDTO = new ResponseDTO(StatusAndMessageEnum.BAD_REQUEST.getStatusCode(), Strings.join(errors, ','));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDTO);
    }

    @ExceptionHandler(value = Throwable.class)
    public ResponseEntity<ResponseDTO> handleException(Throwable throwable) throws JsonProcessingException {
        logError(throwable);
        return ResponseEntityUtil.toResponseEntity(throwable, objectMapper);
    }

    private void logError(Throwable throwable) {
        log.error("Unexpected Throwable caught by GlobalHandlerExceptionControllerAdvice: {}", throwable.getMessage(), throwable);
    }

}
