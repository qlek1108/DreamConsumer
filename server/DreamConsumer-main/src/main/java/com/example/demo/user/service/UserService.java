package com.example.demo.user.service;

import com.example.demo.auth.utils.AuthorityUtils;
import com.example.demo.exception.BusinessLogicException;
import com.example.demo.exception.ExceptionCode;
import com.example.demo.user.domain.UserRepository;
import com.example.demo.user.domain.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthorityUtils authorityUtils;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthorityUtils authorityUtils) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authorityUtils = authorityUtils;
    }

    @Transactional
    public User createUser(User user){
        verifyExistsEmail(user.getEmail());
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        List<String> roles = authorityUtils.createRoles(user.getEmail());
        user.setRoles(roles);
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User findUser(long userId) {
        return findVerifiedUser(userId);
    }

    @Transactional(readOnly = true)
    public void deleteUser(long userId) {
        User foundUser = findVerifiedUser(userId);
        userRepository.delete(foundUser);
    }

    @Transactional
    public User activateUser(long userId) {
        User foundUser = findVerifiedUser(userId);
        if(foundUser.getUserStatus() != User.UserStatus.USER_SLEEP) {
            throw new BusinessLogicException(ExceptionCode.USER_NOT_SLEEPING);
        }
        foundUser.setUserStatus(User.UserStatus.USER_ACTIVE);
        foundUser.setUpdatedAt(LocalDateTime.now());
        return userRepository.save(foundUser);
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    private void verifyExistsEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isPresent()) {
            throw new BusinessLogicException(ExceptionCode.USER_EXISTS);
        }
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    private User findVerifiedUser(long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        User foundUser = optionalUser.orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.USER_NOT_FOUND));
        return foundUser;
    }
}
