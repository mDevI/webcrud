package com.mdevi.webcrud.controller;

import com.mdevi.webcrud.model.Person;
import com.mdevi.webcrud.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PersonController {

    private final PersonRepository personRepository;

    @Autowired
    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping("/")
    public String showPersons(Model model, @RequestParam(defaultValue = "0") int pageIndex) {
        Page<Person> personPage = personRepository.findAll(PageRequest.of(pageIndex, 10));
        model.addAttribute("data", personPage);
        model.addAttribute("currentPage", pageIndex);
        return "index";
    }

    @PostMapping("/save")
    public String savePerson(Person person) {
        personRepository.save(person);
        return "redirect:/";
    }

    @GetMapping("/delete")
    public String deletePerson(Long id) {
        personRepository.deleteById(id);
        return "redirect:/";
    }

    // not realized yet.
    @DeleteMapping("/delete/{id}")
    public String processDelete(@PathVariable String id) {
        personRepository.deleteById(Long.parseLong(id));
        return "redirect:/";
    }

    @GetMapping("/getPerson")
    @ResponseBody
    public Person getThePerson(Long id) {
        return personRepository.findById(id).orElse(null);
    }

}
