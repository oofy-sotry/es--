package com.example.Elasticsearchcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CreateIndexController {

    @GetMapping("/creteindex")
    public String index() {
        return "creteindex";
    }
}
