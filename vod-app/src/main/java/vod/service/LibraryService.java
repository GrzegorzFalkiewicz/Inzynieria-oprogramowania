package vod.service;

import vod.model.Library;
import vod.model.Book;

import java.util.List;

public interface LibraryService {

    Library getLibraryById(int id);

    List<Library> getAllLibraries();

    List<Library> getLibrariesByBook(Book m);

    List<Book> getBooksInLibrary(Library c);

    Library add(Library c);
}