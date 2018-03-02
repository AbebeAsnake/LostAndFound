package me.abebe.demo.repo;

import me.abebe.demo.model.AppUser;
import me.abebe.demo.model.LostItems;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LostItemsRepository extends CrudRepository<LostItems , Long> {
   /* List<LostItems> findLostItemsByUsersIn(List<AppUser> users);
    List<LostItems> findByUsers(AppUser users);*/
   List<LostItems> findByUsers(AppUser users);

}
