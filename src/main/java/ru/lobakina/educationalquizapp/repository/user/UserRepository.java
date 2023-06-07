package ru.lobakina.educationalquizapp.repository.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.lobakina.educationalquizapp.model.user.User;
import ru.lobakina.educationalquizapp.repository.generic.GenericRepository;

import java.util.List;

/**
 * Repository for actions with users
 */
@Repository
public interface UserRepository extends GenericRepository<User> {

    /**
     * Gets user by login
     *
     * @param username login
     * @return user
     */
    User findUserByLogin(String username);

    /**
     * If user exists with this login
     *
     * @param login login
     * @return exists or not
     */
    boolean existsByLogin(String login);

    /**
     * Searches users
     *
     * @param login    login
     * @param pageable page
     * @return users
     */
    @Query(nativeQuery = true,
            value = """
                    select distinct users.*
                    from users
                    where users.login like '%' || :login || '%'
                           """)
    Page<User> searchUsers(@Param(value = "login") String login,
                           Pageable pageable);

    /**
     * Searches students
     *
     * @param login    login
     * @param group    group
     * @param pageable page
     * @return students
     */
    @Query(nativeQuery = true,
            value = """
                    select distinct u.* from users u
                    join groups on u.group_id = groups.id
                    where u.login like '%' || :login || '%'
                    and groups.group_number like '%' || :group || '%'
                       """)
    Page<User> searchStudents(@Param(value = "group") String group,
                              @Param(value = "login") String login,
                              Pageable pageable);

    /**
     * Gets page of students
     *
     * @param pageable page
     * @return students
     */
    @Query(nativeQuery = true,
            value = "select users.* from users where users.role_id = 2")
    Page<User> findStudents(Pageable pageable);

    /**
     * Gets list of students
     *
     * @return students
     */
    @Query(nativeQuery = true,
            value = "select users.* from users where users.role_id = 2")
    List<User> findStudents();
}
