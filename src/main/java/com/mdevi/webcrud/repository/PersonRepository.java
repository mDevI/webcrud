package com.mdevi.webcrud.repository;

import com.mdevi.webcrud.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PostFilter;

public interface PersonRepository extends JpaRepository<Person, Long> {

    @PostFilter("hasPermission(filterObject, 'READ')")
    Page<Person> findAll(Pageable pageable);


}
