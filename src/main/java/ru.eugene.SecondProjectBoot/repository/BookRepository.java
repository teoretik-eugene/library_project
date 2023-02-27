package ru.eugene.SecondProjectBoot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.eugene.SecondProjectBoot.models.Book;


import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    public List<Book> findBookByNameStartingWith(String startName);
}
