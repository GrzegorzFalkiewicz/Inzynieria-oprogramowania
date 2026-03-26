package vod.web.ui;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vod.model.Book;
import vod.service.BookService;
import vod.service.LibraryService;

@Controller
public class LibraryController {

    private final LibraryService libraryService;
    private final BookService bookService;

    public LibraryController(LibraryService libraryService, BookService bookService) {
        this.libraryService = libraryService;
        this.bookService = bookService;
    }

    @GetMapping("/librariesView")
    public String getLibrariesView(@RequestParam(value = "bookId", required = false) Integer bookId,
                                   Model model) {

        if (bookId != null) {
            Book book = bookService.getBookById(bookId);

            if (book != null) {
                model.addAttribute("title", "Biblioteki dla ksiazki");
                model.addAttribute("tip", "Lista bibliotek, w ktorych znajduje sie wybrana ksiazka");
                model.addAttribute("libraries", libraryService.getLibrariesByBook(book));
            } else {
                model.addAttribute("title", "Biblioteki");
                model.addAttribute("tip", "Nie znaleziono ksiazki");
                model.addAttribute("libraries", libraryService.getAllLibraries());
            }
        } else {
            model.addAttribute("title", "Lista bibliotek");
            model.addAttribute("tip", "Wybierz biblioteke, aby zobaczyc dostepne ksiazki");
            model.addAttribute("libraries", libraryService.getAllLibraries());
        }

        return "librariesView";
    }
}