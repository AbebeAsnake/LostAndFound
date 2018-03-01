package me.abebe.demo;

import me.abebe.demo.model.AppRole;
import org.springframework.data.repository.CrudRepository;

public interface AppRoleRepository extends CrudRepository<AppRole , Long> {
    AppRole findAppRoleByRoleName(String roleName);
}
