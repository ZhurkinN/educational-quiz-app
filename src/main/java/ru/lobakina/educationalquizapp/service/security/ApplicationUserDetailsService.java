package ru.lobakina.educationalquizapp.service.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.lobakina.educationalquizapp.model.user.User;
import ru.lobakina.educationalquizapp.repository.user.UserRepository;
import ru.lobakina.educationalquizapp.service.security.model.ApplicationUserDetails;
import ru.lobakina.educationalquizapp.support.constants.RoleNameConstantsKeeper;

import java.util.ArrayList;
import java.util.List;

import static ru.lobakina.educationalquizapp.model.enums.Roles.TEACHER;

@Service
@RequiredArgsConstructor
public class ApplicationUserDetailsService implements UserDetailsService {

    @Value("${spring.security.user.name}")
    private String adminUsername;

    @Value("${spring.security.user.password}")
    private String adminPassword;

    @Value("${spring.security.user.roles}")
    private String adminRole;

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.equals(adminUsername)) {
            return new ApplicationUserDetails(null, username, adminPassword, List.of(new SimpleGrantedAuthority("ROLE_" + adminRole)));
        } else {
            User user = userRepository.findUserByLogin(username);
            List<GrantedAuthority> authorities = new ArrayList<>();
            String roleName = user.getRole().getRoleName();
            String role = "ROLE_" + (roleName.equals(TEACHER.getRoleName()) ? RoleNameConstantsKeeper.TEACHER : RoleNameConstantsKeeper.STUDENT);
            authorities.add(new SimpleGrantedAuthority(role));
            return new ApplicationUserDetails(user.getId().intValue(), username, user.getPassword(), authorities);
        }
    }
}
