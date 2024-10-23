package jk.jobsnapper.mapper;

import jk.jobsnapper.dto.UserDto;
import jk.jobsnapper.dto.UserWithoutImageDto;
import jk.jobsnapper.entity.User;

public class UserMapper {
    public static UserDto maptoUserDto(User user) {
        return new UserDto(
                user.getIduser(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                user.getBirthday(),
                user.getRole(),
                user.getSex(),
                user.getPhone(),
                user.getAbilities(),
                user.getProfile(),
                user.getProfileImage()
        );
    }

    public static User maptoUser(UserDto userDto) {
        return new User(
                userDto.getIduser(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getEmail(),
                userDto.getPassword(),
                userDto.getBirthday(),
                userDto.getRole(),
                userDto.getSex(),
                userDto.getPhone(),
                userDto.getAbilities(),
                userDto.getProfile(),
                userDto.getProfileImage()
        );
    }
    public static UserWithoutImageDto mapToUserWithoutImageDto(User user) {
        return new UserWithoutImageDto(
                user.getIduser(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                user.getBirthday(),
                user.getRole(),
                user.getSex(),
                user.getPhone(),
                user.getAbilities(),
                user.getProfile()
        );
    }
}
