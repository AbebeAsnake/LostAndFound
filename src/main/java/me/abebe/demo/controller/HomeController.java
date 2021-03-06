package me.abebe.demo.controller;

import me.abebe.demo.model.AppUser;
import me.abebe.demo.repo.AppRoleRepository;
import me.abebe.demo.repo.AppUserRepository;
import me.abebe.demo.repo.LostCategoryRepository;
import me.abebe.demo.repo.LostItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @Autowired
    AppUserRepository userRepository;
    @Autowired
    AppRoleRepository roleRepository;
    @Autowired
    LostItemsRepository lostItemsRepository;
    @Autowired
    LostCategoryRepository lostCategoryRepository;
@RequestMapping("/login")
public String login(Model model){
    model.addAttribute("users", new AppUser());
    return "registeruser";
}
    @RequestMapping("/")
    public String indexpage(Model model){
        model.addAttribute("users", new AppUser());
        return "registeruser";
    }
    @GetMapping("/register")
    public String showForm(Model model){
        model.addAttribute("users", new AppUser());
        return "registeruser";
    }
    @RequestMapping("/register")
    public String addItems(Model model , AppUser user){

        user.addRole(roleRepository.findAppRoleByRoleName("USER"));
        userRepository.save(user);
        return "redirect:/";

    }
}
