package me.abebe.demo.repo;

import me.abebe.demo.model.AppUser;
import me.abebe.demo.model.LostItems;
import org.springframework.data.repository.CrudRepository;

public interface AppUserRepository extends CrudRepository<AppUser , Long> {
   // AppUser findByUsername(String username);
    AppUser findAppUserByUsername(String username);

    Iterable<AppUser> findByLostItemsIn(Iterable<LostItems> losts);

}
