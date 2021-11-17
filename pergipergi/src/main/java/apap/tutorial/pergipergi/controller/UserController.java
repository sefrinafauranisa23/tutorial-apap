package apap.tutorial.pergipergi.controller;

import apap.tutorial.pergipergi.model.RoleModel;
import apap.tutorial.pergipergi.model.TourGuideModel;
import apap.tutorial.pergipergi.model.TravelAgensiModel;
import apap.tutorial.pergipergi.model.UserModel;
import apap.tutorial.pergipergi.service.RoleService;
import apap.tutorial.pergipergi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/add")
    public String addUserFormPage(Model model){
        UserModel user = new UserModel();
        List<RoleModel> listRole = roleService.findAll();
        model.addAttribute("user", user);
        model.addAttribute("listRole", listRole);
        return "form-add-user";
    }

    @PostMapping(value="/add")
    public String addUserSubmit(@ModelAttribute UserModel user, Model model){
        userService.addUser(user);
        model.addAttribute("user",user);
        return "redirect:/";
    }

    @GetMapping("/viewall")
    public String listUser(Model model) {
        List<UserModel> listUser = userService.getListUser();
        model.addAttribute("listUser", listUser);
        return "lihat-semua-user";
    }

    @GetMapping("/delete/{id}")
    public String deleteUserSubmit(
            @PathVariable String id,
            Model model
    ){
        UserModel user = userService.getUserById(id);
        userService.deleteUser(user);
        List<UserModel> listUser = userService.getListUser();
        model.addAttribute("listUser", listUser);
        return "lihat-semua-user";
    }

//    @GetMapping("/ubah-password")
//    public String ubahPassword() {
//        return "form-ubah-password";
//    }

    @GetMapping("/ubah-password")
    public String ubahPasswordPage(
            Authentication auth,
            @RequestParam(value = "passwordLama", required = false) String passwordLama,
            @RequestParam(value = "passwordBaru", required = false) String passwordBaru,
            @RequestParam(value = "passwordBaruConf", required = false) String passwordBaruConf,
            Model model
    ){
        if (passwordLama == null || passwordBaru == null || passwordBaruConf == null) {
            return "form-ubah-password";
        }
        UserModel authUser = userService.findByUsername(auth.getName());
        if(userService.isMatch(passwordLama,authUser.getPassword())) {
            if (passwordBaru.equals(passwordBaruConf)) {
                userService.findByUsername(auth.getName()).setPassword(userService.encrypt(passwordBaru));
                userService.updateUser(userService.findByUsername(auth.getName()));
                model.addAttribute("proses", "Ubah password berhasil dilakukan");
                return "proses";
            } else {
                model.addAttribute("proses", "Kedua password baru tidak cocok");
                return "proses";
            }
        }
        model.addAttribute("proses", "Password lama yang anda masukkan salah");
        return "proses";
    }
}
