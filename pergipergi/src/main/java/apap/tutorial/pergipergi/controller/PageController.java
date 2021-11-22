package apap.tutorial.pergipergi.controller;

import apap.tutorial.pergipergi.model.UserModel;
import apap.tutorial.pergipergi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    private String home(Authentication auth, Model model) {
        UserModel authUser = userService.findByUsername(auth.getName());
        model.addAttribute("currRole", authUser.getRole().getRole());
        return "home";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

}
