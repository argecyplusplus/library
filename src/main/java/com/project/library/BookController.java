package com.project.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public String showBooks(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "books";
    }

    @GetMapping("/add")
    public String addBookForm() {
        return "add-book";
    }

    @PostMapping("/add")
    public String addBook(
            @RequestParam String title,
            @RequestParam String author,
            @RequestParam String genre,
            @RequestParam Long year,
            @RequestParam Long pages) {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setGenre(genre);
        book.setYearOfPublication(year);
        book.setNumberOfPages(pages);
        bookService.addBook(book);
        return "redirect:/books";
    }

    @PostMapping("/reserve/{id}")
    public String reserveBook(@PathVariable Long id) {
        bookService.reserveBook(id);
        return "redirect:/books";
    }
}

