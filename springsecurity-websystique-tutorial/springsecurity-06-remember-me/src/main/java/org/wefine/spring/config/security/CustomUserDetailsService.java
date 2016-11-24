package org.wefine.spring.config.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.wefine.spring.model.User;
import org.wefine.spring.model.UserProfile;
import org.wefine.spring.web.service.UserService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Resource
    private UserService userService;

    public UserDetails loadUserByUsername(String ssoId)
            throws UsernameNotFoundException {
        User user = userService.findBySso(ssoId);
        log.info("User : " + user);
        if (user == null) {
            log.info("User not found");
            throw new UsernameNotFoundException("Username not found");
        }
        return new SecurityUser(user.getSsoId(), user.getPassword(),
                user.getState().equals("Active"), true, true, true, getGrantedAuthorities(user));
    }

    private List<GrantedAuthority> getGrantedAuthorities(User user) {
        List<GrantedAuthority> authorities = new ArrayList<>();

        for (UserProfile userProfile : user.getUserProfiles()) {
            log.info("UserProfile : " + userProfile);
            authorities.add(new SimpleGrantedAuthority("ROLE_" + userProfile.getType()));
        }
        log.info("authorities :" + authorities);

        return authorities;
    }
}