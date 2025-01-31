package com.urlshorter.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.opsmatters.bitly.Bitly;
import com.opsmatters.bitly.api.model.v4.CreateBitlinkResponse;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UrlService {

    @Value("${BITLY_TOKEN}")
    private String BITLY_TOKEN;

    private Bitly bitly;

    @PostConstruct
    public void setup() {
        bitly = new Bitly(BITLY_TOKEN);
    }

    public String getShortUrl(String longUrl) {
        if (longUrl == null || longUrl.isEmpty()) {
            log.error("Invalid long URL provided.");
            return "Invalid URL provided.";
        }

        try {
            CreateBitlinkResponse response = bitly.bitlinks().shorten(longUrl).get();
            String shortLink = response.getLink();
            log.info("Successfully shortened URL: {}", shortLink);
            return shortLink;
        } catch (Exception e) {
            log.error("Error shortening URL: {}", e.getMessage());
            return "Failed to shorten the URL.";
        }
    }
}
