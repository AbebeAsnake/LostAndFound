package me.abebe.demo;

import me.abebe.demo.model.AppRole;
import me.abebe.demo.model.AppUser;
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
    @Override
    public void run(String... args) throws Exception {

        AppRole role = new AppRole();
        role.setRoleName("USER");
        roleRepository.save(role);

            role = new AppRole();
            role.setRoleName("ADMIN");
            roleRepository.save(role);


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

    }

}
