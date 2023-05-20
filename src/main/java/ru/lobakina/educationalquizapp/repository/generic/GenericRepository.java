package ru.lobakina.educationalquizapp.repository.generic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import ru.lobakina.educationalquizapp.model.generic.GenericModel;

@NoRepositoryBean
public interface GenericRepository<M extends GenericModel> extends JpaRepository<M, Long> {
}
