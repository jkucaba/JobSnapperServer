package jk.jobsnapper.dto;

import jk.jobsnapper.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserWithoutImageDto {
    private Long iduser;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String birthday;
    private String role;
    private String sex;
    private int phone;
    private String abilities;
    private String profile;

    public UserWithoutImageDto(User user) {
        this.iduser = user.getIduser();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.birthday = user.getBirthday();
        this.role = user.getRole();
        this.sex = user.getSex();
        this.phone = user.getPhone();
        this.abilities = user.getAbilities();
        this.profile = user.getProfile();
    }
}