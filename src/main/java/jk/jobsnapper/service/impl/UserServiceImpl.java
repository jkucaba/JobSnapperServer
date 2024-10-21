package jk.jobsnapper.service.impl;

import jk.jobsnapper.dto.UserDto;
import jk.jobsnapper.entity.User;
import jk.jobsnapper.exception.ResourceNotFoundException;
import jk.jobsnapper.mapper.UserMapper;
import jk.jobsnapper.repository.UserRepository;
import jk.jobsnapper.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    @Override
    public UserDto createUser(UserDto userDto) {
        User user = UserMapper.maptoUser(userDto);
        User savedUser = userRepository.save(user);
        return UserMapper.maptoUserDto(savedUser);
    }

    @Override
    public UserDto getUserById(Long userId) throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
        return UserMapper.maptoUserDto(user);
    }
    @Override
    public UserDto getUserByEmail(String email) throws ResourceNotFoundException {
        try {
            User user = userRepository.findByEmail(email);
            return UserMapper.maptoUserDto(user);
        } catch (Exception e) {
              throw new ResourceNotFoundException("User not found for this id :: " + email);
        }
    }
    @Override
    public List<UserDto> getAllNonAdminUsers() {
        return userRepository.findAll().stream()
                .filter(user -> !user.getRole().equals("admin"))
                .map(user -> new UserDto(user))
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map((employee) -> UserMapper.maptoUserDto(employee))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUsers(Long userId, UserDto updatedUser) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User not found for this id :: " + userId));

        user.setFirstName(updatedUser.getFirstName());
        user.setLastName(updatedUser.getLastName());
        user.setEmail(updatedUser.getEmail());
        user.setPassword(updatedUser.getPassword());
        user.setBirthday(updatedUser.getBirthday());
        user.setRole(updatedUser.getRole());

        User updatedUserObj = userRepository.save(user);
        return UserMapper.maptoUserDto(updatedUserObj);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
