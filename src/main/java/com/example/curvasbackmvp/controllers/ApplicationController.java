package com.example.curvasbackmvp.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@CrossOrigin
@RequestMapping("api/v1")
public class ApplicationController {
    @GetMapping("docs")
    public RedirectView swagger() {
        return new RedirectView("/swagger-ui/index.html");
    }    // Controller apenas para exibição do swagger
}
