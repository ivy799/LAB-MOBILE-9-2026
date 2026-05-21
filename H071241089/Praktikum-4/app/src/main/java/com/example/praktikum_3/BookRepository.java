package com.example.praktikum_3;

import java.util.ArrayList;
import java.util.List;

public class BookRepository {
    private static final List<Book> books = new ArrayList<>();

    static {
        // Adding 15 dummy books
        books.add(new Book("A Court of Thorns and Roses", "Sarah J. Maas", "2015", "A young woman is kidnapped by a faerie...", R.drawable.buku1));
        books.add(new Book("A Court of Mist and Fury", "Sarah J. Maas", "2016", "The second book in the series...", R.drawable.buku2));
        books.add(new Book("Funny Story", "Emily Henry", "2024", "A new romance from Emily Henry...", R.drawable.buku3));
        books.add(new Book("Atomic Habits", "James Clear", "2018", "An easy & proven way to build good habits...", R.drawable.buku1));
        books.add(new Book("The Alchemist", "Paulo Coelho", "1988", "A journey to find worldly treasures...", R.drawable.buku2));
        books.add(new Book("1984", "George Orwell", "1949", "A dystopian social science fiction novel...", R.drawable.buku3));
        books.add(new Book("To Kill a Mockingbird", "Harper Lee", "1960", "A story of race and justice in the South...", R.drawable.buku1));
        books.add(new Book("The Great Gatsby", "F. Scott Fitzgerald", "1925", "A tale of wealth, love, and tragedy...", R.drawable.buku2));
        books.add(new Book("Brave New World", "Aldous Huxley", "1932", "A dystopian future of genetic engineering...", R.drawable.buku3));
        books.add(new Book("The Hobbit", "J.R.R. Tolkien", "1937", "A fantasy novel and children's book...", R.drawable.buku1));
        books.add(new Book("The Catcher in the Rye", "J.D. Salinger", "1951", "A story of teenage rebellion...", R.drawable.buku2));
        books.add(new Book("Pride and Prejudice", "Jane Austen", "1813", "A romantic novel of manners...", R.drawable.buku3));
        books.add(new Book("The Little Prince", "Antoine de Saint-Exupéry", "1943", "A poetic tale about a young prince...", R.drawable.buku1));
        books.add(new Book("Moby Dick", "Herman Melville", "1851", "The narrative of Captain Ahab's quest...", R.drawable.buku2));
        books.add(new Book("The Book Thief", "Markus Zusak", "2005", "A story narrated by Death...", R.drawable.buku3));
    }

    public static List<Book> getBooks() {
        return books;
    }

    public static void addBook(Book book) {
        books.add(0, book); // Add to the top
    }

    public static void updateLikeStatus(String title, boolean isLiked) {
        for (Book book : books) {
            if (book.getTitle().equals(title)) {
                book.setLiked(isLiked);
                break;
            }
        }
    }
}
