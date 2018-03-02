package me.abebe.demo.repo;

import me.abebe.demo.model.AppRole;
import me.abebe.demo.model.AppUser;
import org.springframework.data.repository.CrudRepository;

public interface AppRoleRepository extends CrudRepository<AppRole , Long> {
    AppRole findAppRoleByRoleName(String roleName);

    AppRole findByUsers(AppUser user);
}
