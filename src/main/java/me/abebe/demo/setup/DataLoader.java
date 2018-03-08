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
//load user and role
        AppRole role = new AppRole();
        role.setRoleName("USER");
        roleRepository.save(role);

            role = new AppRole();
            role.setRoleName("ADMIN");
            roleRepository.save(role);

        AppUser user = new AppUser();
        user.setFirstName("user");
        user.setLastName("lastName");
        user.setEmail("user@email.com");
        user.setImage("http://www.nurseryrhymes.org/nursery-rhymes-styles/images/john-jacob-jingleheimer-schmidt.jpg");
        user.setPassword("pass");
        user.setUsername("user");
        user.addRole(roleRepository.findAppRoleByRoleName("USER"));
        userRepository.save(user);

        AppUser admin = new AppUser();
        admin.setFirstName("admin");
        admin.setLastName("admin");
        admin.setEmail("admin@email.com");
        admin.setImage("http://www.nurseryrhymes.org/nursery-rhymes-styles/images/john-jacob-jingleheimer-schmidt.jpg");
        admin.setPassword("pass");
        admin.setUsername("admin");
        admin.addRole(roleRepository.findAppRoleByRoleName("ADMIN"));
        userRepository.save(admin);

        //load category
        LostCategory lostCategory1 = new LostCategory();
        lostCategory1.setCategoryName("Clothes");
        lostCategoryRepository.save(lostCategory1);

        LostCategory category2 = new LostCategory();
        category2.setCategoryName("Pet");
        lostCategoryRepository.save(category2);


        LostCategory category3 = new LostCategory();
        category3.setCategoryName("Other");
        lostCategoryRepository.save(category3);

        //load lost items
        //1
        LostItems lostItems1 = new LostItems();
        lostItems1.setTitle("jacket");
        lostItems1.setDesc("red color");
        lostItems1.setImage("http://www.cheatsheet.com/wp-content/uploads/2015/11/Men-Clothing-in-Fashion-Store.jpg");
        lostItems1.setItemStatus("lost");

        lostItems1.setLostCategories(category2);
        lostItemsRepository.save(lostItems1);

        category2.addLostItems(lostItems1);
        lostCategoryRepository.save(category2);
        lostItems1.setUsers(user);
        lostItemsRepository.save(lostItems1);


///2
        LostItems lostItems2 = new LostItems();
        lostItems2.setTitle("fluffy");
        lostItems2.setDesc("brown");
        lostItems2.setImage("http://www.loc8tor.com/media/pet-site-home-hero.jpg");
        lostItems2.setItemStatus("lost");

        lostItems2.setLostCategories(category2);
        lostItemsRepository.save(lostItems2);

        category2.addLostItems(lostItems2);
        lostCategoryRepository.save(category2);
        lostItems2.setUsers(user);
        lostItemsRepository.save(lostItems2);


//3
         LostItems lostItems3 = new LostItems();
        lostItems3.setTitle("other thing");
        lostItems3.setDesc("unknown color");
        lostItems3.setImage("http://genosplace.com/images/Other%20Stuff.png");
        lostItems3.setItemStatus("lost");

        lostItems3.setLostCategories(category3);
        lostItemsRepository.save(lostItems3);

        category3.addLostItems(lostItems3);
        lostCategoryRepository.save(category3);
        lostItems3.setUsers(user);
        lostItemsRepository.save(lostItems3);



    }

}
