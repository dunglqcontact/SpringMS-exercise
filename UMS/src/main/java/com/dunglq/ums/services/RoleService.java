package com.dunglq.ums.services;

import com.dunglq.ums.entities.Role;
import com.dunglq.ums.exceptions.BaseException;

import java.util.List;

public interface RoleService {
    List<Role> getRoles();
    Role getRoleByRoleId (long roleId) throws BaseException;
}
