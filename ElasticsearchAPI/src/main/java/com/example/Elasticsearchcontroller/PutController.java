package com.example.Elasticsearchcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PutController {

    @GetMapping("/putindex")
    public String index() {
        return "putindex";
    }
}
