package com.sivalabs.demo;

import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class IndexController {

    @GetMapping("/")
    String index(Model model,
                 Authentication authentication,
                 HttpSession session) {
        Object principal = authentication.getPrincipal();
        if(principal instanceof SecurityUser securityUser) {
            model.addAttribute("username", securityUser.getUsername());
        }
        if(principal instanceof User user) {
            model.addAttribute("username", "UNEXPECTED");
        }
        return "index";
    }
}
