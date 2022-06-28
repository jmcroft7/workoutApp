package com.croft.workoutapp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
public class AppController {

    @GetMapping("/")
    public String viewIndexPage() {

        return "index";

    }
}
