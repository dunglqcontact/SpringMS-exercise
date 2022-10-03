package com.dunglq.ums.request;

import com.dunglq.ums.annotation.CustomNotBlank;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.Date;

@Data
public class UpdateUserInfoDTO implements Serializable {
    @CustomNotBlank(statusCode = 1006, message = "user id must not blank", httpStatus = HttpStatus.BAD_REQUEST)
    private long userId;
    private String name;
    private String password;
    private Date dateOfBirth;
}