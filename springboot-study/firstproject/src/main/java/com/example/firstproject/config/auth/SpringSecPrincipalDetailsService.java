package com.example.firstproject.config.auth;

import com.example.firstproject.dao.repository.UserRepository;
import com.example.firstproject.models.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SpringSecPrincipalDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    // 시큐리티 session(내부 Authentication(내부 UserDetails))
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userEntity = userRepository.findByUsername(username);

        // 없는 아이디를 입력했을 경우
        if(userEntity == null) {
            throw new UsernameNotFoundException("아이디가 존재하지 않습니다" + username);
        }

        return new SpringSecPrincipalDetails(userEntity);
    }
}
