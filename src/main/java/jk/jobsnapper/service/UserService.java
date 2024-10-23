package jk.jobsnapper.service;

import jk.jobsnapper.dto.UserDto;
import jk.jobsnapper.dto.UserWithoutImageDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);

    UserDto getUserById(Long userId);

    List<UserDto> getAllUsers();

    UserDto updateUsers(Long userId, UserDto updatedUser);
    UserDto getUserByEmail(String email);
    List<UserWithoutImageDto> getAllNonAdminUsers();
    void saveProfileImage(Long userId, MultipartFile file) throws IOException;
    
    void deleteUser(Long userId);

    byte[] getProfileImage(Long userId);
}
