package de.htwberlin.webtech.webtech.Controller;

import de.htwberlin.webtech.webtech.Model.User;
import de.htwberlin.webtech.webtech.Service.AuthService;
import de.htwberlin.webtech.webtech.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    public AuthController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public User register(@RequestBody Map<String, String> payload) {
        return userService.register(payload.get("email"), payload.get("password"));
    }

    @PostMapping("/login")
    public User login(@RequestBody Map<String, String> payload) {
        return authService.login(payload.get("email"), payload.get("password"));
    }
}
