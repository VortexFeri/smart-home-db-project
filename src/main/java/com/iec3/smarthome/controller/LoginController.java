package com.iec3.smarthome.controller;

import com.iec3.smarthome.entity.Login;
import com.iec3.smarthome.exception.LoginException;
import com.iec3.smarthome.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
//@RequestMapping (path = "index")
public class LoginController {
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @RequestMapping("")
    public String rootHandler(HttpServletRequest request) {
        return "index";
    }

    @PostMapping("login")
    public String getLoginUser(@RequestBody Login user) throws Throwable {
       if(loginService.verifyLogin(user))
           return "rooms";

       else
           throw new LoginException("Wrong password");
    }
}
