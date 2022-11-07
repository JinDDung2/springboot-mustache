package com.mustache.bbs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MustacheController {

    @GetMapping("/hi")
    public String mustacheCon(Model model) {
        model.addAttribute("username", "Jin-hyuck");
        return "greetings";
    }

    @GetMapping("/hi/{id}")
    public String mustacheConV2(@PathVariable String id, Model model) {
        model.addAttribute("id", "0");
        model.addAttribute("username", "Jin-hyuck");
        return "greetings";
    }

}
