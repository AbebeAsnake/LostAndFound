package me.abebe.demo.controller;

import me.abebe.demo.model.LostItems;
import me.abebe.demo.repo.AppUserRepository;
import me.abebe.demo.repo.LostCategoryRepository;
import me.abebe.demo.repo.LostItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class SearchController {
    @Autowired
    LostItemsRepository lostItemsRepository;
    @Autowired
    LostCategoryRepository categoryRepository;
    @Autowired
    AppUserRepository userRepository;
    @Autowired
    LostCategoryRepository lostCategoryRepository;

    @GetMapping("/search")
    public String getSearch(Model model){
        model.addAttribute("category", lostCategoryRepository.findAll());

        return "search";
    }

    @PostMapping("/search")
    public String showSearchResults(HttpServletRequest request, Model model){
        String searchItems = request.getParameter("search");
        //model.addAttribute("search",searchItems);
        String searchOption = request.getParameter("searchOption");
        //model.addAttribute("searchOption", searchOption);
//        Expecting multiple parameters or else will throw No parameter available Need to pass as many as are in constructor in Entity.
        Iterable<LostItems> category = lostItemsRepository.findAllByLostCategories(searchOption);
        if(searchItems!= null){
    model.addAttribute("itemserach",lostItemsRepository.findAllByTitleContaining(searchItems));
}
else if(searchOption !=null) {
    model.addAttribute("searchoptions", category);
}


        //   model.addAttribute("newLosts", lostRepository.findByCategoryNameIgnoreCaseContaining(srch));

//
        return "searchresult";
    }


}
