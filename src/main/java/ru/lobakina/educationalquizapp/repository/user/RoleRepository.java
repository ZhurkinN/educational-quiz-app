package ru.lobakina.educationalquizapp.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.lobakina.educationalquizapp.model.enums.Roles;
import ru.lobakina.educationalquizapp.model.user.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRoleName(String roleName);
}