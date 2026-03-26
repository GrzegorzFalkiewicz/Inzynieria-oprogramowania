package vod.repository.mem;

import vod.model.Library;
import vod.model.Author;
import vod.model.Book;

import java.util.ArrayList;
import java.util.List;

class SampleData {

    static List<Library> libraries = new ArrayList<>();
    static List<Author> authors = new ArrayList<>();
    static List<Book> books = new ArrayList<>();

    static {

        // ---- AUTHORS ----
        Author sapkowski = new Author(1, "Andrzej", "Sapkowski");
        Author tolkien = new Author(2, "J.R.R.", "Tolkien");
        Author orwell = new Author(3, "George", "Orwell");
        Author rowling = new Author(4, "J.K.", "Rowling");

        // ---- BOOKS ----
        // cover: tu mozesz dac dowolne linki/placeholdery - wazne, ze to String
        Book witcher = new Book(1, "The Witcher: The Last Wish",
                "https://example.com/covers/witcher-last-wish.jpg",
                sapkowski, 4.7f);

        Book witcher2 = new Book(2, "The Witcher: Sword of Destiny",
                "https://example.com/covers/witcher-sword-of-destiny.jpg",
                sapkowski, 4.6f);

        Book lotr = new Book(3, "The Lord of the Rings",
                "https://example.com/covers/lotr.jpg",
                tolkien, 4.9f);

        Book hobbit = new Book(4, "The Hobbit",
                "https://example.com/covers/hobbit.jpg",
                tolkien, 4.8f);

        Book nineteen = new Book(5, "1984",
                "https://example.com/covers/1984.jpg",
                orwell, 4.5f);

        Book animal = new Book(6, "Animal Farm",
                "https://example.com/covers/animal-farm.jpg",
                orwell, 4.4f);

        Book hp1 = new Book(7, "Harry Potter and the Philosopher's Stone",
                "https://example.com/covers/hp1.jpg",
                rowling, 4.6f);

        Book hp2 = new Book(8, "Harry Potter and the Chamber of Secrets",
                "https://example.com/covers/hp2.jpg",
                rowling, 4.5f);

        // ---- BIND BOOK <-> AUTHOR (1..many) ----
        bind(witcher, sapkowski);
        bind(witcher2, sapkowski);

        bind(lotr, tolkien);
        bind(hobbit, tolkien);

        bind(nineteen, orwell);
        bind(animal, orwell);

        bind(hp1, rowling);
        bind(hp2, rowling);

        // ---- LIBRARIES ----
        Library bibliotekaSlaska = new Library(1, "Slaska", "https://example.com/logos/bsl.png");
        Library bibliotekaJagiellonska = new Library(2, "Biblioteka Jagiellonska", "https://example.com/logos/bj.png");
        Library bibliotekaNarodowa = new Library(3, "Biblioteka Narodowa", "https://example.com/logos/bn.png");
        Library bibliotekaMiejska = new Library(4, "Biblioteka Miejska", "https://example.com/logos/bm.png");

        // ---- BIND LIBRARY <-> BOOK (many..many) ----
        // Biblioteka Slaska
        bind(bibliotekaSlaska, witcher);
        bind(bibliotekaSlaska, nineteen);
        bind(bibliotekaSlaska, hp1);

        // Biblioteka Jagiellonska
        bind(bibliotekaJagiellonska, lotr);
        bind(bibliotekaJagiellonska, hobbit);
        bind(bibliotekaJagiellonska, witcher2);

        // Biblioteka Narodowa
        bind(bibliotekaNarodowa, lotr);
        bind(bibliotekaNarodowa, nineteen);
        bind(bibliotekaNarodowa, animal);

        // Biblioteka Miejska
        bind(bibliotekaMiejska, hp1);
        bind(bibliotekaMiejska, hp2);
        bind(bibliotekaMiejska, hobbit);

        // ---- LISTS ----
        books.add(witcher);
        books.add(witcher2);
        books.add(lotr);
        books.add(hobbit);
        books.add(nineteen);
        books.add(animal);
        books.add(hp1);
        books.add(hp2);

        authors.add(sapkowski);
        authors.add(tolkien);
        authors.add(orwell);
        authors.add(rowling);

        libraries.add(bibliotekaSlaska);
        libraries.add(bibliotekaJagiellonska);
        libraries.add(bibliotekaNarodowa);
        libraries.add(bibliotekaMiejska);
    }

    private static void bind(Library library, Book book) {
        library.addBook(book);
        book.addLibrary(library);
    }

    private static void bind(Book book, Author author) {
        author.addBook(book);
        book.setAuthor(author);
    }
}