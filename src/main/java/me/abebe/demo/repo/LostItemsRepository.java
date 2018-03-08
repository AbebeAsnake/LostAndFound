package me.abebe.demo.repo;

import me.abebe.demo.model.AppUser;
import me.abebe.demo.model.LostItems;
import org.springframework.data.repository.CrudRepository;

public interface LostItemsRepository extends CrudRepository<LostItems , Long> {
   /* List<LostItems> findLostItemsByUsersIn(List<AppUser> users);
    List<LostItems> findByUsers(AppUser users);*/
  Iterable<LostItems> findByUsersAndItemStatus(AppUser users, String status);
   Iterable<LostItems> findByLostCategories(String category);
   Iterable<LostItems> findByTitle(String sitle);
   Iterable<LostItems> findAllByTitleContainingOrDescrContaining( String title, String desc);
   Iterable<LostItems> findAllByLostCategories(String category);
Iterable<LostItems> findByItemStatus(String status);
}
