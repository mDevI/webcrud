package com.mdevi.webcrud.repository;

import com.mdevi.webcrud.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
