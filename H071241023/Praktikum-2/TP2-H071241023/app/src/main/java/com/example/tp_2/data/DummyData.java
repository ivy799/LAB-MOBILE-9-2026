package com.example.tp_2.data;

import com.example.tp_2.R;
import com.example.tp_2.model.Feed;
import com.example.tp_2.model.Story;

import java.util.ArrayList;

public class DummyData {

    public static ArrayList<Feed> feeds = new ArrayList<>();
    public static ArrayList<Story> stories = new ArrayList<>();
    public static ArrayList<Feed> profileFeeds = new ArrayList<>();

    private static String getDrawableUri(int drawable) {
        return "android.resource://com.example.tp_2/" + drawable;
    }

    public static void initStory() {
        if (!stories.isEmpty()) return;

        // 7 item story sesuai requirement
        stories.add(new Story(R.drawable.post1, "You"));
        stories.add(new Story(R.drawable.post2, "asdfghijkkkk"));
        stories.add(new Story(R.drawable.post3, "_notthree"));
        stories.add(new Story(R.drawable.post4, "hppn.me"));
        stories.add(new Story(R.drawable.post5, "saaturdaydrip"));
        stories.add(new Story(R.drawable.post6, "2.0mort"));
        stories.add(new Story(R.drawable.post7, "kiyaschoice"));
    }

    public static void initData() {
        if (!feeds.isEmpty()) return;

        // FIX: minimal 10 item feed sesuai requirement (sebelumnya hanya 9)
        feeds.add(new Feed(getDrawableUri(R.drawable.post6),  "Sunset dulu gak sehh",        "bwakekoqq",       getDrawableUri(R.drawable.post1)));
        feeds.add(new Feed(getDrawableUri(R.drawable.post2),  "Seruduk nih",                  "asdfghijkkkk",    getDrawableUri(R.drawable.post1)));
        feeds.add(new Feed(getDrawableUri(R.drawable.post3),  "Push? menggg 🐱",              "_notthree",       getDrawableUri(R.drawable.post1)));
        feeds.add(new Feed(getDrawableUri(R.drawable.post8),  "Alah alah kemayune 😍",        "hppn.me",         getDrawableUri(R.drawable.post1)));
        feeds.add(new Feed(getDrawableUri(R.drawable.post5),  "😚",                           "saaturdaydrip",   getDrawableUri(R.drawable.post1)));
        feeds.add(new Feed(getDrawableUri(R.drawable.post4),  "Bukber kidss",                 "2.0mort",         getDrawableUri(R.drawable.post1)));
        feeds.add(new Feed(getDrawableUri(R.drawable.post7),  "Maba ceritanya",               "kiyaschoice",     getDrawableUri(R.drawable.post1)));
        feeds.add(new Feed(getDrawableUri(R.drawable.post1),  "Kami pernah di situ di posisimu~", "burgerchikenuget", getDrawableUri(R.drawable.post1)));
        feeds.add(new Feed(getDrawableUri(R.drawable.post9),  "Cisss",                        "iilllaaaa_",      getDrawableUri(R.drawable.post1)));
        feeds.add(new Feed(getDrawableUri(R.drawable.post2),  "Golden hour vibes ✨",          "uv_wxyzzz",   getDrawableUri(R.drawable.post1))); // ← FIX: item ke-10
    }

    public static void initProfileData() {
        if (!profileFeeds.isEmpty()) return;

        profileFeeds.add(new Feed(getDrawableUri(R.drawable.post1), "Caption 1", "bwakekoqq", getDrawableUri(R.drawable.post1)));
        profileFeeds.add(new Feed(getDrawableUri(R.drawable.post2), "Caption 2", "bwakekoqq", getDrawableUri(R.drawable.post1)));
        profileFeeds.add(new Feed(getDrawableUri(R.drawable.post3), "Caption 3", "bwakekoqq", getDrawableUri(R.drawable.post1)));
        profileFeeds.add(new Feed(getDrawableUri(R.drawable.post4), "Caption 4", "bwakekoqq", getDrawableUri(R.drawable.post1)));
        profileFeeds.add(new Feed(getDrawableUri(R.drawable.post5), "Caption 5", "bwakekoqq", getDrawableUri(R.drawable.post1)));
    }
}