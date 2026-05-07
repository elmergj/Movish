package io.github.elmergj.movish.api.infrastructure.persistence.jpa.entity;


import io.github.elmergj.movish.api.domain.model.entity.library.TrackingStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(
        name = "user_title",
        uniqueConstraints = @UniqueConstraint(name = "unique_title_constraint", columnNames = {"user_id", "title_id"}
))
public class UserTitleEntity {

    @Id
    @Column(nullable = false, updatable = false)
    private String id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @Column(nullable = false)
    private String titleId;

    @Column(nullable = false)
    private LocalDate createdDate;

    @Column(nullable = false)
    private boolean isFavorite;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TrackingStatus status;

    @Column(nullable = false)
    private int timesWatched;

    private int userTitleRating;

    private String userTitleReview;

}