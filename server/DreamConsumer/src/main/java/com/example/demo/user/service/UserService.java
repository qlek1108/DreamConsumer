package com.example.demo.user.service;

import com.example.demo.exception.BusinessLogicException;
import com.example.demo.exception.ExceptionCode;
import com.example.demo.user.repository.UserRepository;
import com.example.demo.user.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User createUser(User user){
        verifyExistsEmail(user.getEmail());
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
        foundUser.setModifiedAt(LocalDateTime.now());
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
