package vod;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import vod.model.Library;
import vod.service.LibraryService;

import java.util.List;

@Component
public class VodComponent {

    private final LibraryService libraryService;

    public VodComponent(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @PostConstruct
    public void init() {
        System.out.println("Let's find libraries!");

        List<Library> libraries = libraryService.getAllLibraries();
        System.out.println(libraries.size() + " libraries found:");
        libraries.forEach(System.out::println);
    }
}