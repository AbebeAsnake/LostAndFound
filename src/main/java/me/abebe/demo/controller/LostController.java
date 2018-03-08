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
import org.springframework.web.bind.annotation.PathVariable;
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

@GetMapping("/found/{id}")
public String editLost(@PathVariable("id") long id, Model model, Authentication auth) {
    AppUser user = userRepository.findAppUserByUsername(auth.getName());
    LostItems lost = lostItemsRepository.findOne(new Long(id));
String status = lost.getItemStatus();
if(status.equalsIgnoreCase("found")){
    lost.setItemStatus("lost");
}
else
    lost.setItemStatus("found");
lostItemsRepository.save(lost);
return "redirect:/listlost";

}

    @GetMapping("/delete/{id}")
    public String deleteLost(@PathVariable("id") long id, Model model, Authentication auth) {
        LostItems lost = lostItemsRepository.findOne(new Long(id));
        lostItemsRepository.delete(lost);
        return "redirect:/listlost";

    }

    @GetMapping("/listlost")
    public String listlost(Model model, Authentication auth){
        model.addAttribute("losts", lostItemsRepository.findByItemStatus("lost"));


        //model.addAttribute("users", userRepository.findAppUserByUsername(auth.getName()));

        model.addAttribute("category", lostCategoryRepository.findAll());
        return "lostitems";
    }

    @GetMapping("/myitems")
    public String lisMyItems(Model model, Authentication auth, HttpServletRequest request){
        AppUser currentUser =  userRepository.findAppUserByUsername(auth.getName());
    Iterable<LostItems> losts = lostItemsRepository.findByUsersAndItemStatus(currentUser,"found");
    model.addAttribute("losts", losts);
        model.addAttribute("users", userRepository.findAppUserByUsername(auth.getName()));

        model.addAttribute("category", lostCategoryRepository.findAll());
        return "myitems";
    }
    @GetMapping("/additems")
    public String showForm(Model model){

        model.addAttribute("category", lostCategoryRepository.findAll());
        model.addAttribute("losts", new LostItems());
        model.addAttribute("userss", userRepository.findAll());

        return "addlost";
    }
   @RequestMapping(value = "/additems")
    public String addItems(@Valid @ModelAttribute("losts") LostItems lost, AppUser user, BindingResult result, Authentication auth, HttpServletRequest request){
        if(result.hasErrors()){
            return "additems";
                    }

       String anonymous = request.getParameter("unknownuser");
        if(anonymous != null){
            lost.setItemStatus("lost");
            lostItemsRepository.save(lost);
        }
        else {
            String users = request.getParameter("us");
           String img = request.getParameter("image");

            lost.setUsers(userRepository.findAppUserByUsername(auth.getName()));
            if(img.equals("")){
                lost.setImage("https://www.vetmed.wisc.edu/wp-content/uploads/2016/10/default.jpg");
            }
           /* if(!users.equals("")){
                lost.setUsers();
            }*/
            lost.setItemStatus("lost");
            lostItemsRepository.save(lost);
        }
        return "redirect:/listlost";

    }
}
