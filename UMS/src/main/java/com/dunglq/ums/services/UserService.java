package com.dunglq.ums.services;

import com.dunglq.ums.entities.User;
import com.dunglq.ums.exceptions.BaseException;
import com.dunglq.ums.request.CreateUserDTO;
import com.dunglq.ums.request.RegisterUserDTO;
import com.dunglq.ums.request.UpdateUserInfoDTO;
import com.dunglq.ums.request.UpdateUserRoleDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService {
    Page<User> getUsers(Pageable pageable);
    User getUserByUserId (long UserId) throws BaseException;
    User createUser(CreateUserDTO request) throws BaseException;
    public User updateUser(UpdateUserInfoDTO userInfo) throws BaseException;
    public User register(RegisterUserDTO request) throws BaseException;
    public User updateRoleForUser(UpdateUserRoleDTO request) throws BaseException;
    void deleteUser(long userName) throws BaseException;
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
    public User getUserByUserName(String username) throws UsernameNotFoundException;
}
