package com.example.tuprak_2.model;

import com.example.recycleview.R;
import java.util.ArrayList;
import java.util.List;

public class DataDummy {

    // ─── USERS ──────────────────────────────────────────────────────────────
    public static List<User> getUsers() {
        List<User> users = new ArrayList<>();

        users.add(new User("u1", "alex.wanderer", "Alex Wanderer",
                "📸 Fotografer & Traveler | Keliling dunia satu frame\nMakassar, Indonesia",
                R.drawable.profile_1, 0, 12400, 320));

        users.add(new User("u2", "nina.cook", "Nina Ramadhani",
                "👩‍🍳 Chef rumahan | Resep nusantara & fusion\nSuka bereksperimen di dapur",
                R.drawable.profile_2, 0, 8750, 210));

        users.add(new User("u3", "budi.tech", "Budi Santoso",
                "💻 Software Engineer | Open source enthusiast\nCode. Coffee. Repeat.",
                R.drawable.profile_3, 0, 3200, 98));

        users.add(new User("u4", "sari.art", "Sari Indah",
                "🎨 Ilustrator & Digital Artist\nKomisi terbuka | DM untuk info",
                R.drawable.profile_4, 0, 21000, 150));

        users.add(new User("u5", "rama.fitness", "Rama Putra",
                "💪 Personal Trainer | Nutrisi & Olahraga\nHelping you become the best version",
                R.drawable.profile_5, 0, 15600, 430));

        users.add(new User("u6", "dewi.traveler", "Dewi Lestari",
                "✈️ Travel Blogger | 30 negara & counting\nIG travel: setiap sudut dunia",
                R.drawable.profile_6, 0, 45000, 280));

        users.add(new User("u7", "andi.photo", "Andi Pratama",
                "📷 Wedding & Portrait Photographer\nMoments worth remembering",
                R.drawable.profile_7, 0, 9800, 175));

        users.add(new User("u8", "lisa.fashion", "Lisa Maharani",
                "👗 Fashion Stylist | Beauty Enthusiast\nStyle is a way to say who you are",
                R.drawable.profile_8, 0, 33000, 520));

        users.add(new User("u9", "hendra.music", "Hendra Gunawan",
                "🎸 Musisi | Singer-Songwriter\nNew single out now!",
                R.drawable.profile_9, 0, 7400, 310));

        users.add(new User("u10", "maya.garden", "Maya Kusuma",
                "🌿 Urban Gardener | Plant Mom\nMenanam, merawat, mencintai alam",
                R.drawable.profile_10, 0, 11200, 395));

        // set posts & highlights untuk masing-masing user
        for (User u : users) {
            u.setPosts(getPostsForUser(u.getUserId(), u.getUsername(), u.getProfileImageRes()));
            u.setHighlights(getHighlightsForUser(u.getUserId()));
        }

        return users;
    }

