package com.dunglq.ums.request;

import com.dunglq.ums.annotation.CustomNotBlank;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.Date;

@Data
public class CreateUserDTO implements Serializable {
    @CustomNotBlank(statusCode = 1001, message = "name must not blank", httpStatus = HttpStatus.BAD_REQUEST)
    private String name;
    @CustomNotBlank(statusCode = 1002, message = "username must not blank", httpStatus = HttpStatus.BAD_REQUEST)
    private String username;
    @CustomNotBlank(statusCode = 1003, message = "password must not blank", httpStatus = HttpStatus.BAD_REQUEST)
    private String password;
    @CustomNotBlank(statusCode = 1004, message = "date of birth must not blank", httpStatus = HttpStatus.BAD_REQUEST)
    private Date dateOfBirth;
    @CustomNotBlank(statusCode = 1005, message = "role must not blank", httpStatus = HttpStatus.BAD_REQUEST)
    private long roleId;
}