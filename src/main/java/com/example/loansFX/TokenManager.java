package com.example.loansFX;

import org.json.JSONObject;

public class TokenManager {
    private static String accessToken;
    private static String refreshToken;

    public static void setTokensFromJson(String json) {
        JSONObject obj = new JSONObject(json);
        accessToken = obj.getString("accessToken");
        refreshToken = obj.getString("refreshToken");
    }

    public static String getAccessToken() {
        return accessToken;
    }

    public static String getRefreshToken() {
        return refreshToken;
    }

    public static void clearTokens() {
        accessToken = null;
        refreshToken = null;
    }
}
