package org.wefine.spring.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dba")
public class DbaController extends AbstractController {

    @GetMapping
    public String dbaPage(ModelMap model) {
        model.addAttribute("user", getPrincipal());

        return "dba/index";
    }
}
