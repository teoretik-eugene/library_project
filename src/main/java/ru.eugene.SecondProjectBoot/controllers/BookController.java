package ru.eugene.SecondProjectBoot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.eugene.SecondProjectBoot.models.Book;
import ru.eugene.SecondProjectBoot.models.Person;
import ru.eugene.SecondProjectBoot.service.BookService;
import ru.eugene.SecondProjectBoot.service.PeopleService;


@Controller
@RequestMapping("/books")
public class BookController {

    public final BookService bookService;
    public final PeopleService peopleService;

    public BookController(BookService bookService, PeopleService peopleService) {
        this.bookService = bookService;
        this.peopleService = peopleService;
    }

    @GetMapping()
    public String getAll(Model model,
                         @RequestParam(value = "page", required = false)Integer page,
                         @RequestParam(value = "books_per_page", required = false)Integer booksPerPage,
                         @RequestParam(value = "sort_by_year", required = false)Boolean sortByYear){
        if(page != null && booksPerPage != null)
            model.addAttribute("books", bookService.getAllFromPageSorted(page, booksPerPage, sortByYear));
        else
            model.addAttribute("books", bookService.getAll(sortByYear));

        return "books/books";
    }

    @GetMapping("/{id}")
    public String getBook(@PathVariable("id")int id, Model model, @ModelAttribute("person") Person person){
        model.addAttribute("book_by_id", bookService.getBookById(id));
        model.addAttribute("people", peopleService.getAll());
        // Проверим просрочена ли книга
        // bookService.isOverdue(bookService.getBookById(id));
        //bookService.addDate(bookService.getBookById(id));
        return "books/book";
    }

    @GetMapping("/{id}/edit")
    public String editBook(@PathVariable("id")int id, Model model){
        model.addAttribute("book_by_id", bookService.getBookById(id));
        return "books/edit_book";
    }

    @PatchMapping("/{id}")
    public String updateBook(@PathVariable("id")int id, @ModelAttribute("book_by_id") Book book){
        book.setId(id);
        bookService.update(book);
        return "redirect:/books";
    }

    @GetMapping("/new")
    public String createBook(@ModelAttribute("book")Book book){
        return "books/add_book";
    }

    @PostMapping()
    public String saveBook(@ModelAttribute("book")Book book){
        bookService.save(book);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/assign")
    public String assignBook(@PathVariable("id")int id,
                             @ModelAttribute("person")Person person, Model model){
        bookService.assign(id, person);
        model.addAttribute("book_by_id", bookService.getBookById(id));
        return "redirect:/books/{id}";
    }

    @PatchMapping("/{id}/release")
    public String releaseBook(@PathVariable("id")int id, Model model,
                              @ModelAttribute("person")Person person){
        bookService.releaseBook(id);    // Освободили книгу
        model.addAttribute("book_by_id", bookService.getBookById(id));
        model.addAttribute("people", peopleService.getAll());
        return "redirect:/books/{id}";
    }

    @GetMapping("/search")
    public String getSearch(@RequestParam(value = "search_book", required = false)String searchBook,
                            Model model){
        if(searchBook != null)
            model.addAttribute("book_list", bookService.searchBookByStartName(searchBook));
        return "books/search";
    }
}
