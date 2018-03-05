package me.abebe.demo.controller;

import me.abebe.demo.model.AppUser;
import me.abebe.demo.model.LostItems;
import me.abebe.demo.repo.AppRoleRepository;
import me.abebe.demo.repo.AppUserRepository;
import me.abebe.demo.repo.LostCategoryRepository;
import me.abebe.demo.repo.LostItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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
@RequestMapping("/found")
public String dound(@ModelAttribute("losts") LostItems lost ,HttpServletRequest request , Model model){
    String found = request.getParameter("foundid");
//    model.addAttribute("losts",lostItemsRepository.findOne(new Long(request.getParameter("foundid"))));
    //lost.setItemStatus(lostItemsRepository.findOne(new Long(request.getParameter("found"))));
    lostItemsRepository.save(lost);
    return "redirect:/listlost";
}
    @GetMapping("/listlost")
    public String listlost(Model model, Authentication auth){
        model.addAttribute("losts", lostItemsRepository.findAll());
        model.addAttribute("users", userRepository.findAppUserByUsername(auth.getName()));
        model.addAttribute("category", lostCategoryRepository.findAll());
        return "lostitems";
    }
    @GetMapping("/additems")
    public String showForm(Model model){

        model.addAttribute("category", lostCategoryRepository.findAll());
        model.addAttribute("losts", new LostItems());

        return "addlost";
    }
   @RequestMapping(value = "/additems")
    public String addItems(@Valid @ModelAttribute("losts") LostItems lost, AppUser user, BindingResult result, Authentication auth, HttpServletRequest request){
        if(result.hasErrors()){
            return "additems";
                    }
        lost.setUsers(userRepository.findAppUserByUsername(auth.getName()));
        lost.setItemStatus("lost");
        lostItemsRepository.save(lost);

        return "redirect:/listlost";

    }
}
