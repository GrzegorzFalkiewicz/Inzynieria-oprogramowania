package vod.web.ui;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vod.model.Author;
import vod.model.Library;
import vod.service.BookService;
import vod.service.LibraryService;

@Controller
public class BookController {

    private final BookService bookService;
    private final LibraryService libraryService;

    public BookController(BookService bookService, LibraryService libraryService) {
        this.bookService = bookService;
        this.libraryService = libraryService;
    }

    @GetMapping("/booksView")
    public String getBooksView(@RequestParam(value = "libraryId", required = false) Integer libraryId,
                               @RequestParam(value = "authorId", required = false) Integer authorId,
                               Model model) {

        if (libraryId != null) {
            Library library = libraryService.getLibraryById(libraryId);

            if (library != null) {
                model.addAttribute("title", "Ksiazki w bibliotece");
                model.addAttribute("tip", "Lista ksiazek dostepnych w wybranej bibliotece");
                model.addAttribute("books", libraryService.getBooksInLibrary(library));
            } else {
                model.addAttribute("title", "Ksiazki");
                model.addAttribute("tip", "Nie znaleziono biblioteki");
                model.addAttribute("books", bookService.getAllBooks());
            }
        } else if (authorId != null) {
            Author author = bookService.getAuthorById(authorId);

            if (author != null) {
                model.addAttribute("title", "Ksiazki autora");
                model.addAttribute("tip", "Lista ksiazek wybranego autora");
                model.addAttribute("books", bookService.getBooksByAuthor(author));
            } else {
                model.addAttribute("title", "Ksiazki");
                model.addAttribute("tip", "Nie znaleziono autora");
                model.addAttribute("books", bookService.getAllBooks());
            }
        } else {
            model.addAttribute("title", "Lista ksiazek");
            model.addAttribute("tip", "Mozesz przegladac wszystkie ksiazki lub filtrowac je po bibliotece i autorze");
            model.addAttribute("books", bookService.getAllBooks());
        }

        return "booksView";
    }
}