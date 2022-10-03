package com.dunglq.ums.controllers;

import com.dunglq.ums.dto.ResponseDTO;
import com.dunglq.ums.entities.User;
import com.dunglq.ums.exceptions.BaseException;
import com.dunglq.ums.request.CreateUserDTO;
import com.dunglq.ums.request.RegisterUserDTO;
import com.dunglq.ums.request.UpdateUserInfoDTO;
import com.dunglq.ums.request.UpdateUserRoleDTO;
import com.dunglq.ums.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@ResponseBody
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("api/v1/users")
    public ResponseEntity<ResponseDTO> getUsers(Pageable pageable) {
        ResponseDTO responseDTO = ResponseDTO.success();
        Page<User> Users = userService.getUsers(pageable);
        responseDTO.setData(Users);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("api/v1/users/{userName}")
    public ResponseEntity<ResponseDTO> getUser(@PathVariable long userName) throws BaseException {
        ResponseDTO responseDTO = ResponseDTO.success();
        User User = userService.getUserByUserId(userName);
        responseDTO.setData(User);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PostMapping("api/v1/users")
    public ResponseEntity<ResponseDTO> createUser(@Valid @RequestBody CreateUserDTO createUserDTO) throws BaseException {
        ResponseDTO responseDTO = ResponseDTO.success();
        User User = userService.createUser(createUserDTO);
        responseDTO.setData(User);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @PutMapping("api/v1/users")
    public ResponseEntity<ResponseDTO> updateUser(@Valid @RequestBody UpdateUserInfoDTO updateUserInfoDTO) throws BaseException {
        ResponseDTO responseDTO = ResponseDTO.success();
        User User = userService.updateUser(updateUserInfoDTO);
        responseDTO.setData(User);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @PostMapping("api/v1/register")
    public ResponseEntity<ResponseDTO> register(@Valid @RequestBody RegisterUserDTO registerUserDTO) throws BaseException {
        ResponseDTO responseDTO = ResponseDTO.success();
        User User = userService.register(registerUserDTO);
        responseDTO.setData(User);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @PutMapping("api/v1/users/user-role")
    public ResponseEntity<ResponseDTO> updateUserRole(@Valid @RequestBody UpdateUserRoleDTO updateUserRoleDTO) throws BaseException {
        ResponseDTO responseDTO = ResponseDTO.success();
        User User = userService.updateRoleForUser(updateUserRoleDTO);
        responseDTO.setData(User);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("api/v1/users/{userName}")
    public ResponseEntity<ResponseDTO> deleteUser(@PathVariable long userName) throws BaseException {
        ResponseDTO responseDTO = ResponseDTO.success();
        userService.deleteUser(userName);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }
}
