package com.croft.workoutapp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@RequestMapping("/error")
public class ErrorController {

    @RequestMapping(value="/404")
    public String error404(HttpServletRequest request) {
        String badUrl = (String) request.getAttribute("javax.servlet.forward.request_url");
        log.error("Requested Url not found: " + badUrl);

        return "error/404";
    }

    @RequestMapping(value="/500")
    public String error500() {
        log.error("Server Issue: ");

        return "error/500";
    }

}
