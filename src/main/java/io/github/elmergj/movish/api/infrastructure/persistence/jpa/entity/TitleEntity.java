package io.github.elmergj.movish.api.infrastructure.persistence.jpa.entity;

import io.github.elmergj.movish.api.domain.model.entity.catalog.title.MediaType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "title")
public class TitleEntity{

    @Id
    @Column(nullable = false, updatable = false)
    private String id;

    @Column(nullable = false, updatable = false)
    private String externalTitleId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDate releaseDate;

    @Column(nullable = false)
    private Double tmdbRating;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MediaType mediaType;
}
