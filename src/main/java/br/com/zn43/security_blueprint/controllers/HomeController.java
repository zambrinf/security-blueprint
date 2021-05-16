package br.com.zn43.security_blueprint.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return ("<h1>Welcome everyone</h1>");
    }

    @GetMapping("/home")
    public String authorization() {
        return ("<h1>Welcome, authenticated by JWT</h1>");
    }

    @GetMapping("/user")
    public String user() {
        return ("<h1>Welcome User</h1><p><a href=\"/logout\">Logout</a></p>");
    }

    @GetMapping("/admin")
    public String admin() {
        return ("<h1>Welcome Admin</h1><p><a href=\"/logout\">Logout</a></p>");
    }

}
