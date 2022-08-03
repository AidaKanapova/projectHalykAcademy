package kz.halykacademy.bookstore.config;

import kz.halykacademy.bookstore.entity.User;
import kz.halykacademy.bookstore.errors.ResourceNotFoundeException;
import kz.halykacademy.bookstore.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {



        User foundUser = userRepository.findByLogin(username)
                .orElseThrow(() -> new ResourceNotFoundeException("User not found"));
        return org.springframework.security.core.userdetails.User.builder()
                .username(foundUser.getLogin())
                .password(foundUser.getPassword())
                .authorities(foundUser.getRole())
                .build();
    }
}
