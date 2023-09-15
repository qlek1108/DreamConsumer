package com.example.demo.user.mapper;

import com.example.demo.user.dto.UserMyPageResponseDto;
import com.example.demo.user.dto.UserPostDto;
import com.example.demo.user.dto.UserResponseDto;
import com.example.demo.user.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-11T12:26:53+0900",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.2.1.jar, environment: Java 11.0.18 (Azul Systems, Inc.)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User UserPostDtoToUser(UserPostDto userPostDto) {
        if ( userPostDto == null ) {
            return null;
        }

        User user = new User();

        user.setUserName( userPostDto.getUserName() );
        user.setEmail( userPostDto.getEmail() );
        user.setPassword( userPostDto.getPassword() );
        user.setJob( userPostDto.getJob() );
        user.setAge( userPostDto.getAge() );
        user.setEmailAcceptance( userPostDto.isEmailAcceptance() );

        return user;
    }

    @Override
    public UserResponseDto UserToUserResponseDto(User user) {
        if ( user == null ) {
            return null;
        }

        long userId = 0L;
        String userName = null;
        String email = null;
        String password = null;
        String job = null;
        int age = 0;
        boolean emailAcceptance = false;

        userId = user.getUserId();
        userName = user.getUserName();
        email = user.getEmail();
        password = user.getPassword();
        job = user.getJob();
        age = user.getAge();
        emailAcceptance = user.isEmailAcceptance();

        UserResponseDto userResponseDto = new UserResponseDto( userId, userName, email, password, job, age, emailAcceptance );

        return userResponseDto;
    }

    @Override
    public UserMyPageResponseDto UserToUserMyPageResponseDto(User user) {
        if ( user == null ) {
            return null;
        }

        long userId = 0L;
        String userName = null;
        String email = null;
        String job = null;
        int age = 0;

        userId = user.getUserId();
        userName = user.getUserName();
        email = user.getEmail();
        job = user.getJob();
        age = user.getAge();

        UserMyPageResponseDto userMyPageResponseDto = new UserMyPageResponseDto( userId, userName, email, job, age );

        return userMyPageResponseDto;
    }
}
