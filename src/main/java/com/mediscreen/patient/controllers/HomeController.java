package com.mediscreen.patient.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import io.swagger.annotations.ApiOperation;

@Controller
public class HomeController {

    @ApiOperation(value = "App home page", notes = "VIEW - Get request - Home page")
    @GetMapping("/")
    public String home(final Model model) {
        return "home";
    }

}
