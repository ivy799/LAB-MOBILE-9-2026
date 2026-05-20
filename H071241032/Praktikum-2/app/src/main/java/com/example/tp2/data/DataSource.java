package com.example.tp2.data;

import com.example.tp2.R;
import com.example.tp2.models.Feed;
import com.example.tp2.models.Highlight;

import java.util.ArrayList;

public class DataSource {

    public static ArrayList<Feed> getHomeFeeds() {
        ArrayList<Feed> feeds = new ArrayList<>();

        initProfileFeeds();

        // Mengambil postingan dari profil pribadi agar beranda sinkron otomatis
        if (profileFeeds.size() > 0) feeds.add(profileFeeds.get(0));

        // Menggunakan nama akun asli teman-temanmu
        feeds.add(new Feed(R.drawable.profil_ryan, "ryan", R.drawable.post_ryan, "Mode Ambu emang keliatan kejam yak?"));
        feeds.add(new Feed(R.drawable.profile_sammi, "sammi", R.drawable.post_dua_sammi, "Mau gua genjutsu atau amaterasu?"));

        if (profileFeeds.size() > 1) feeds.add(profileFeeds.get(1));

        feeds.add(new Feed(R.drawable.profil_puad, "puad", R.drawable.post_puad, "Peperangan akan dimulai..."));
        feeds.add(new Feed(R.drawable.profil_yoga, "yoga", R.drawable.post_yoga, "Bagusnya berapa periode nih?"));

        if (profileFeeds.size() > 2) feeds.add(profileFeeds.get(2));

        feeds.add(new Feed(R.drawable.profil_najib, "najib", R.drawable.post_najib, "Pamer dulu ah susano'o kece ni"));
        feeds.add(new Feed(R.drawable.profile_sammi, "sammi", R.drawable.post_satu_sammi, "Kece ga boy??"));

        return feeds;
    }

    public static ArrayList<Feed> profileFeeds = new ArrayList<>();

    public static void initProfileFeeds() {
        if (profileFeeds.isEmpty()) {
            profileFeeds.add(new Feed(R.drawable.profil_didit, "didit_iqbal", R.drawable.post_satu, "Pain Bajingan lagi nyerang desa jir"));
            profileFeeds.add(new Feed(R.drawable.profil_didit, "didit_iqbal", R.drawable.post_dua, "Emang juara nih ramen, terbaik dah"));
            profileFeeds.add(new Feed(R.drawable.profil_didit, "didit_iqbal", R.drawable.post_tiga, "Kalem Kuramaa!"));
            profileFeeds.add(new Feed(R.drawable.profil_didit, "didit_iqbal", R.drawable.post_empat, "Kapan ya diakui di desa sendiri"));
            profileFeeds.add(new Feed(R.drawable.profil_didit, "didit_iqbal", R.drawable.post_lima, "Masuk rs gara gara akatsuki kampret..."));
        }
    }

    // Sorotan kustom di bawah 6 item untuk masing-masing teman
    public static ArrayList<Highlight> getHighlights(String username) {
        ArrayList<Highlight> highlights = new ArrayList<>();

        if (username == null || username.equals("didit_iqbal")) {
            highlights.add(new Highlight(R.drawable.highlight_team7, "Team 7"));
            highlights.add(new Highlight(R.drawable.highlight_me, "Saya"));
            highlights.add(new Highlight(R.drawable.highlight_sasuke, "Saskeee"));
            highlights.add(new Highlight(R.drawable.highlight_konoha, "Konohagakure"));
            highlights.add(new Highlight(R.drawable.highlight_baryon, "Baryon Mode"));
            highlights.add(new Highlight(R.drawable.highlight_nonton, "Nonton"));
            highlights.add(new Highlight(R.drawable.highlight_go, "Lestgo!"));
            return highlights;
        }

        switch (username) {
            case "ryan":
                highlights.add(new Highlight(R.drawable.k1, "Anbu"));
                highlights.add(new Highlight(R.drawable.k2, "Kamui"));
                highlights.add(new Highlight(R.drawable.k3, "Chidori"));
                break;

            case "sammi":
                highlights.add(new Highlight(R.drawable.ic_launcher_background, "UI/UX"));
                highlights.add(new Highlight(R.drawable.ic_launcher_background, "Chill"));
                highlights.add(new Highlight(R.drawable.ic_launcher_background, "Café"));
                break;

            case "puad":
                highlights.add(new Highlight(R.drawable.m1, "Juubidara"));
                highlights.add(new Highlight(R.drawable.m2, "Solo"));
                highlights.add(new Highlight(R.drawable.m3, "Mode Badas"));
                break;

            case "yoga":
                highlights.add(new Highlight(R.drawable.g2, "Mode Jincuriki"));
                highlights.add(new Highlight(R.drawable.g3, "Me"));
                highlights.add(new Highlight(R.drawable.g1, "Random"));
                break;

            case "najib":
                highlights.add(new Highlight(R.drawable.s1, "Susano'o"));
                highlights.add(new Highlight(R.drawable.s2, "Mangekyo"));
                highlights.add(new Highlight(R.drawable.s3, "Gambar"));
                highlights.add(new Highlight(R.drawable.s4, "Me"));
                break;

            default:
                highlights.add(new Highlight(R.drawable.ic_launcher_background, "Story"));
                highlights.add(new Highlight(R.drawable.ic_launcher_background, "Dump"));
                break;
        }

        return highlights;
    }
}