package com.library.app.model;

import java.util.ArrayList;
import java.util.List;

public class BookRepository {

    private static BookRepository instance;
    private final List<Book> books;

    private BookRepository() {
        books = new ArrayList<>();
        loadDummyBooks();
    }

    public static BookRepository getInstance() {
        if (instance == null) {
            instance = new BookRepository();
        }
        return instance;
    }

    private void loadDummyBooks() {
        // Newest first (highest year first)
        books.add(new Book(
            "Atomic Habits",
            "James Clear",
            2023,
            "An easy and proven way to build good habits and break bad ones. James Clear reveals practical strategies to form habits that stick.",
            "Self-Help", 4.8f, "cover_atomic_habits"
        ));
        books.add(new Book(
            "Fourth Wing",
            "Rebecca Yarros",
            2023,
            "Violet Sorrengail enters the Riders Quadrant to train as a dragon rider, where the wrong choice means death and the right one ignites passion.",
            "Fantasy", 4.7f, "cover_fourth_wing"
        ));
        books.add(new Book(
            "Tomorrow, and Tomorrow, and Tomorrow",
            "Gabrielle Zevin",
            2022,
            "Two friends bond over video games and spend decades making art, finding love, and grappling with identity and mortality.",
            "Fiction", 4.6f, "cover_tomorrow"
        ));
        books.add(new Book(
            "Babel",
            "R.F. Kuang",
            2022,
            "A dark academic fantasy about a Chinese boy brought to Oxford to study translation magic — and the cost of belonging to an empire.",
            "Fantasy", 4.5f, "cover_babel"
        ));
        books.add(new Book(
            "Lessons in Chemistry",
            "Bonnie Garmus",
            2022,
            "A chemist becomes the star of a 1960s cooking show and inspires a generation of women to change their lives.",
            "Fiction", 4.6f, "cover_lessons_chemistry"
        ));
        books.add(new Book(
            "Project Hail Mary",
            "Andy Weir",
            2021,
            "A lone astronaut wakes with no memory in deep space. His mission: save humanity. His only companion: an alien.",
            "Sci-Fi", 4.9f, "cover_hail_mary"
        ));
        books.add(new Book(
            "The Midnight Library",
            "Matt Haig",
            2020,
            "Between life and death lies a library with infinite books — each revealing a different life Nora Seed could have lived.",
            "Fiction", 4.4f, "cover_midnight_library"
        ));
        books.add(new Book(
            "Piranesi",
            "Susanna Clarke",
            2020,
            "A man lives in a mysterious House of endless halls and tidal statues — until he discovers a secret that shatters his world.",
            "Mystery", 4.5f, "cover_piranesi"
        ));
        books.add(new Book(
            "The Invisible Life of Addie LaRue",
            "V.E. Schwab",
            2020,
            "A French woman makes a deal with the devil to live forever — but is cursed to be forgotten by everyone she meets.",
            "Fantasy", 4.4f, "cover_addie_larue"
        ));
        books.add(new Book(
            "Where the Crawdads Sing",
            "Delia Owens",
            2018,
            "A girl raised alone in the North Carolina marshes becomes a suspect in the murder of a local man.",
            "Mystery", 4.5f, "cover_crawdads"
        ));
        books.add(new Book(
            "The Name of the Wind",
            "Patrick Rothfuss",
            2007,
            "Kvothe, a legendary wizard, recounts the tale of his extraordinary life — his rise from orphan to the most feared magician alive.",
            "Fantasy", 4.8f, "cover_name_wind"
        ));
        books.add(new Book(
            "The Kite Runner",
            "Khaled Hosseini",
            2003,
            "A story of friendship, betrayal, and redemption across decades of Afghan history, from the fall of the monarchy through the Taliban regime.",
            "Fiction", 4.7f, "cover_kite_runner"
        ));
        books.add(new Book(
            "1984",
            "George Orwell",
            1949,
            "In a totalitarian future society, Winston Smith secretly rebels against the all-controlling Party and its leader Big Brother.",
            "Dystopia", 4.7f, "cover_1984"
        ));
        books.add(new Book(
            "Dune",
            "Frank Herbert",
            1965,
            "On the desert planet Arrakis, young Paul Atreides becomes the center of a cosmic conflict over the most valuable substance in the universe.",
            "Sci-Fi", 4.8f, "cover_dune"
        ));
        books.add(new Book(
            "To Kill a Mockingbird",
            "Harper Lee",
            1960,
            "Through a child's eyes, a father's moral courage in defending an innocent Black man in Depression-era Alabama challenges a town's prejudice.",
            "Classic", 4.8f, "cover_mockingbird"
        ));
        books.add(new Book(
            "The Psychology of Money",
            "Morgan Housel",
            2020,
            "Timeless lessons on wealth, greed, and happiness — exploring the quirky ways people think about money and how to make better financial decisions.",
            "Self-Help", 4.6f, "cover_psychology_money"
        ));
        books.add(new Book(
            "Educated",
            "Tara Westover",
            2018,
            "A memoir about a woman who grows up in a survivalist family in Idaho and never attends school — yet earns a PhD from Cambridge.",
            "Biography", 4.7f, "cover_educated"
        ));
    }

    public List<Book> getAllBooks() {
        return books;
    }

    public List<Book> getLikedBooks() {
        List<Book> liked = new ArrayList<>();
        for (Book b : books) {
            if (b.isLiked()) liked.add(b);
        }
        return liked;
    }

    public void addBook(Book book) {
        books.add(0, book); // newest first
    }

    public Book getBookById(int id) {
        for (Book b : books) {
            if (b.getId() == id) return b;
        }
        return null;
    }

    public List<String> getAllGenres() {
        List<String> genres = new ArrayList<>();
        genres.add("All");
        for (Book b : books) {
            if (!genres.contains(b.getGenre())) {
                genres.add(b.getGenre());
            }
        }
        return genres;
    }
}
