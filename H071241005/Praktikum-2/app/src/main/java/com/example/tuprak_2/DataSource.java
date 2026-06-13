package com.example.tuprak_2;

import java.util.ArrayList;

public class DataSource {
    // Data Home (Min 10 item)
    public static ArrayList<Post> getHomeFeeds() {
        ArrayList<Post> feeds = new ArrayList<>();
        feeds.add(new Post("andi_hidayat", R.drawable.profile, R.drawable.jpg_feed1, "Menikmati senja di pinggir pantai #sunset #vibes"));
        feeds.add(new Post("budi_santoso", R.drawable.profile, R.drawable.jpg_feed2, "Kopi pagi ini sungguh nikmat ☕️ #coffee #morning"));
        feeds.add(new Post("citra_dewi", R.drawable.profile, R.drawable.jpg_feed3, "Jalan-jalan sore di taman kota 🌳 #nature #refreshing"));
        feeds.add(new Post("dina_lestari", R.drawable.profile, R.drawable.jpg_feed4, "Mencoba resep baru hari ini, enak banget! 🍰 #baking #foodie"));
        feeds.add(new Post("eko_prasetyo", R.drawable.profile, R.drawable.jpg_feed5, "Olahraga pagi biar badan tetap fit 💪 #workout #healthy"));
        feeds.add(new Post("fajar_ramadhan", R.drawable.profile, R.drawable.jpg_feed6, "Membaca buku di akhir pekan 📚 #reading #weekend"));
        feeds.add(new Post("gita_permata", R.drawable.profile, R.drawable.jpg_feed7, "Pemandangan gunung yang luar biasa ⛰️ #mountain #travel"));
        feeds.add(new Post("hadi_wijaya", R.drawable.profile, R.drawable.jpg_feed8, "Lagi asik main game bareng teman 🎮 #gaming #friends"));
        feeds.add(new Post("indah_sari", R.drawable.profile, R.drawable.jpg_feed9, "Bunga-bunga mulai bermekaran 🌸 #spring #flowers"));
        feeds.add(new Post("joko_susilo", R.drawable.profile, R.drawable.jpg_feed10, "Makan siang dengan menu favorit 🍲 #lunch #delicious"));
        return feeds;
    }

    // Data Profile Posts (Bisa bertambah) - Min 5 Item awal
    public static ArrayList<Post> profilePosts = new ArrayList<>();
    static {
        profilePosts.add(new Post("my_profile", R.drawable.profile, R.drawable.jpg_postingan1, "Memori indah liburan tahun lalu ✈️"));
        profilePosts.add(new Post("my_profile", R.drawable.profile, R.drawable.jpg_postingan2, "Fokus pada tujuan hidup 🎯"));
        profilePosts.add(new Post("my_profile", R.drawable.profile, R.drawable.jpg_postingan3, "Bersyukur atas segala nikmat yang ada 🙏"));
        profilePosts.add(new Post("my_profile", R.drawable.profile, R.drawable.jpg_postingan4, "Terus berkarya tanpa batas 🎨"));
        profilePosts.add(new Post("my_profile", R.drawable.profile, R.drawable.jpg_postingan5, "Kebahagiaan itu sederhana 😊"));
    }

    // Data Highlight Stories (Min 7 item)
    public static ArrayList<Highlight> getHighlights() {
        ArrayList<Highlight> highlights = new ArrayList<>();
        highlights.add(new Highlight("campus", R.drawable.highlights1));
        highlights.add(new Highlight("setup", R.drawable.highlights2));
        highlights.add(new Highlight("code", R.drawable.highlights3));
        highlights.add(new Highlight("desain", R.drawable.highlights4));
        highlights.add(new Highlight("musik", R.drawable.highlights5));
        highlights.add(new Highlight("gunung", R.drawable.highlights6));
        highlights.add(new Highlight("food", R.drawable.highlights7));
        return highlights;
    }
}
