package com.example.tuprak2.data;

import com.example.tuprak2.model.FeedModel;
import com.example.tuprak2.model.PostModel;
import com.example.tuprak2.model.StoryModel;

import java.util.ArrayList;

public class DataDummy {

    private static ArrayList<PostModel> userPosts = null;

    public static ArrayList<FeedModel> generateFeed() {
        ArrayList<FeedModel> feeds = new ArrayList<>();

        feeds.add(new FeedModel("https://xsgames.co/randomusers/assets/avatars/male/1.jpg", "johndoe",
                "https://picsum.photos/300?random=1", "Beautiful sunset by the sea \uD83C\uDF05", 1243, "2 hours ago"));
        feeds.add(new FeedModel("https://xsgames.co/randomusers/assets/avatars/female/2.jpg", "janedoe",
                "https://picsum.photos/300?random=2", "Coffee time \u2615 #morningvibes", 856, "3 hours ago"));
        feeds.add(new FeedModel("https://xsgames.co/randomusers/assets/avatars/male/3.jpg", "mike_photos",
                "https://picsum.photos/300?random=3", "Exploring the mountains today \uD83C\uDFD4\uFE0F", 2340, "5 hours ago"));
        feeds.add(new FeedModel("https://xsgames.co/randomusers/assets/avatars/female/4.jpg", "sarah.art",
                "https://picsum.photos/300?random=4", "New painting finished! \uD83C\uDFA8 What do you think?", 3421, "6 hours ago"));
        feeds.add(new FeedModel("https://xsgames.co/randomusers/assets/avatars/male/5.jpg", "travel_diary",
                "https://picsum.photos/300?random=5", "Bali is magical \u2728 #travel #bali", 5678, "8 hours ago"));
        feeds.add(new FeedModel("https://xsgames.co/randomusers/assets/avatars/female/6.jpg", "foodie_hub",
                "https://picsum.photos/300?random=6", "Homemade pasta \uD83C\uDF5D Recipe in bio!", 1890, "10 hours ago"));
        feeds.add(new FeedModel("https://xsgames.co/randomusers/assets/avatars/male/7.jpg", "fitness_king",
                "https://picsum.photos/300?random=7", "Leg day complete \uD83D\uDCAA #gymlife", 945, "12 hours ago"));
        feeds.add(new FeedModel("https://xsgames.co/randomusers/assets/avatars/female/8.jpg", "petlover",
                "https://picsum.photos/300?random=8", "Meet my new puppy! \uD83D\uDC36", 7654, "14 hours ago"));
        feeds.add(new FeedModel("https://xsgames.co/randomusers/assets/avatars/male/9.jpg", "code_ninja",
                "https://picsum.photos/300?random=9", "Shipping new features today \uD83D\uDE80 #developer", 432, "16 hours ago"));
        feeds.add(new FeedModel("https://xsgames.co/randomusers/assets/avatars/female/10.jpg", "music_addict",
                "https://picsum.photos/300?random=10", "Live concert vibes \uD83C\uDFB5\uD83C\uDFB8", 2100, "1 day ago"));

        return feeds;
    }

    public static ArrayList<PostModel> generatePosts() {
        if (userPosts == null) {
            userPosts = new ArrayList<>();

            userPosts.add(new PostModel("https://picsum.photos/300?random=101", "myprofile", "My first post! \uD83D\uDCF8"));
            userPosts.add(new PostModel("https://picsum.photos/300?random=102", "myprofile", "Weekend vibes \uD83C\uDF0A"));
            userPosts.add(new PostModel("https://picsum.photos/300?random=103", "myprofile", "Nature walk \uD83C\uDF3F"));
            userPosts.add(new PostModel("https://picsum.photos/300?random=104", "myprofile", "City lights \uD83C\uDF03"));
            userPosts.add(new PostModel("https://picsum.photos/300?random=105", "myprofile", "Throwback Thursday \uD83D\uDD19"));
        }
        return userPosts;
    }

    public static void addPost(PostModel post) {
        if (userPosts == null) {
            generatePosts();
        }
        userPosts.add(0, post); // Add at the beginning
    }

    public static ArrayList<StoryModel> generateStories() {
        ArrayList<StoryModel> stories = new ArrayList<>();

        stories.add(new StoryModel("https://picsum.photos/300?random=201", "Travel"));
        stories.add(new StoryModel("https://picsum.photos/300?random=202", "Food"));
        stories.add(new StoryModel("https://picsum.photos/300?random=203", "Music"));
        stories.add(new StoryModel("https://picsum.photos/300?random=204", "Coding"));
        stories.add(new StoryModel("https://picsum.photos/300?random=205", "Fitness"));
        stories.add(new StoryModel("https://picsum.photos/300?random=206", "Art"));
        stories.add(new StoryModel("https://picsum.photos/300?random=207", "Nature"));

        return stories;
    }
}
