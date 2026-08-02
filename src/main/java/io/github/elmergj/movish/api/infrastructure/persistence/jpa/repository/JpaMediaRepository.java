package io.github.elmergj.movish.api.infrastructure.persistence.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaMediaRepository extends JpaRepository<Object, String> {
//public interface JpaMediaRepository extends JpaRepository<MediaEntity, String> {

//    Optional<MediaEntity> findByIdAndMediaType(String id, MediaType mediaType);
}
