package me.abebe.demo.setup;

import me.abebe.demo.model.AppRole;
import me.abebe.demo.model.AppUser;
import me.abebe.demo.model.LostCategory;
import me.abebe.demo.model.LostItems;
import me.abebe.demo.repo.AppRoleRepository;
import me.abebe.demo.repo.AppUserRepository;
import me.abebe.demo.repo.LostCategoryRepository;
import me.abebe.demo.repo.LostItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    AppUserRepository userRepository;
    @Autowired
    AppRoleRepository roleRepository;

    @Autowired
    LostCategoryRepository lostCategoryRepository;

    @Autowired
    LostItemsRepository lostItemsRepository;
    @Override
    public void run(String... args) throws Exception {

        AppRole role = new AppRole();
        role.setRoleName("USER");
        roleRepository.save(role);

            role = new AppRole();
            role.setRoleName("ADMIN");
            roleRepository.save(role);


        LostCategory lostCategory = new LostCategory();
        lostCategory.setCategoryName("Clothes");
        lostCategoryRepository.save(lostCategory);


        lostCategory = new LostCategory();
        lostCategory.setCategoryName("Pets ");
        lostCategoryRepository.save(lostCategory);

        lostCategory = new LostCategory();
        lostCategory.setCategoryName("other");
        lostCategoryRepository.save(lostCategory);

        AppUser user = new AppUser();
        user.setFirstName("user");
        user.setFirstName("lastName");
        user.setEmail("user@email.com");
        user.setImage("http://www.nurseryrhymes.org/nursery-rhymes-styles/images/john-jacob-jingleheimer-schmidt.jpg");
        user.setPassword("pass");
        user.setUsername("user");
        user.addRole(roleRepository.findAppRoleByRoleName("USER"));
        userRepository.save(user);


        user = new AppUser();
        user.setFirstName("admin");
        user.setFirstName("lastName");
        user.setEmail("admin@email.com");
        user.setImage("http://www.nurseryrhymes.org/nursery-rhymes-styles/images/john-jacob-jingleheimer-schmidt.jpg");
        user.setPassword("pass");
        user.setUsername("admin");
        user.addRole(roleRepository.findAppRoleByRoleName("ADMIN"));
        userRepository.save(user);

        LostItems lostItems = new LostItems();
        lostItems.setTitle("wearable");
        lostItems.setDesc("red color");
        lostItems.setImage("image");
        lostItems.addCategory(lostCategoryRepository.findByCategoryName("Clothes"));
        lostItems.addUser(userRepository.findByUsername("USER"));
        lostItemsRepository.save(lostItems);



    }

}
