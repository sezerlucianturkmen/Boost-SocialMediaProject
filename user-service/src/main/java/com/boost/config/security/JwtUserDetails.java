package com.boost.config.security;

import com.boost.manager.IAuthManager;
import com.boost.repository.entity.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JwtUserDetails implements UserDetailsService {


    @Autowired
    IAuthManager authManager;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }


    public UserDetails loadUserByAuthId(UserProfile userProfile) {
        List<GrantedAuthority> authorityList = new ArrayList<>();

        String role = authManager.findRoleByAuthId(userProfile.getAuthid()).getBody().getRole();
        authorityList.add(new SimpleGrantedAuthority(role));


        return User.builder().
                username(userProfile.getUsername())
                .password("")
                .accountLocked(false)
                .accountExpired(false)
                .authorities(authorityList)
                .build();
    }
}