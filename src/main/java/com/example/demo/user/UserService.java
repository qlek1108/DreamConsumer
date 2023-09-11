package com.example.demo.user;

import com.example.demo.exception.BusinessLogicException;
import com.example.demo.exception.ExceptionCode;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user){
        verifyExistsEmail(user.getEmail());
        return userRepository.save(user);
    }

    public User findUser(long userId) {
        return findVerifiedUser(userId);
    }

    public void deleteUser(long userId) {
        User foundUser = findVerifiedUser(userId);
        userRepository.delete(foundUser);
    }

    public User activateUser(long userId) {
        User foundUser = findVerifiedUser(userId);
        // 휴면 상태인지 확인
        if(foundUser.getUserStatus() != User.UserStatus.USER_SLEEP) {
            throw new BusinessLogicException(ExceptionCode.USER_NOT_SLEEPING);
        }
        foundUser.setUserStatus(User.UserStatus.USER_ACTIVE);
        return userRepository.save(foundUser);
    }

    private void verifyExistsEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isPresent()) {
            throw new BusinessLogicException(ExceptionCode.USER_EXISTS);
        }
    }

    private User findVerifiedUser(long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        User foundUser = optionalUser.orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.USER_NOT_FOUND));
        return foundUser;
    }
}
