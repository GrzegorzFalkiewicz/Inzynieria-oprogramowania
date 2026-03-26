package vod.web.rest;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import vod.service.BookService;

@Component
public class BookDtoValidator implements Validator {

    private final BookService bookService;

    public BookDtoValidator(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return BookDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        BookDto bookDto = (BookDto) target;

        if (bookDto.getAuthorId() == null) {
            return;
        }

        if (bookService.getAuthorById(bookDto.getAuthorId()) == null) {
            errors.rejectValue("authorId", "book.authorId.notfound");
        }
    }
}