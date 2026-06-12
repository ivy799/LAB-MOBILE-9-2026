package com.example.libraryapp.util;

import com.example.libraryapp.R;
import com.example.libraryapp.model.Book;

import java.util.ArrayList;
import java.util.List;

public class BookRepository {
    private static BookRepository instance;
    private List<Book> books;

    private BookRepository() {
        books = new ArrayList<>();
        loadDummyData();
    }

    public static BookRepository getInstance() {
        if (instance == null) {
            instance = new BookRepository();
        }
        return instance;
    }

    private void loadDummyData() {
        // Fix timestamps so newest appears first
        long base = System.currentTimeMillis();

        Book b;

        b = new Book("Bumi", "Tere Liye", 2014,
                "Raib, seorang gadis 15 tahun, memiliki kemampuan menghilang. Suatu hari ia terseret ke dunia paralel bersama sahabatnya, Seli dan Ali. Petualangan luar biasa dimulai.",
                "Fantasy", 4.5f,
                "Salah satu karya terbaik Tere Liye. World-building yang kaya dan karakter yang mudah dicintai. Wajib baca untuk penggemar fantasy lokal.",
                R.drawable.cover_bumi);
        b.setAddedTime(base - 1000); books.add(b);

        b = new Book("Laskar Pelangi", "Andrea Hirata", 2005,
                "Kisah sepuluh anak dari keluarga miskin di Belitung yang berjuang mendapatkan pendidikan. Penuh semangat, haru, dan harapan.",
                "Drama", 4.8f,
                "Novel inspiratif yang mengubah cara pandang banyak orang tentang pendidikan dan mimpi. Karya ikonik sastra Indonesia.",
                R.drawable.cover_laskar);
        b.setAddedTime(base - 2000); books.add(b);

        b = new Book("Dilan 1990", "Pidi Baiq", 2014,
                "Milea menceritakan masa SMA-nya di Bandung tahun 1990, bertemu dengan Dilan — cowok motor yang unik, puitis, dan tidak terduga.",
                "Romance", 4.3f,
                "Gaya bercerita yang ringan dan manis. Dialog Dilan yang ikonik sukses bikin baper jutaan pembaca. Cocok untuk bacaan santai.",
                R.drawable.cover_dilan);
        b.setAddedTime(base - 3000); books.add(b);

        b = new Book("Atomic Habits", "James Clear", 2018,
                "Panduan praktis membentuk kebiasaan baik dan menghilangkan kebiasaan buruk, berdasarkan riset ilmu perilaku dan psikologi.",
                "Self-Help", 4.9f,
                "Buku self-help terbaik yang pernah ada. Konsep 1% improvement sangat applicable di kehidupan nyata. Sudah mengubah jutaan orang.",
                R.drawable.cover_atomic);
        b.setAddedTime(base - 4000); books.add(b);

        b = new Book("Pulang", "Tere Liye", 2015,
                "Bujang, anak dari pedalaman Sumatera, terlibat dalam dunia shadow economy yang keras. Kisah tentang balas dendam, keluarga, dan identitas.",
                "Action", 4.6f,
                "Lebih gelap dan dewasa dari karya Tere Liye lainnya. Aksi yang intens dengan pesan moral yang kuat di baliknya.",
                R.drawable.cover_pulang);
        b.setAddedTime(base - 5000); books.add(b);

        b = new Book("Harry Potter and the Sorcerer's Stone", "J.K. Rowling", 1997,
                "Harry Potter, bocah yatim piatu, mendapat surat dari Hogwarts — sekolah sihir. Petualangan magis pun dimulai.",
                "Fantasy", 4.9f,
                "Masterpiece yang mendefinisikan satu generasi. Dunia Hogwarts terasa nyata dan setiap karakter memiliki kedalaman tersendiri.",
                R.drawable.cover_hp);
        b.setAddedTime(base - 6000); books.add(b);

        b = new Book("Rich Dad Poor Dad", "Robert Kiyosaki", 1997,
                "Perbandingan pola pikir keuangan antara ayah miskin dan ayah kaya. Mengajarkan cara berpikir tentang uang, investasi, dan aset.",
                "Finance", 4.4f,
                "Membuka wawasan tentang literasi keuangan. Wajib baca sebelum masuk dunia kerja. Konsepnya simpel tapi sangat impactful.",
                R.drawable.cover_richdad);
        b.setAddedTime(base - 7000); books.add(b);

        b = new Book("The Alchemist", "Paulo Coelho", 1988,
                "Santiago, seorang penggembala dari Spanyol, mengejar mimpinya menemukan harta karun di Mesir. Perjalanan yang penuh makna filosofis.",
                "Fiction", 4.7f,
                "Novel yang terasa seperti perjalanan spiritual. Setiap kalimat mengandung wisdom yang dalam. Buku yang selalu relevan di setiap usia.",
                R.drawable.cover_alchemist);
        b.setAddedTime(base - 8000); books.add(b);

        b = new Book("Negeri 5 Menara", "Ahmad Fuadi", 2009,
                "Alif meninggalkan Minangkabau untuk belajar di pesantren Gontor. Di sana ia bertemu lima sahabat yang bersama-sama bermimpi tentang dunia.",
                "Drama", 4.5f,
                "Novel yang menginspirasi dengan latar pesantren yang autentik. Motto 'man jadda wajada' akan terus terngiang setelah membaca ini.",
                R.drawable.cover_negeri5);
        b.setAddedTime(base - 9000); books.add(b);

        b = new Book("1984", "George Orwell", 1949,
                "Di negara totaliter Oceania, Winston Smith bekerja untuk pemerintah yang mengontrol kebenaran. Ia mulai memberontak diam-diam.",
                "Dystopia", 4.8f,
                "Klasik distopia yang semakin relevan di era modern. Konsep Big Brother dan Doublethink terasa begitu nyata dan menakutkan.",
                R.drawable.cover_1984);
        b.setAddedTime(base - 10000); books.add(b);

        b = new Book("Sapiens", "Yuval Noah Harari", 2011,
                "Sejarah umat manusia dari Homo Sapiens pertama hingga era modern, dikupas dari perspektif biologi, sejarah, dan sosiologi.",
                "Non-Fiction", 4.7f,
                "Mengubah cara pandang tentang sejarah manusia secara fundamental. Ditulis dengan gaya yang sangat accessible meski topiknya berat.",
                R.drawable.cover_sapiens);
        b.setAddedTime(base - 11000); books.add(b);

        b = new Book("Tenggelamnya Kapal Van Der Wijck", "Hamka", 1938,
                "Zainuddin mencintai Hayati, tapi perbedaan adat dan status sosial memisahkan mereka. Kisah cinta yang tragis berlatar Minangkabau.",
                "Romance", 4.6f,
                "Karya sastra klasik Indonesia yang tak lekang oleh waktu. Menggugah emosi dengan bahasa yang indah dan plot yang memilukan.",
                R.drawable.cover_vanderwijck);
        b.setAddedTime(base - 12000); books.add(b);

        b = new Book("The Psychology of Money", "Morgan Housel", 2020,
                "Bagaimana psikologi manusia memengaruhi keputusan keuangan. Berisi cerita dan pelajaran tentang hubungan unik antara manusia dan uang.",
                "Finance", 4.8f,
                "Buku finansial terbaik dalam dekade terakhir. Tidak membahas angka tapi perilaku — itulah yang membuatnya berbeda dan sangat powerful.",
                R.drawable.cover_psychmoney);
        b.setAddedTime(base - 13000); books.add(b);

        b = new Book("Perahu Kertas", "Dewi Lestari", 2009,
                "Kugy dan Keenan bertemu di Bandung. Dua jiwa seni yang saling jatuh cinta namun terhalang oleh pilihan hidup masing-masing.",
                "Romance", 4.4f,
                "Karya Dee yang paling romantis. Chemistry antara Kugy dan Keenan terasa sangat natural. Ending-nya memuaskan setelah perjalanan panjang.",
                R.drawable.cover_perahukrts);
        b.setAddedTime(base - 14000); books.add(b);

        b = new Book("Ikigai", "Héctor García & Francesc Miralles", 2016,
                "Rahasia panjang umur dan kebahagiaan orang Jepang di pulau Okinawa — ditemukan dalam konsep Ikigai: alasan untuk bangun pagi.",
                "Self-Help", 4.5f,
                "Bacaan ringan tapi penuh insight. Membuatmu merefleksikan tujuan hidupmu sendiri. Sangat cocok dibaca saat merasa kehilangan arah.",
                R.drawable.cover_ikigai);
        b.setAddedTime(base - 15000); books.add(b);

        b = new Book("Clean Code", "Robert C. Martin", 2008,
                "Panduan menulis kode yang bersih, mudah dibaca, dan mudah di-maintain. Wajib baca bagi setiap software developer.",
                "Technology", 4.6f,
                "Buku yang mengubah cara saya menulis kode. Prinsip-prinsipnya terasa seperti common sense, tapi jarang diterapkan sebelum membaca ini.",
                R.drawable.cover_cleancode);
        b.setAddedTime(base - 16000); books.add(b);

        b = new Book("Homo Deus", "Yuval Noah Harari", 2015,
                "Setelah menaklukkan penyakit dan kelaparan, apa agenda manusia selanjutnya? Harari mengeksplorasi masa depan umat manusia dengan AI dan bioteknologi.",
                "Non-Fiction", 4.6f,
                "Sekuel Sapiens yang lebih spekulatif tapi sama menariknya. Membuat kita mempertanyakan arti kemanusiaan di era teknologi.",
                R.drawable.cover_homodeus);
        b.setAddedTime(base - 17000); books.add(b);
    }

    public List<Book> getAllBooks() {
        // Sort by addedTime descending (newest first)
        List<Book> sorted = new ArrayList<>(books);
        sorted.sort((a, b) -> Long.compare(b.getAddedTime(), a.getAddedTime()));
        return sorted;
    }

    public List<Book> getLikedBooks() {
        List<Book> liked = new ArrayList<>();
        for (Book b : books) {
            if (b.isLiked()) liked.add(b);
        }
        return liked;
    }

    public List<Book> getBooksByGenre(String genre) {
        if (genre == null || genre.equals("All")) return getAllBooks();
        List<Book> filtered = new ArrayList<>();
        for (Book b : getAllBooks()) {
            if (b.getGenre().equals(genre)) filtered.add(b);
        }
        return filtered;
    }

    public List<String> getGenres() {
        List<String> genres = new ArrayList<>();
        genres.add("All");
        for (Book b : books) {
            if (!genres.contains(b.getGenre())) genres.add(b.getGenre());
        }
        return genres;
    }

    public void addBook(Book book) {
        books.add(0, book);
    }

    public Book getBookById(int id) {
        for (Book b : books) {
            if (b.getId() == id) return b;
        }
        return null;
    }
}
