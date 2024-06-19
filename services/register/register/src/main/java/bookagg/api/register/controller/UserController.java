package bookagg.api.register.controller;

import bookagg.api.register.dto.RegisterRequest;
import bookagg.api.register.model.ApplicationUser;
import bookagg.api.register.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<ApplicationUser> getUserById(@PathVariable UUID id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<Page<ApplicationUser>> getAllUsers(Pageable pageable) throws BadRequestException {
        return ResponseEntity.ok(userService.getAllUsers(pageable));
    }

    @PostMapping("/register")
    public ResponseEntity<ApplicationUser> createUser(@RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(userService.registerUser(registerRequest));
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<ApplicationUser> updateUser(@PathVariable UUID id, @RequestBody ApplicationUser user) {
        return ResponseEntity.ok(userService.updateUser(id, user));
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
