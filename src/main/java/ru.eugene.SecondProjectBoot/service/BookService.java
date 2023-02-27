package ru.eugene.SecondProjectBoot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.eugene.SecondProjectBoot.models.Book;
import ru.eugene.SecondProjectBoot.models.Person;
import ru.eugene.SecondProjectBoot.repository.BookRepository;


import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class BookService {

    public final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAll(){
        return bookRepository.findAll();
    }

    public List<Book> getAllFromPage(int page, int booksPerPage){
        return bookRepository.findAll(PageRequest.of(page, booksPerPage)).getContent();
    }

    public List<Book> getAllFromPageSorted(int page, int booksPerPage, Boolean sortByYear){
        if(sortByYear != null && sortByYear == true)
            return bookRepository.findAll(PageRequest.of(page, booksPerPage, Sort.by("year")))
                    .getContent();
        else
            return bookRepository.findAll(PageRequest.of(page, booksPerPage)).getContent();
    }

    public List<Book> getAll(Boolean sortByYear){
        if(sortByYear != null && sortByYear == true)
            return bookRepository.findAll(Sort.by("year"));
        else
            return bookRepository.findAll();
    }

    public Book getBookById(int id){
        return bookRepository.findById(id).orElse(null);
    }

    public void update(Book updatedBook){
        // Назначить тут владельца и дату выдачи
        bookRepository.save(updatedBook);
    }

    public void save(Book book){
        bookRepository.save(book);
    }

    public void assign(int bookId, Person person){
        Book book = getBookById(bookId);
        book.setPerson(person);
        book.setAssignedAt(new Date());
        bookRepository.save(book);
    }

    public void releaseBook(int bookId){
        Book book = getBookById(bookId);
        book.setPerson(null);
        book.setAssignedAt(null);
        bookRepository.save(book);
    }

    public List<Book> searchBookByStartName(String startName){
        return bookRepository.findBookByNameStartingWith(startName);
    }

    public boolean isOverdue(Book book){
        Date now = new Date();
        // Разобраться над null
        if(book.getAssignedAt() == null)
            return false;
        long diffMillis = now.getTime() - book.getAssignedAt().getTime();
        long days = diffMillis / (1000*60*60*24);
        // Проверка на 7 дней
        if(days > 7){
            System.out.println("IS OVERDUE");
            System.out.println(days);
            return true;
        }
        else{
            System.out.println("NOT OVERDUE");
            System.out.println(days);
            return false;
        }
    }

    public void isOverdue(List<Book> bookList){
        Date now = new Date();
        for(int i = 0; i < bookList.size(); i++){
            long diffMillis = now.getTime() - bookList.get(i).getAssignedAt().getTime();
            long days = diffMillis / (1000*60*60*24);
            if(days > 7)
                bookList.get(i).setOverdue(true);
            else
                bookList.get(i).setOverdue(false);
        }
    }

    public void addDate(Book book){
        if(book.getAssignedAt() != null) {
            Date now = new Date();
            Date randDate = new Date(now.getTime() - 864000000);
            book.setAssignedAt(randDate);
            bookRepository.save(book);
        }
    }
}
