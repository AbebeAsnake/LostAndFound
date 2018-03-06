package me.abebe.demo.security;

import me.abebe.demo.model.AppRole;
import me.abebe.demo.model.AppUser;
import me.abebe.demo.repo.AppUserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
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
        Set <GrantedAuthority> userAuthorities = new HashSet<>();
        AppUser thisUser = userRepo.findAppUserByUsername(username);
        if(thisUser==null)
            throw new UsernameNotFoundException("Invalid username or password");
        return new User(thisUser.getUsername(),thisUser.getPassword(),grantedAuthorities(thisUser));
    }

    public Set <GrantedAuthority> grantedAuthorities(AppUser user)
    {
        Set <GrantedAuthority> userAuthorities = new HashSet<>();
        for(AppRole eachRole: user.getRoles())
        {
            userAuthorities.add(new SimpleGrantedAuthority(eachRole.getRoleName()));
        }
        return userAuthorities;
    }
}


