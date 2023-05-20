package ru.lobakina.educationalquizapp.model.generic;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
public class GenericModel {

    @Id
    @Column(name = "id",
            unique = true,
            nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(columnDefinition = "timestamp default now()")
    protected LocalDateTime createdWhen = LocalDateTime.now();
}
