package vod.web.rest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;
import vod.model.Book;
import vod.model.Library;
import vod.service.BookService;
import vod.service.LibraryService;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("/webapi")
public class LibraryRest {

    private final LibraryService libraryService;
    private final BookService bookService;
    private final MessageSource messageSource;
    private final LocaleResolver localeResolver;

    public LibraryRest(LibraryService libraryService,
                       BookService bookService,
                       MessageSource messageSource,
                       LocaleResolver localeResolver) {
        this.libraryService = libraryService;
        this.bookService = bookService;
        this.messageSource = messageSource;
        this.localeResolver = localeResolver;
    }

    @GetMapping("/libraries")
    public List<Library> getLibraries(
            @RequestParam(value = "phrase", required = false) String phrase,
            @RequestHeader(value = "custom-header", required = false) String customHeader,
            @RequestHeader(value = "Cookie", required = false) String cookieHeader
    ) {
        log.info("REST GET /webapi/libraries");
        log.info("phrase param: " + phrase);
        log.info("custom header param: " + customHeader);
        log.info("some cookie value: " + cookieHeader);

        if ("foo".equalsIgnoreCase(phrase)) {
            throw new IllegalArgumentException("phrase cannot be foo");
        }

        List<Library> libraries = libraryService.getAllLibraries();

        if (phrase != null && !phrase.isBlank()) {
            libraries = libraries.stream()
                    .filter(c -> c.getName() != null && c.getName().toLowerCase().contains(phrase.toLowerCase()))
                    .collect(Collectors.toList());
        }

        log.info(libraries.size() + " libraries found");
        return libraries;
    }

    @GetMapping("/libraries/{id}")
    public ResponseEntity<Library> getLibraryById(@PathVariable("id") int id) {
        log.info("REST GET /webapi/libraries/" + id);

        Library library = libraryService.getLibraryById(id);

        if (library == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(library);
    }

    @PostMapping("/libraries")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addLibrary(@Valid @RequestBody Library library,
                                        Errors errors,
                                        HttpServletRequest request) {
        log.info("REST POST /webapi/libraries");
        log.info("Trying to add library: " + library.getName());

        if (errors.hasErrors()) {
            Locale locale = localeResolver.resolveLocale(request);

            String errorMessage = errors.getAllErrors().stream()
                    .map(error -> messageSource.getMessage(error, locale))
                    .reduce("", (a, b) -> a + b + " ");

            return ResponseEntity.badRequest().body(errorMessage);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(libraryService.add(library));
    }

    @GetMapping("/books/{bookId}/libraries")
    public ResponseEntity<List<Library>> getLibrariesByBook(@PathVariable("bookId") int bookId) {
        log.info("REST GET /webapi/books/" + bookId + "/libraries");

        Book book = bookService.getBookById(bookId);

        if (book == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(libraryService.getLibrariesByBook(book));
    }

    @GetMapping("/libraries/{id}/books")
    public ResponseEntity<List<Book>> getBooksInLibrary(@PathVariable("id") int id) {
        log.info("REST GET /webapi/libraries/" + id + "/books");

        Library library = libraryService.getLibraryById(id);

        if (library == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(libraryService.getBooksInLibrary(library));
    }
}