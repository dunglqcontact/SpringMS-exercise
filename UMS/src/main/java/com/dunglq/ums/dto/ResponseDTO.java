package com.dunglq.ums.dto;


import com.dunglq.ums.enums.StatusAndMessageEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseDTO implements Serializable {
    private static final long serialVersionUID = -1041131165167692228L;

    private Integer statusCode;
    private String message;
    private Object data;

    public ResponseDTO(Integer statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public static ResponseDTO success() {
        return new ResponseDTO(StatusAndMessageEnum.SUCCESS.getStatusCode(), StatusAndMessageEnum.SUCCESS.getMessage());
    }

    public static ResponseDTO internalServiceError() {
        return new ResponseDTO(StatusAndMessageEnum.INTERNAL_SERVER_ERROR.getStatusCode(), StatusAndMessageEnum.INTERNAL_SERVER_ERROR.getMessage());
    }

    public static ResponseDTO timeOut() {
        return new ResponseDTO(StatusAndMessageEnum.TIME_OUT.getStatusCode(), StatusAndMessageEnum.TIME_OUT.getMessage());
    }

    public static ResponseDTO badRequest() {
        return new ResponseDTO(StatusAndMessageEnum.BAD_REQUEST.getStatusCode(), StatusAndMessageEnum.BAD_REQUEST.getMessage());
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
