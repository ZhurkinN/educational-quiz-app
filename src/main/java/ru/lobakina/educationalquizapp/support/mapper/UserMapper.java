package ru.lobakina.educationalquizapp.support.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import ru.lobakina.educationalquizapp.model.user.User;
import ru.lobakina.educationalquizapp.repository.user.GroupRepository;
import ru.lobakina.educationalquizapp.repository.user.RoleRepository;
import ru.lobakina.educationalquizapp.support.dto.UserDTO;
import ru.lobakina.educationalquizapp.support.mapper.generic.GenericMapper;

import java.util.Objects;

import static ru.lobakina.educationalquizapp.model.enums.Roles.STUDENT;

@Component
@RequiredArgsConstructor
public class UserMapper extends GenericMapper<User, UserDTO> {

    private final RoleRepository roleRepository;
    private final GroupRepository groupRepository;

    @Override
    public User toEntity(UserDTO dto) {
        User user = new User().setLogin(dto.getLogin())
                .setPassword(dto.getPassword())
                .setBirthDate(dto.getBirthDate())
                .setEmail(dto.getEmail())
                .setGender(dto.getGender())
                .setFirstName(dto.getFirstName())
                .setLastName(dto.getLastName())
                .setMiddleName(dto.getMiddleName());
        if (!Objects.isNull(dto.getRole())) {
            user.setRole(roleRepository.findByRoleName(dto.getRole()));
        } else {
            user.setRole(roleRepository.findByRoleName(STUDENT.getRoleName()));
        }
        if (!Objects.isNull(dto.getGroup())) {
            user.setGroup(groupRepository.findByGroupNumber(dto.getGroup()));
        } else {
            user.setGroup(null);
        }
        user.setCreatedWhen(dto.getCreatedWhen());
        return user;
    }

    @Override
    public UserDTO toDto(User entity) {
        return null;
    }
}
