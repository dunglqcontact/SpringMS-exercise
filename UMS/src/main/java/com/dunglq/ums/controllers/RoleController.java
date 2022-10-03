package com.dunglq.ums.controllers;


import com.dunglq.ums.dto.ResponseDTO;
import com.dunglq.ums.entities.Role;
import com.dunglq.ums.entities.User;
import com.dunglq.ums.exceptions.BaseException;
import com.dunglq.ums.services.RoleService;
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
public class RoleController {
    @Autowired
    private RoleService roleService;
    @GetMapping("api/v1/roles")
    public ResponseEntity<ResponseDTO> getRoles() {
        ResponseDTO responseDTO = ResponseDTO.success();
        List<Role> roles = roleService.getRoles();
        responseDTO.setData(roles);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("api/v1/roles/{roleId}")
    public ResponseEntity<ResponseDTO> getUser(@PathVariable long roleId) throws BaseException {
        ResponseDTO responseDTO = ResponseDTO.success();
        Role role = roleService.getRoleByRoleId(roleId);
        responseDTO.setData(role);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
