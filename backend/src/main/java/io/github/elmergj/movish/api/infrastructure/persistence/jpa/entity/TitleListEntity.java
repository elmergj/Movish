package io.github.elmergj.movish.api.infrastructure.persistence.jpa.entity;

import io.github.elmergj.movish.api.domain.model.entity.listing.TitleListType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "title_list")
public class TitleListEntity {

    @Id
    @Column(nullable = false, updatable = false)
    private String id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @Column(nullable = false)
    private String name;

    @ElementCollection
    @CollectionTable(name = "list_titles_ids", joinColumns = @JoinColumn(name = "list_id"))
    @Column(name = "user_title_id")
    private Set<String> userTitleIds;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TitleListType listType;

    @Column(nullable = false)
    private LocalDate dateCreated;
}
