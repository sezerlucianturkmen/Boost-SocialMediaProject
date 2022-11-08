package com.boost.config.security;

import com.boost.repository.entity.UserProfile;
import com.boost.service.UserProfileService;
import com.boost.utility.JwtTokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class JwtTokenFilter extends OncePerRequestFilter {
    @Autowired
    private JwtTokenManager jwtTokenManager;

    @Autowired
    UserProfileService userProfileService;
    @Autowired
    JwtUserDetails jwtUserDetails;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {


        final String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            Optional<Long> authid = jwtTokenManager.getUserId(token);
            if (authid.isPresent()) {
                Optional<UserProfile> userProfile = userProfileService.findByAuthId(authid.get());
                if (userProfile.isPresent()) {
                    UserDetails userDetails = jwtUserDetails.loadUserByAuthId(userProfile.get());

                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                            = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

                }


            }

        }

        filterChain.doFilter(request, response);
    }
}