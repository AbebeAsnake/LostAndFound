package me.abebe.demo.security;

import me.abebe.demo.model.AppRole;
import me.abebe.demo.model.AppUser;
import me.abebe.demo.repo.AppUserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Transactional
@Service
public class SSUserDetailsService implements UserDetailsService {
    private AppUserRepository userRepo;
    public SSUserDetailsService(AppUserRepository userRepository) {
        this.userRepo = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Set <GrantedAuthority> userAuthorities = new HashSet<>();
            AppUser user = userRepo.findAppUserByUsername(username);
            if (user == null) {
                throw new UsernameNotFoundException("user not found");
            }
            System.out.println(user);
            return new org.springframework.security.core.userdetails.User(
                    user.getUsername(),
                    user.getPassword(),
                    getAuthorities(user));
        } catch (Exception e) {
            throw new UsernameNotFoundException("user not found");

        }

    }
    private Set<GrantedAuthority> getAuthorities (AppUser user){
        Set<GrantedAuthority> authorities
                = new HashSet<GrantedAuthority>();
        for (AppRole role : user.getRoles()) {
            GrantedAuthority grantedAuthority
                    = new SimpleGrantedAuthority(role.getRoleName());
            authorities.add(grantedAuthority);
        }
        return authorities;
    }

}
