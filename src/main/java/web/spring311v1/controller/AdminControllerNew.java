package web.spring311v1.controller;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import web.spring311v1.model.Role;
import web.spring311v1.model.User;
import web.spring311v1.service.UserService;

import java.util.HashSet;
import java.util.Set;

@Data
@Controller
@RequestMapping("/admin")
public class AdminControllerNew {

    @Autowired
    private UserService userService;

    @GetMapping("/all_users")
    public ModelAndView getAllUsers() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("all_users");
        modelAndView.addObject("users", userService.getAllUsers());
        return modelAndView;
    }

    @GetMapping("/{id}")
    public String getUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id).get());
        return "edit_page";
    }

    @PostMapping("/{id}")
    public String editUser(@ModelAttribute("user") User user, @RequestParam("role") String role) {
        Set<Role> roleSet = new HashSet<>();
        if (role.equals("ROLE_ADMIN")){
            roleSet.add(userService.getRoleByName("ROLE_ADMIN").get());
            roleSet.add(userService.getRoleByName("ROLE_USER").get());
        } else {
            roleSet.add(userService.getRoleByName("ROLE_USER").get());
        }
        user.setRoles(roleSet);
        userService.editUser(user);
        return "edit_page";
    }

    @GetMapping("/all_users/remove_user/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return "redirect:/admin/all_users";
    }
}
