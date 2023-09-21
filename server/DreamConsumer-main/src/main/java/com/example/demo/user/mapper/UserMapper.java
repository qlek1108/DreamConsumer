package com.example.demo.user.mapper;

import com.example.demo.user.dto.UserMyPageResponseDto;
import com.example.demo.user.dto.UserPostDto;
import com.example.demo.user.dto.UserResponseDto;
import com.example.demo.user.domain.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User UserPostDtoToUser(UserPostDto userPostDto);
    UserResponseDto UserToUserResponseDto(User user);
    UserMyPageResponseDto UserToUserMyPageResponseDto(User user);
}
