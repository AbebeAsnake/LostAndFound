package me.abebe.demo.controller;

import me.abebe.demo.model.LostItems;
import me.abebe.demo.repo.AppRoleRepository;
import me.abebe.demo.repo.AppUserRepository;
import me.abebe.demo.repo.LostCategoryRepository;
import me.abebe.demo.repo.LostItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LostController {
    @Autowired
    AppUserRepository userRepository;
    @Autowired
    AppRoleRepository roleRepository;

    @Autowired
    LostCategoryRepository lostCategoryRepository;

    @Autowired
    LostItemsRepository lostItemsRepository;

    @GetMapping("/additems")
    public String showForm(Model model){
        model.addAttribute("category", lostCategoryRepository.findAll());
        model.addAttribute("losts", new LostItems());

        System.out.println(lostCategoryRepository.findByCategoryName("other"));
        return "addlost";
    }
    @PostMapping("/additems")
    public String addItems(Authentication auth,Model model){
        LostItems lost = new LostItems();
        lost.setUsers(userRepository.findAppUserByUsername(auth.getName()));
        lostItemsRepository.save(lost);

        return "redirect:/";

    }
}
