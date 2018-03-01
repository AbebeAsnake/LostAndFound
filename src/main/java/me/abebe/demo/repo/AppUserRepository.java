package me.abebe.demo.repo;

import me.abebe.demo.model.AppUser;
import org.springframework.data.repository.CrudRepository;

public interface AppUserRepository extends CrudRepository<AppUser , Long> {
    AppUser findByUsername(String username);
}
