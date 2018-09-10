package com.mdevi.webcrud;

import com.mdevi.webcrud.model.Person;
import com.mdevi.webcrud.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HrDbApplication implements CommandLineRunner {

    @Autowired
    private PersonRepository personRepository;

    public static void main(String[] args) {
        SpringApplication.run(HrDbApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        personRepository.save(new Person("John", "Doe", "jdoe@gmail.com"));
        personRepository.save(new Person("Jane", "Doe", "janedoe@gmail.com"));
        personRepository.save(new Person("Nick", "Finn", "nick@gmail.com"));
        personRepository.save(new Person("Meggy", "Rolls", "meg@gmail.com"));
        personRepository.save(new Person("Walter", "Frisky", "waltf@gmail.com"));
        personRepository.save(new Person("Chad", "Sing", "chad@gmail.com"));
        personRepository.save(new Person("Melany", "Ouster", "mous@gmail.com"));
        personRepository.save(new Person("Gordon", "Brown", "gbrown@gmail.com"));
        personRepository.save(new Person("John", "Doe", "jdoe@gmail.com"));
        personRepository.save(new Person("John", "Doe", "jdoe@gmail.com"));
        personRepository.save(new Person("John", "Doe", "jdoe@gmail.com"));
        personRepository.save(new Person("John", "Doe", "jdoe@gmail.com"));
        personRepository.save(new Person("John", "Doe", "jdoe@gmail.com"));
        personRepository.save(new Person("John", "Doe", "jdoe@gmail.com"));
        personRepository.save(new Person("John", "Doe", "jdoe@gmail.com"));
        personRepository.save(new Person("John", "Doe", "jdoe@gmail.com"));
        personRepository.save(new Person("John", "Doe", "jdoe@gmail.com"));
        personRepository.save(new Person("John", "Doe", "jdoe@gmail.com"));
        personRepository.save(new Person("John", "Doe", "jdoe@gmail.com"));
        personRepository.save(new Person("John", "Doe", "jdoe@gmail.com"));
        personRepository.save(new Person("John", "Doe", "jdoe@gmail.com"));
        personRepository.save(new Person("John", "Doe", "jdoe@gmail.com"));
        personRepository.save(new Person("John", "Doe", "jdoe@gmail.com"));
        personRepository.save(new Person("John", "Doe", "jdoe@gmail.com"));
        personRepository.save(new Person("John", "Doe", "jdoe@gmail.com"));
        personRepository.save(new Person("John", "Doe", "jdoe@gmail.com"));
        personRepository.save(new Person("John", "Doe", "jdoe@gmail.com"));
    }
}
