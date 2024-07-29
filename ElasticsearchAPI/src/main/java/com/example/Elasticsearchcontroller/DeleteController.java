package com.example.Elasticsearchcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DeleteController {

    @GetMapping("/deleteindex")
    public String index() {
        return "deleteindex";
    }
}
