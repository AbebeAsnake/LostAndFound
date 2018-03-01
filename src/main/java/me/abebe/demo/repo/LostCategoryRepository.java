package me.abebe.demo.repo;

import me.abebe.demo.model.LostCategory;
import org.springframework.data.repository.CrudRepository;

public interface LostCategoryRepository extends CrudRepository<LostCategory , Long>{
    LostCategory findByCategoryName(String name);
}
