package com.example.Elasticsearchcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TitleSearchController {

    @GetMapping("/titlesearch")
    public String index() {
        return "titlesearch";
    }
}
