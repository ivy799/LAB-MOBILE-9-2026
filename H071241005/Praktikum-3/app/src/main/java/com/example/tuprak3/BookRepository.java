package com.example.tuprak3;

import java.util.ArrayList;
import java.util.List;

public class BookRepository {
    private static BookRepository instance;
    private List<Book> bookList;

    private BookRepository() {
        bookList = new ArrayList<>();
        addInitialBooks();
    }

    private void addInitialBooks() {
        // book_1: THE END - Your story isn't over yet
        bookList.add(new Book("1", "The End", "Inspirational", "2021", 
            "Your story isn't over yet. Sebuah pengingat bahwa setiap akhir hanyalah bab baru dalam hidupmu.", 
            "Self-Help", 4.5, R.drawable.book_1));
            
        // book_2: WAVES by Ingrid Chabbert & Carole Maurel
        bookList.add(new Book("2", "Waves", "Ingrid Chabbert", "2019", 
            "Kisah grafis yang emosional tentang menghadapi duka dan harapan yang datang seperti ombak.", 
            "Fiksi", 4.6, R.drawable.book_2));
            
        // book_3: SENSATION - Protect your DNA
        bookList.add(new Book("3", "Sensation", "Martin Grof", "2020", 
            "Protect your DNA. Misteri tentang identitas manusia dan rahasia genetik yang terkubur dalam.", 
            "Sci-Fi", 4.3, R.drawable.book_3));
            
        // book_4: RINDU by Tere Liye
        bookList.add(new Book("4", "Rindu", "Tere Liye", "2014", 
            "Tentang sebuah perjalanan panjang dengan kapal uap, membawa rindu yang harus diselesaikan di pelabuhan terakhir.", 
            "Fiksi", 4.8, R.drawable.book_4));
            
        // book_5: LOVERS BY THE SEA by Mimidaisy
        bookList.add(new Book("5", "Lovers By The Sea", "Mimidaisy", "2022", 
            "Pertemuan manis di tepi laut yang mengubah hidup dua jiwa selamanya dalam harmoni alam.", 
            "Romantis", 4.4, R.drawable.book_5));
            
        // book_6: HUJAN by Tere Liye
        bookList.add(new Book("6", "Hujan", "Tere Liye", "2016", 
            "Tentang persahabatan, cinta, dan melupakan kenangan di tengah dunia masa depan yang berubah drastis.", 
            "Fiksi", 4.9, R.drawable.book_6));
            
        // book_7: PETER PAN IN WONDERLAND by Jeni Conrad
        bookList.add(new Book("7", "Peter Pan in Wonderland", "Jeni Conrad", "2021", 
            "Ketika dua dunia ajaib bertabrakan dalam petualangan epik Peter Pan di tanah Wonderland.", 
            "Fantasi", 4.7, R.drawable.book_7));
            
        // book_8: THE LOST EMERALD OF BRIARWOOD by Vona Stewart
        bookList.add(new Book("8", "The Lost Emerald of Briarwood", "Vona Stewart", "2023", 
            "Petualangan mencari permata legendaris yang hilang di tengah misteri hutan Briarwood.", 
            "Petualangan", 4.5, R.drawable.book_8));
            
        // book_9: SEPORSI MIE AYAM SEBELUM MATI by Brian Khrisna
        bookList.add(new Book("9", "Seporsi Mie Ayam Sebelum Mati", "Brian Khrisna", "2023", 
            "Kisah tentang makna hidup dan harapan yang ditemukan lewat kesederhanaan semangkuk mie ayam.", 
            "Drama", 4.8, R.drawable.book_9));
            
        // book_10: SISI TERGELAP SURGA by Brian Khrisna
        bookList.add(new Book("10", "Sisi Tergelap Surga", "Brian Khrisna", "2022", 
            "Mengeksplorasi realitas kehidupan kota yang keras dan rahasia-rahasia yang tak pernah terucap.", 
            "Drama", 4.7, R.drawable.book_10));
            
        // book_11: NOBODY KNOWS BUT YOU by Anica Mrose Rissi
        bookList.add(new Book("11", "Nobody Knows But You", "Anica Mrose Rissi", "2020", 
            "The perfect lie begins as truth. Sebuah thriller psikologis tentang rahasia kelam yang menghantui.", 
            "Thriller", 4.6, R.drawable.book_11));
            
        // book_12: WHO AM I?
        bookList.add(new Book("12", "Who Am I?", "Unknown Author", "2018", 
            "Pencarian jati diri di tengah hiruk pikuk dunia yang semakin membingungkan.", 
            "Psikologi", 4.2, R.drawable.book_12));
            
        // book_13: BUNGKAM SUARA by J.S. Khairen
        bookList.add(new Book("13", "Bungkam Suara", "J.S. Khairen", "2021", 
            "Tentang luka yang tak bersuara dan keberanian untuk kembali bicara di tengah tekanan hidup.", 
            "Fiksi", 4.8, R.drawable.book_13));
            
        // book_14: BHOR by Author Name
        bookList.add(new Book("14", "Bhor", "Author Name", "2023", 
            "Some hearts learn to breathe again at dawn. Sebuah perjalanan musik dan pemulihan hati yang indah.", 
            "Romantis", 4.5, R.drawable.book_14));
            
        // book_15: BUMI MANUSIA by Pramoedya Ananta Toer
        bookList.add(new Book("15", "Bumi Manusia", "Pramoedya Ananta Toer", "1980", 
            "Mahakarya sastra Indonesia tentang perjuangan Minke di tengah kolonialisme Hindia Belanda.",
            "Sejarah", 5.0, R.drawable.book_15));
    }

    public static synchronized BookRepository getInstance() {
        if (instance == null) { instance = new BookRepository(); }
        return instance;
    }

    public List<Book> getAllBooks() { return bookList; }

    public void addBook(Book book) {
        bookList.add(0, book);
    }
}
