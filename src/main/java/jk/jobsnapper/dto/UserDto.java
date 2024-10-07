package jk.jobsnapper.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long iduser;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String birthday;
    private String role;
}
