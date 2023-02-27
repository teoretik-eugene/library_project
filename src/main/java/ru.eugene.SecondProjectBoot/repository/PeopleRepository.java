package ru.eugene.SecondProjectBoot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.eugene.SecondProjectBoot.models.Person;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {

}
