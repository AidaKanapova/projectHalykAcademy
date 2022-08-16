package kz.halykacademy.bookstore.service.impl;

import kz.halykacademy.bookstore.entity.User;
import kz.halykacademy.bookstore.errors.AuthorizationException;
import kz.halykacademy.bookstore.errors.ResourceNotFoundeException;
import kz.halykacademy.bookstore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;


@RequiredArgsConstructor
public class UserDetailsImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {

        System.out.println(" --------------login------------ ");
        User foundUser = userRepository.findByLogin(username)
                .orElseThrow(() -> new ResourceNotFoundeException("User not found"));
        if (foundUser.isBlocked()) {
            throw new AuthorizationException("user is blocked");
        } else {
            return org.springframework.security.core.userdetails.User.builder()
                    .username(foundUser.getLogin())
                    .password(foundUser.getPassword())
                    .authorities(foundUser.getRole().toString())

                    .build();
        }
    }

}
