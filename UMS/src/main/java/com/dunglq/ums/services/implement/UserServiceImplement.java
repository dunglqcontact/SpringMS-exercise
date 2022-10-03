package com.dunglq.ums.services.implement;

import com.dunglq.ums.entities.Role;
import com.dunglq.ums.entities.User;
import com.dunglq.ums.exceptions.BaseException;
import com.dunglq.ums.repositories.RoleRepository;
import com.dunglq.ums.repositories.UserRepository;
import com.dunglq.ums.request.CreateUserDTO;
import com.dunglq.ums.request.RegisterUserDTO;
import com.dunglq.ums.request.UpdateUserInfoDTO;
import com.dunglq.ums.request.UpdateUserRoleDTO;
import com.dunglq.ums.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;

@Service
public class UserServiceImplement implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Page<User> getUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User getUserByUserId(long id) throws BaseException {
        return userRepository.findById(id).orElseThrow(() ->
                new BaseException(1003, "Can't find User", HttpStatus.BAD_REQUEST));
    }

    @Override
    public User createUser(CreateUserDTO request) throws BaseException {
        Optional<User> optionalUser = Optional.ofNullable(userRepository.findByUserName(request.getUsername()));
        if (optionalUser.isPresent()) {
            throw new BaseException(1003, "Username already exist", HttpStatus.BAD_REQUEST);
        }
        Date date = new Date();
        Role role = roleRepository.findById(request.getRoleId()).get();
        User newUser = User.builder()
                .name((request.getName()))
                .userName(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .dateOfBirth(request.getDateOfBirth())
                .role(role)
                .build();
        return userRepository.save(newUser);
    }

    @Override
    public User updateUser(UpdateUserInfoDTO userInfo) throws BaseException {
        Optional<User> optionalUser = Optional.ofNullable(userRepository.findById(userInfo.getUserId()).get());
        if (!optionalUser.isPresent()) {
            throw new BaseException(1003, "User not exist", HttpStatus.BAD_REQUEST);
        }
        User user = userRepository.findById(userInfo.getUserId()).get();
        if(userInfo.getDateOfBirth()!=null) {
            user.setDateOfBirth(userInfo.getDateOfBirth());
        }
        if(userInfo.getPassword()!=null){
            user.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        }
        if(userInfo.getName()!=null){
            user.setName(userInfo.getName());
        }

        return userRepository.save(user);
    }

    @Override
    public User updateRoleForUser(UpdateUserRoleDTO request) throws BaseException {
        Optional<User> optionalUser = Optional.ofNullable(userRepository.findById(request.getUserId()).get());
        if (!optionalUser.isPresent()) {
            throw new BaseException(1006, "User Id is not exist", HttpStatus.BAD_REQUEST);
        }
        User updateUser = userRepository.findById(request.getUserId()).get();
        Role role = roleRepository.findById(request.getRoleId()).get();
        updateUser.setRole(role);

        return userRepository.save(updateUser);
    }

    @Override
    public User register(RegisterUserDTO request) throws BaseException {
        Optional<User> optionalAccount = Optional.ofNullable(userRepository.findByUserName(request.getUsername()));
        if (optionalAccount.isPresent()) {
            throw new BaseException(1003, "Username already exist", HttpStatus.BAD_REQUEST);
        }
        Role role = roleRepository.findById(2L).get();
        Date date = new Date();
        User newAccount = User.builder()
                .name((request.getName()))
                .userName(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .dateOfBirth(request.getDateOfBirth())
                .role(role)
                .build();
        return userRepository.save(newAccount);
    }

    @Override
    public void deleteUser(long id) throws BaseException {
        User customer = userRepository.findById(id).orElseThrow(() ->
                new BaseException(1000, "Can't find user", HttpStatus.NOT_FOUND));
        userRepository.delete(customer);
    }
    @Override
    public User getUserByUserName(String username) throws UsernameNotFoundException {
        return userRepository.findByUserName(username);
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User User = userRepository.findByUserName(username);
        if(User == null){
            System.out.println("User not found in database");
            throw new UsernameNotFoundException("User not found in database");
        }
        else{
            System.out.println("User found in database: "+ username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority(User.getRole().getRoleName()));
        return new org.springframework.security.core.userdetails.User(User.getUserName(),User.getPassword(), authorities);
    }
}
