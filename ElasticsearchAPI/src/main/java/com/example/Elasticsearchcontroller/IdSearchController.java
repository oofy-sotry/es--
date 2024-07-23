package com.example.Elasticsearchcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IdSearchController {

    @GetMapping("/idesearch")
    public String index() {
        return "idsearch";
    }
}
