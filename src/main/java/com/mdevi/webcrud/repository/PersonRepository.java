package com.mdevi.webcrud.repository;

import com.mdevi.webcrud.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {

    @PostFilter("hasPermission(filterObject, 'READ')")
    Page<Person> findAll(Pageable pageable);

    @PostAuthorize("hasPermission(returnObject, 'READ')")
    Optional<Person> findById(Long aLong);

    @SuppressWarnings("unchecked")
    @PreAuthorize("hasPermission(#person, 'WRITE')")
    Person save(@Param("person") Person person);
}
