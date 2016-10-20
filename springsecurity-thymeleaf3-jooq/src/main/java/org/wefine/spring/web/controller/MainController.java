package org.wefine.spring.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.unbescape.html.HtmlEscape;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@Controller
public class MainController {

    @GetMapping("/")
    public String root(Locale locale) {
        return "redirect:/index.html";
    }

    /**
     * Home page.
     */
    @GetMapping("/index.html")
    public String index() {
        return "index";
    }

    /**
     * User zone index.
     */
    @GetMapping("/user/index.html")
    public String userIndex() {
        return "user/index";
    }

    /**
     * Administration zone index.
     */
    @GetMapping("/admin/index.html")
    public String adminIndex() {
        return "admin/index";
    }

    /**
     * Shared zone index.
     */
    @GetMapping("/shared/index.html")
    public String sharedIndex() {
        return "shared/index";
    }

    /**
     * Login form.
     */
    @GetMapping("/login.html")
    public String login() {
        return "login";
    }

    /**
     * Login form with error.
     */
    @GetMapping("/login-error.html")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

    /**
     * Simulation of an exception.
     */
    @GetMapping("/simulateError.html")
    public void simulateError() {
        throw new RuntimeException("This is a simulated error message");
    }

    /**
     * Error page.
     */
    @GetMapping("/error.html")
    public String error(HttpServletRequest request, Model model) {
        model.addAttribute("errorCode", "Error " + request.getAttribute("javax.servlet.error.status_code"));
        Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
        StringBuilder errorMessage = new StringBuilder();
        errorMessage.append("<ul>");
        while (throwable != null) {
            errorMessage.append("<li>").append(HtmlEscape.escapeHtml5(throwable.getMessage())).append("</li>");
            throwable = throwable.getCause();
        }
        errorMessage.append("</ul>");
        model.addAttribute("errorMessage", errorMessage.toString());
        return "error";
    }

    /**
     * Error page.
     */
    @GetMapping("/403.html")
    public String forbidden() {
        return "403";
    }


}
