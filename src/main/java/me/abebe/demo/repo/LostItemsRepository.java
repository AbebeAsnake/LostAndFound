package me.abebe.demo.repo;

import me.abebe.demo.model.LostItems;
import org.springframework.data.repository.CrudRepository;

public interface LostItemsRepository extends CrudRepository<LostItems , Long> {
}
