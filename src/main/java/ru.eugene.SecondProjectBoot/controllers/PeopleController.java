package ru.eugene.SecondProjectBoot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.eugene.SecondProjectBoot.models.Person;
import ru.eugene.SecondProjectBoot.service.BookService;
import ru.eugene.SecondProjectBoot.service.PeopleService;

@Controller
@RequestMapping("/people")
public class PeopleController {

    public final PeopleService peopleService;
    public final BookService bookService;

    @Autowired
    public PeopleController(PeopleService peopleService, BookService bookService) {
        this.peopleService = peopleService;
        this.bookService = bookService;
    }

    @GetMapping()
    public String getAll(Model model) {
        model.addAttribute("people", peopleService.getAll());
        return "people/persons";
    }

    @GetMapping("/{id}")
    public String getPerson(@PathVariable("id")int id, Model model){
        Person person = peopleService.getPersonById(id);
        // Попробовать просмотреть все книги?
        bookService.isOverdue(person.getBookList());
        model.addAttribute("person", person);

        return "people/person_by_id";
    }

    @GetMapping("/{id}/edit")
    public String editPerson(@PathVariable("id")int id, Model model){
        model.addAttribute("person_for_edit", peopleService.getPersonById(id));
        return "people/edit_person";
    }

    @PatchMapping("/{id}")
    public String updatePerson(@PathVariable("id")int id,
                               @ModelAttribute("person_for_edit")Person person){
        person.setId(id);
        peopleService.updatePerson(person);
        return "redirect:/people";
    }

    @GetMapping("/new")
    public String createPerson(@ModelAttribute("person")Person person){
        return "people/create_person";
    }

    @PostMapping()
    public String addPerson(@ModelAttribute("person")Person person){
        peopleService.savePerson(person);
        return "redirect:/people";
    }
}
