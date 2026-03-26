package vod.web.rest;

import lombok.extern.slf4j.Slf4j;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;
import vod.model.Author;
import vod.model.Book;
import vod.service.BookService;

import java.util.List;
import java.util.Locale;

@RestController
@Slf4j
@RequestMapping("/webapi")
public class BookRest {

    private final BookService bookService;
    private final MessageSource messageSource;
    private final LocaleResolver localeResolver;

    public BookRest(BookService bookService,
                    MessageSource messageSource,
                    LocaleResolver localeResolver) {
        this.bookService = bookService;
        this.messageSource = messageSource;
        this.localeResolver = localeResolver;
    }

    @GetMapping("/books")
    public List<Book> getBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") int id) {
        Book book = bookService.getBookById(id);

        if (book == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(book);
    }

    @PostMapping("/books")
    public ResponseEntity<?> addBook(@Valid @RequestBody BookDto bookDto,
                                     Errors errors,
                                     HttpServletRequest request) {
        if (errors.hasErrors()) {
            Locale locale = localeResolver.resolveLocale(request);

            String errorMessage = errors.getAllErrors().stream()
                    .map(error -> messageSource.getMessage(error, locale))
                    .reduce("", (a, b) -> a + b + " ");

            return ResponseEntity.badRequest().body(errorMessage);
        }

        Author author = bookService.getAuthorById(bookDto.getAuthorId());

        Book book = new Book();
        book.setTitle(bookDto.getTitle());
        book.setCover(bookDto.getCover());
        book.setRating(bookDto.getRating());
        book.setAuthor(author);

        Book createdBook = bookService.addBook(book);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
    }
}