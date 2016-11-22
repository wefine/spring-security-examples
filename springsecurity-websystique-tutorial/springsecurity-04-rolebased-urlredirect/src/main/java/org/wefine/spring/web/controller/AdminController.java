package org.wefine.spring.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController extends AbstractController {

    @GetMapping
    public String adminPage(ModelMap model) {
        model.addAttribute("user", getPrincipal());

        return "admin/index";
    }
}
