package com.example.tp3.utils;

import com.example.tp3.R;
import com.example.tp3.model.Book;
import java.util.ArrayList;

public class DataHelper {

    // Inilah tempat penyimpanan utama buku kita di memori
    public static ArrayList<Book> bookList = new ArrayList<>();

    // Method untuk mengisi 15 data dummy
    public static void initDummyData() {
        // Cek agar data tidak digandakan jika method ini terpanggil dua kali
        if (bookList.isEmpty()) {
            bookList.add(new Book("1", "Laskar Pelangi", "Andrea Hirata", "2005", "Kisah perjuangan 10 anak di Belitung dalam menempuh pendidikan di tengah keterbatasan.", R.mipmap.ic_launcher, null, 4.8, "Fiksi / Inspiratif"));
            bookList.add(new Book("2", "Bumi Manusia", "Pramoedya Ananta Toer", "1980", "Kisah cinta Minke dan Annelies di masa kolonial Belanda, sarat akan kritik sosial.", R.mipmap.ic_launcher, null, 4.9, "Fiksi Sejarah"));
            bookList.add(new Book("3", "Cantik Itu Luka", "Eka Kurniawan", "2002", "Kisah tragis Ayu Halimunda dan keturunannya yang memadukan mitos, sejarah, dan realisme magis.", R.mipmap.ic_launcher, null, 4.7, "Realisme Magis"));
            bookList.add(new Book("4", "Filosofi Teras", "Henry Manampiring", "2018", "Pengantar filsafat Stoisisme untuk menemukan ketenangan hidup di tengah kekacauan.", R.mipmap.ic_launcher, null, 4.6, "Pengembangan Diri"));
            bookList.add(new Book("5", "Pulang", "Tere Liye", "2015", "Perjalanan seorang anak laki-laki yang keluar dari kampung halamannya dan menghadapi kerasnya dunia bayangan.", R.mipmap.ic_launcher, null, 4.5, "Aksi / Petualangan"));

            bookList.add(new Book("6", "Hujan", "Tere Liye", "2016", "Kisah tentang persahabatan, cinta, dan perpisahan dengan latar dunia masa depan yang hancur karena bencana alam.", R.mipmap.ic_launcher, null, 4.5, "Fiksi Ilmiah / Romansa"));
            bookList.add(new Book("7", "Ronggeng Dukuh Paruk", "Ahmad Tohari", "1982", "Kisah penari ronggeng di sebuah desa terpencil yang terjebak dalam pusaran sejarah kelam Indonesia.", R.mipmap.ic_launcher, null, 4.8, "Fiksi Sejarah"));
            bookList.add(new Book("8", "Laut Bercerita", "Leila S. Chudori", "2017", "Kisah persahabatan, cinta, dan pengkhianatan sekelompok aktivis mahasiswa di era Orde Baru.", R.mipmap.ic_launcher, null, 4.9, "Fiksi Sejarah"));
            bookList.add(new Book("9", "Atomic Habits", "James Clear", "2018", "Cara mudah dan terbukti untuk membentuk kebiasaan baik dan menghilangkan kebiasaan buruk.", R.mipmap.ic_launcher, null, 4.8, "Pengembangan Diri"));
            bookList.add(new Book("10", "Sapiens", "Yuval Noah Harari", "2011", "Sejarah singkat umat manusia dari zaman batu hingga abad ke-21.", R.mipmap.ic_launcher, null, 4.7, "Sejarah / Sains"));

            bookList.add(new Book("11", "The Psychology of Money", "Morgan Housel", "2020", "Pelajaran abadi mengenai kekayaan, ketamakan, dan kebahagiaan dalam mengatur keuangan.", R.mipmap.ic_launcher, null, 4.7, "Keuangan / Bisnis"));
            bookList.add(new Book("12", "Gadis Kretek", "Ratih Kumala", "2012", "Penelusuran masa lalu keluarga pemilik pabrik kretek yang mengungkap rahasia dan kisah cinta terlarang.", R.mipmap.ic_launcher, null, 4.6, "Fiksi Sejarah"));
            bookList.add(new Book("13", "Orang-Orang Biasa", "Andrea Hirata", "2019", "Kisah sekelompok orang biasa yang merencanakan perampokan bank demi menyekolahkan anak yang cerdas.", R.mipmap.ic_launcher, null, 4.5, "Fiksi / Komedi"));
            bookList.add(new Book("14", "Dunia Sophie", "Jostein Gaarder", "1991", "Novel yang mengenalkan sejarah filsafat melalui kisah misterius seorang gadis remaja.", R.mipmap.ic_launcher, null, 4.6, "Filsafat / Fiksi"));
            bookList.add(new Book("15", "1984", "George Orwell", "1949", "Novel distopia klasik tentang bahaya totaliterisme dan pengawasan pemerintah yang ekstrem.", R.mipmap.ic_launcher, null, 4.8, "Fiksi Ilmiah / Distopia"));
        }
    }

    // Method untuk mengambil buku berdasarkan ID (Berguna nanti saat buka DetailActivity)
    public static Book getBookById(String id) {
        for (Book book : bookList) {
            if (book.getId().equals(id)) {
                return book;
            }
        }
        return null;
    }
}