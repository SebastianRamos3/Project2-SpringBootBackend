package sports_betting;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
public class AppUserService {

    private final AppUserRepository userRepo;

    public AppUserService(AppUserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Transactional
    public AppUser createUser(String username, String password, String email) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty");
        }

        Optional<AppUser> existingUser = userRepo.findByUsernameIgnoreCase(username.trim());
        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("Username already exists");
        }

        Optional<AppUser> existingEmail = userRepo.findByEmail(email.trim());
        if (existingEmail.isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }

        AppUser newUser = new AppUser();
        newUser.setUsername(username.trim());
        newUser.setPassword(password.trim());
        newUser.setEmail(email.trim());
        newUser.setDisplayName(username.trim());

        return userRepo.save(newUser);
    }

    @Transactional
    public AppUser authenticateUser(String username, String password) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }

        Optional<AppUser> user = userRepo.findByUsernameIgnoreCase(username.trim());
        if (user.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }

        AppUser foundUser = user.get();
        if (!foundUser.getPassword().equals(password.trim())) {
            throw new IllegalArgumentException("Invalid password");
        }

        return foundUser;
    }

    @Transactional
    public void updateDisplayName(Long userId, String newDisplayName) {
        if (newDisplayName == null || newDisplayName.trim().isEmpty()) {
            throw new IllegalArgumentException("Display name cannot be empty");
        }

        var user = userRepo.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        var existingUser = userRepo.findByDisplayName(newDisplayName.trim());
        if (existingUser.isPresent() && !existingUser.get().getId().equals(userId)) {
            throw new IllegalArgumentException("Display name is already taken");
        }

        user.setDisplayName(newDisplayName.trim());
        userRepo.save(user);
    }
}
