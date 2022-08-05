package kz.halykacademy.bookstore.config;


import kz.halykacademy.bookstore.repository.OrderRepository;
import kz.halykacademy.bookstore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;


@Component("userSecurity")
@RequiredArgsConstructor
public class UserSecurity {


    private  final UserRepository userRepo;
    private final OrderRepository orderRepository;

    public boolean hasUserId(Authentication authentication, long userId) {

        long userID=userRepo.findByLogin(authentication.getName()).get().getUser_id(); //16
        long orderUserId = orderRepository.findById(userId).get().getUser().getUser_id(); //16

        return userID == orderUserId;

    }
}