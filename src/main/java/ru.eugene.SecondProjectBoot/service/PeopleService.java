package ru.eugene.SecondProjectBoot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.eugene.SecondProjectBoot.models.Person;
import ru.eugene.SecondProjectBoot.repository.PeopleRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PeopleService{
    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> getAll(){
        return peopleRepository.findAll();
    }

    public Person getPersonById(int id){
        return peopleRepository.findById(id).orElse(null);
    }

    public void savePerson(Person person){
        peopleRepository.save(person);
    }

    public void updatePerson(Person updatedPerson){
        peopleRepository.save(updatedPerson);
    }



}
