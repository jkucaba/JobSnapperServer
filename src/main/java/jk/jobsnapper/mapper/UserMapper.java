package jk.jobsnapper.mapper;

import jk.jobsnapper.dto.UserDto;
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
                user.getRole()
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
                userDto.getRole()
        );
    }
}
