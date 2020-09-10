package com.wz.math;

import java.util.HashMap;
import java.util.Map;

/**
 * TinyURL is a URL shortening service where you enter a URL such as https://leetcode.com/problems/design-tinyurl
 * and it returns a short URL such as http://tinyurl.com/4e9iAk.
 * Design the encode and decode methods for the TinyURL service.
 * There is no restriction on how your encode/decode algorithm should work.
 * You just need to ensure that a URL can be encoded to a tiny URL and the tiny URL can be decoded to the original URL.
 */
public class EncodeAndDecodeTinyURL {
    Map<String, String> map = new HashMap<>();
    String BASE_URL = "http://tinyurl.com/";

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        String shortUrl = BASE_URL + longUrl.hashCode();
        map.put(shortUrl, longUrl);
        return shortUrl;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        return map.getOrDefault(shortUrl, shortUrl);
    }
}
