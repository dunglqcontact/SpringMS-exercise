package com.dunglq.ums.services.implement;

import com.dunglq.ums.entities.Role;
import com.dunglq.ums.exceptions.BaseException;
import com.dunglq.ums.repositories.RoleRepository;
import com.dunglq.ums.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImplement  implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role getRoleByRoleId(long roleId) throws BaseException {
        return roleRepository.findById(roleId).orElseThrow(() ->
                new BaseException(1003, "Can't find Role", HttpStatus.BAD_REQUEST));
    }
}
