package ru.lobakina.educationalquizapp.support.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import ru.lobakina.educationalquizapp.support.dto.generic.GenericDTO;

import java.sql.Date;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class UserDTO extends GenericDTO {

    private String login;
    private String password;
    private String firstName;
    private String middleName;
    private String lastName;
    private String gender;
    private Date birthDate;
    private String telephoneNumber;
    private String email;
    private String role;
    private String group;

}