    // ─── POSTS PER USER ──────────────────────────────────────────────────────
    public static List<Post> getPostsForUser(String userId, String username, int profileRes) {
        List<Post> posts = new ArrayList<>();

        switch (userId) {
            case "u1":
                posts.add(new Post("p1_1", userId, username, profileRes, R.drawable.post_nature1,
                        "Golden hour di Pantai Losari 🌅 Tidak ada kata capek kalau hasilnya sebegini indah", 234, 18, "2 jam lalu"));
                posts.add(new Post("p1_2", userId, username, profileRes, R.drawable.post_city1,
                        "Kota Makassar dari atas 🏙️ Amazing view setelah hiking 3 jam!", 412, 31, "1 hari lalu"));
                posts.add(new Post("p1_3", userId, username, profileRes, R.drawable.post_nature2,
                        "Raja Ampat never disappoints 🌊 #paradise #Indonesia", 891, 67, "3 hari lalu"));
                posts.add(new Post("p1_4", userId, username, profileRes, R.drawable.post_food1,
                        "Coto Makassar after a long day 🍲 Nothing hits different", 156, 9, "5 hari lalu"));
                posts.add(new Post("p1_5", userId, username, profileRes, R.drawable.post_street1,
                        "Street photography di Pasar Butung 📸 Cerita rakyat yang tak pernah basi", 203, 14, "1 minggu lalu"));
                break;

            case "u2":
                posts.add(new Post("p2_1", userId, username, profileRes, R.drawable.post_food1,
                        "Ayam Rica-Rica homemade 🌶️ Resep rahasia nenek, kini kubagikan! Link di bio ya", 567, 45, "1 jam lalu"));
                posts.add(new Post("p2_2", userId, username, profileRes, R.drawable.post_food2,
                        "Pisang Epe Makassar 🍌 Classic street food yang selalu bikin kangen", 342, 28, "2 hari lalu"));
                posts.add(new Post("p2_3", userId, username, profileRes, R.drawable.post_nature1,
                        "Market day hunting bahan-bahan segar 🌿 Kualitas bahan = kualitas masakan", 198, 16, "4 hari lalu"));
                posts.add(new Post("p2_4", userId, username, profileRes, R.drawable.post_food3,
                        "Kue Putu Cangkiri 🫙 Nostalgia masa kecil yang kini kuracik ulang", 423, 39, "6 hari lalu"));
                posts.add(new Post("p2_5", userId, username, profileRes, R.drawable.post_food4,
                        "Pallu Butung, dessert khas Makassar 🥣 Manis dan menyegarkan!", 289, 21, "1 minggu lalu"));
                break;

            default:
                posts.add(new Post("p" + userId + "_1", userId, username, profileRes, R.drawable.post_nature1,
                        "Hari yang indah untuk berbagi momen 📸✨", 120 + userId.hashCode() % 200, 8, "3 jam lalu"));
                posts.add(new Post("p" + userId + "_2", userId, username, profileRes, R.drawable.post_city1,
                        "Setiap momen punya ceritanya sendiri 🌟", 80 + userId.hashCode() % 150, 5, "1 hari lalu"));
                posts.add(new Post("p" + userId + "_3", userId, username, profileRes, R.drawable.post_food1,
                        "Alam selalu jadi inspirasi terbaik 🌿", 200 + userId.hashCode() % 100, 12, "2 hari lalu"));
                posts.add(new Post("p" + userId + "_4", userId, username, profileRes, R.drawable.post_street1,
                        "Bersyukur atas setiap kesempatan yang ada 🙏", 95 + userId.hashCode() % 180, 7, "4 hari lalu"));
                posts.add(new Post("p" + userId + "_5", userId, username, profileRes, R.drawable.post_nature2,
                        "Cerita belum selesai, masih banyak hal indah di depan 🚀", 310 + userId.hashCode() % 90, 20, "1 minggu lalu"));
                break;
        }

        return posts;
    }

    // ─── HOME FEED (gabungan semua user) ─────────────────────────────────────
    public static List<Post> getHomeFeed() {
        List<Post> feed = new ArrayList<>();
        List<User> users = getUsers();
        // ambil 2 post pertama dari setiap user → total 20 post di home
        for (User u : users) {
            List<Post> userPosts = u.getPosts();
            int count = Math.min(2, userPosts.size());
            for (int i = 0; i < count; i++) {
                feed.add(userPosts.get(i));
            }
        }
        return feed;
    }

    // ─── HIGHLIGHTS / STORY ──────────────────────────────────────────────────
    public static List<Story> getHighlightsForUser(String userId) {
        List<Story> stories = new ArrayList<>();
        String[] titles = {"Travel", "Food", "Daily", "Work", "Fun", "Friends", "Moments", "Best Of"};
        int[] covers = {
            R.drawable.post_nature1, R.drawable.post_food1,
            R.drawable.post_city1, R.drawable.post_street1,
            R.drawable.post_nature2, R.drawable.post_food2,
            R.drawable.post_food3, R.drawable.post_food4
        };
        int[] images = {
            R.drawable.story_1, R.drawable.story_2, R.drawable.story_3,
            R.drawable.story_4, R.drawable.story_5, R.drawable.story_6,
            R.drawable.story_7, R.drawable.story_8
        };

        for (int i = 0; i < 8; i++) {
            stories.add(new Story(
                "s_" + userId + "_" + i,
                userId,
                titles[i],
                covers[i % covers.length],
                images[i % images.length]
            ));
        }
        return stories;
    }
}
