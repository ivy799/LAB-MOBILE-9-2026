package com.example.final_lab_mobile.storage;

import android.content.Context;
import android.content.SharedPreferences;
import com.example.final_lab_mobile.model.Product;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SharedPrefsManager {
    private SharedPreferences prefs;
    private Gson gson;

    public SharedPrefsManager(Context context) {
        prefs = context.getSharedPreferences("OutdoorPrefs", Context.MODE_PRIVATE);
        gson = new Gson();
    }

    public void saveCache(List<Product> products) {
        prefs.edit().putString("CACHE_PRODUCTS", gson.toJson(products)).apply();
    }

    public List<Product> getCache() {
        String json = prefs.getString("CACHE_PRODUCTS", null);
        if (json == null) return new ArrayList<>();
        Type type = new TypeToken<List<Product>>() {}.getType();
        return gson.fromJson(json, type);
    }

    // --- User-Specific Data ---

    public void saveFavorite(Product product) {
        String email = getUserEmail();
        List<Product> favs = getFavorites();
        for (Product p : favs) {
            if (p.getId() == product.getId()) return;
        }
        favs.add(product);
        prefs.edit().putString("FAV_PRODUCTS_" + email, gson.toJson(favs)).apply();
    }

    public void removeFavorite(int productId) {
        String email = getUserEmail();
        List<Product> favs = getFavorites();
        favs.removeIf(p -> p.getId() == productId);
        prefs.edit().putString("FAV_PRODUCTS_" + email, gson.toJson(favs)).apply();
    }

    public List<Product> getFavorites() {
        String email = getUserEmail();
        String json = prefs.getString("FAV_PRODUCTS_" + email, "[]");
        Type type = new TypeToken<List<Product>>() {}.getType();
        return gson.fromJson(json, type);
    }

    public boolean isFavorite(int productId) {
        for (Product p : getFavorites()) {
            if (p.getId() == productId) return true;
        }
        return false;
    }

    public void saveOrder(Product product) {
        String email = getUserEmail();
        List<Product> orders = getOrders();
        orders.add(0, product);
        prefs.edit().putString("ORDER_HISTORY_" + email, gson.toJson(orders)).apply();
    }

    public List<Product> getOrders() {
        String email = getUserEmail();
        String json = prefs.getString("ORDER_HISTORY_" + email, "[]");
        Type type = new TypeToken<List<Product>>() {}.getType();
        return gson.fromJson(json, type);
    }

    // --- Theme ---

    public void setTheme(boolean isDark) {
        prefs.edit().putBoolean("DARK_MODE", isDark).apply();
    }

    public boolean getTheme() {
        return prefs.getBoolean("DARK_MODE", false);
    }

    // --- Auth & Session ---

    public boolean registerUser(String email, String password) {
        if (prefs.contains("USER_PASS_" + email)) {
            return false;
        }
        prefs.edit().putString("USER_PASS_" + email, password).apply();
        return true;
    }

    public boolean loginUser(String email, String password) {
        String savedPass = prefs.getString("USER_PASS_" + email, null);
        if (savedPass != null && savedPass.equals(password)) {
            prefs.edit().putString("CURRENT_USER_EMAIL", email).apply();
            prefs.edit().putBoolean("IS_LOGGED_IN", true).apply();
            return true;
        }
        return false;
    }

    public void saveLastCredentials(String email, String password) {
        prefs.edit().putString("LAST_EMAIL", email).apply();
        prefs.edit().putString("LAST_PASSWORD", password).apply();
    }

    public String getLastEmail() {
        return prefs.getString("LAST_EMAIL", "");
    }

    public String getLastPassword() {
        return prefs.getString("LAST_PASSWORD", "");
    }

    public void setLoggedIn(boolean isLoggedIn) {
        prefs.edit().putBoolean("IS_LOGGED_IN", isLoggedIn).apply();
    }

    public boolean isLoggedIn() {
        return prefs.getBoolean("IS_LOGGED_IN", false);
    }

    public String getUserEmail() {
        return prefs.getString("CURRENT_USER_EMAIL", "Guest");
    }

    public void logout() {
        prefs.edit().putBoolean("IS_LOGGED_IN", false).apply();
        prefs.edit().putString("CURRENT_USER_EMAIL", "Guest").apply();
    }
}
