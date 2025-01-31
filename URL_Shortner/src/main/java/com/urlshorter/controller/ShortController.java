package com.urlshorter.controller;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.urlshorter.model.UrlModel;
import com.urlshorter.service.UrlService;

@Controller
public class ShortController {

    private final UrlService urlService;

    public ShortController(UrlService urlService) {
        this.urlService = urlService;
    }

    @GetMapping("/index")
    public String home(Model model) {
        model.addAttribute("urlModel", new UrlModel());
        return "index";
    }

    @PostMapping("/process")
    public String processUrl(UrlModel urlModel, Model model) {
        String shortUrl = urlService.getShortUrl(urlModel.getLongUrl());
        urlModel.setLongUrl(""); 
        model.addAttribute("urlModel", urlModel);
        model.addAttribute("shortUrl", shortUrl);
        return "index";
    }
}
