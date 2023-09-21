package com.example.demo.user.service;

import com.example.demo.auth.utils.AuthorityUtils;
import com.example.demo.exception.BusinessLogicException;
import com.example.demo.exception.ExceptionCode;
import com.example.demo.user.domain.User;
import com.example.demo.user.domain.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.Optional;

public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    private final UserRepository userRepository;
    private final AuthorityUtils authorityUtils;

    public UserDetailsService(UserRepository userRepository, AuthorityUtils authorityUtils) {
        this.userRepository = userRepository;
        this.authorityUtils = authorityUtils;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByEmail(username);
        User findUser = optionalUser.orElseThrow(() -> new BusinessLogicException(ExceptionCode.USER_NOT_FOUND));

        Collection<? extends GrantedAuthority> authorities = authorityUtils.createAuthorities(findUser.getEmail());

        return new AuthorizedUserDetails(findUser);
    }

    private final class AuthorizedUserDetails extends User implements UserDetails {
        AuthorizedUserDetails(User user) {
            // 이거 어디까지 해야 하지? -> 어디서 쓰이는지 확인해봐야겠다
            setUserId(user.getUserId());
            setUserName(user.getUserName());
            setEmail(user.getEmail());
            setPassword(user.getPassword());
            setRoles(user.getRoles());
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return authorityUtils.createAuthorities(this.getRoles());
        }

        @Override
        public String getUsername() { // 여기서 username은 email(credential)
            return this.getEmail();
        }

        // 얘네도 나중에 써야겠다
        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }
    }
}
