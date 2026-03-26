package vod.web.rest;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import vod.model.Library;
import vod.service.LibraryService;

@Component
public class LibraryValidator implements Validator {

    private final LibraryService libraryService;

    public LibraryValidator(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Library.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Library library = (Library) target;

        boolean duplicated = libraryService.getAllLibraries().stream()
                .anyMatch(l -> l.getName() != null
                        && library.getName() != null
                        && l.getName().equalsIgnoreCase(library.getName()));

        if (duplicated) {
            errors.rejectValue("name", "library.name.duplicated");
        }
    }
}