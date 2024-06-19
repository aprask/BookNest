package bookagg.api.register.service.impl;

import bookagg.api.register.dto.RegisterRequest;
import bookagg.api.register.model.ApplicationUser;
import bookagg.api.register.repository.UserRepository;
import bookagg.api.register.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public ApplicationUser registerUser(RegisterRequest registrationReq) {
        if (registrationReq == null) {
            log.error("Registration request is null");
            return null;
        }
        return userRepository.save(convertToEntity(registrationReq));
    }

    private ApplicationUser convertToEntity(RegisterRequest registrationReq) {
        return ApplicationUser.builder()
               .userName(registrationReq.userName())
               .firstName(registrationReq.firstName())
               .lastName(registrationReq.lastName())
               .email(registrationReq.email())
               .favoriteBooks(registrationReq.favoriteBooks())
               .build();
    }

    @Override
    public ApplicationUser getUserById(UUID userId) {
        if (userId == null) {
            log.error("User ID: {}, is null", (Object) null);
            return null;
        }
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public Page<ApplicationUser> getAllUsers(Pageable pageable) throws BadRequestException {
        if(validatePageable(pageable)) {
            return userRepository.findAll(pageable);
        }
        throw new BadRequestException("Invalid pageable");
    }

    private boolean validatePageable(Pageable pageable) {
        return pageable.getPageSize() >= 0 && pageable.getOffset() > 0;
    }

    @Override
    public ApplicationUser updateUser(UUID userId, ApplicationUser user) {
        if (userId == null || user == null) {
            log.error("User {} with ID {} is null", userId, user);
            return null;
        }
        ApplicationUser newUser = userRepository.findById(userId).orElse(null);
        if (newUser == null) {
            log.error("User {} with ID {} is null", userId, user);
            return null;
        }
        if (user.getEmail()!= null) {
            newUser.setEmail(user.getEmail());
        }
        if (user.getFirstName()!= null) {
            newUser.setFirstName(user.getFirstName());
        }
        if (user.getLastName()!= null) {
            newUser.setLastName(user.getLastName());
        }
        if (user.getFavoriteBooks()!= null) {
            newUser.setFavoriteBooks(user.getFavoriteBooks());
        }
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(UUID userId) {
        if (userId == null) {
            log.error("User with ID: {}, is null and cannot be deleted", (Object) null);
            return;
        }
        userRepository.deleteById(userId);
    }
}
