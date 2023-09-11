package com.example.demo.user;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User UserPostDtoToUser(UserPostDto userPostDto);
    UserResponseDto UserToUserResponseDto(User user);
}
