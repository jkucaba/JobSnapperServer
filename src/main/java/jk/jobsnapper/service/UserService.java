package jk.jobsnapper.service;

import jk.jobsnapper.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);

    UserDto getUserById(Long userId);

    List<UserDto> getAllUsers();

    UserDto updateUsers(Long userId, UserDto updatedUser);

    void deleteUser(Long userId);
}
