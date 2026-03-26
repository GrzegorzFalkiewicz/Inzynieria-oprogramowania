package vod.web.ui;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import vod.service.BookService;

@Controller
public class AuthorController {

    private final BookService bookService;

    public AuthorController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/authorsView")
    public String getAuthorsView(Model model) {
        model.addAttribute("title", "Lista autorow");
        model.addAttribute("tip", "Kliknij autora, aby zobaczyc jego ksiazki");
        model.addAttribute("authors", bookService.getAllAuthors());
        return "authorsView";
    }
}