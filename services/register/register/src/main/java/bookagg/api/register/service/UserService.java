package bookagg.api.register.service;

import bookagg.api.register.dto.RegisterRequest;
import bookagg.api.register.model.ApplicationUser;
import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface UserService {

    ApplicationUser registerUser(RegisterRequest user);

    ApplicationUser getUserById(UUID userId);

    Page<ApplicationUser> getAllUsers(Pageable pageable) throws BadRequestException;

    ApplicationUser updateUser(UUID userId, ApplicationUser updatedUser);

    void deleteUser(UUID userId);

}
