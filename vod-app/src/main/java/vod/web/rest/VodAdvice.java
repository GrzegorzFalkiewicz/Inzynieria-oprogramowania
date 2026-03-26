package vod.web.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
@ResponseBody
public class VodAdvice {

    private final LibraryValidator libraryValidator;
    private final BookDtoValidator bookDtoValidator;

    public VodAdvice(LibraryValidator libraryValidator, BookDtoValidator bookDtoValidator) {
        this.libraryValidator = libraryValidator;
        this.bookDtoValidator = bookDtoValidator;
    }

    @InitBinder("library")
    public void initLibraryBinder(WebDataBinder binder) {
        binder.addValidators(libraryValidator);
    }

    @InitBinder("bookDto")
    public void initBookDtoBinder(WebDataBinder binder) {
        binder.addValidators(bookDtoValidator);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        log.error("illegal argument provided", e);
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body(e.getMessage());
    }
}