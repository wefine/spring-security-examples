package org.wefine.spring.web.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class SharedController extends AbstractController {

    @GetMapping(value = {"/", "/home", "index"})
    public String homePage(ModelMap model) {
        model.addAttribute("greeting", "Hi, Welcome to my site. ");

        return "index";
    }

    @GetMapping(value = "/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping(value = "/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        // 重定向到登出界面
        return "redirect:/login?logout";
    }

    @GetMapping(value = "/Access_Denied")
    public String accessDeniedPage(ModelMap model) {
        model.addAttribute("user", getPrincipal());

        return "shared/accessDenied";
    }

    @GetMapping("favicon.ico")
    public String favicon() {
        return "forward:/static/images/favicon.ico";
    }
}