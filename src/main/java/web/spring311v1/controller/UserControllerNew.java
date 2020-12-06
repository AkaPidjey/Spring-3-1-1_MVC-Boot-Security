package web.spring311v1.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import web.spring311v1.model.Role;
import web.spring311v1.model.User;
import web.spring311v1.service.UserService;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Data
@Controller
public class UserControllerNew {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/new_user")
    public String newUser() {
        return "new_user";
    }

    @PostMapping("/new_user")
    public String createNewUser(@ModelAttribute("user") User user, @RequestParam("role") String role) {
        user.setPasswordReal(user.getPassword());
        Set<Role> roleSet = new HashSet<>();
        if (role.equals("ROLE_ADMIN")){
            roleSet.add(userService.getRoleByName("ROLE_ADMIN").get());
            roleSet.add(userService.getRoleByName("ROLE_USER").get());
        } else {
            roleSet.add(userService.getRoleByName("ROLE_USER").get());
        }
        user.setRoles(roleSet);



//        if (role.equals("roleAdmin")) {
//            user.setRoles(new HashSet<>(new Role().getName());
//            user.getRoles().add(userService.getRoleByName("ROLE_ADMIN").get());
//        }
//        user.getRoles().add(userService.getRoleByName("ROLE_USER"));

        userService.createNewUser(user);
        return "redirect:/new_user";
    }

    @GetMapping("/user")
    public ModelAndView showUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user_page");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

}
