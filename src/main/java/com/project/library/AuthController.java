package com.project.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String login(@RequestParam String login, @RequestParam String password, Model model) {
        try {
            Object user = userService.authenticate(login, password);

            if (user instanceof Reader) {
                model.addAttribute("user", user);
                return "redirect:/profile"; // Читательский профиль
            } else if (user instanceof Staff) {
                model.addAttribute("user", user);
                return "redirect:/profile"; // Профиль библиотекаря
            }
        } catch (RuntimeException e) {
            model.addAttribute("error", "Invalid login or password");
            return "login";
        }

        return "login";
    }
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";  // Возвращает страницу login.html
    }

    @GetMapping("/profile")
    public String showProfilePage(Model model) {
        model.addAttribute("user", "Authenticated User");
        return "profile"; // Страница профиля
    }
}
