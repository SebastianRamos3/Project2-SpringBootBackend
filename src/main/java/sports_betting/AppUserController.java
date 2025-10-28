package sports_betting;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class AppUserController {

    private final AppUserService userService;

    public AppUserController(AppUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> registerUser(@RequestBody Map<String, String> request) {
        try {
            String username = request.get("username");
            String password = request.get("password");
            String email = request.get("email");

            AppUser newUser = userService.createUser(username, password, email);

            return ResponseEntity.ok(Map.of(
                    "message", "User created successfully",
                    "userId", newUser.getId(),
                    "username", newUser.getUsername(),
                    "displayName", newUser.getDisplayName()));

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> loginUser(@RequestBody Map<String, String> request) {
        try {
            String username = request.get("username");
            String password = request.get("password");

            AppUser user = userService.authenticateUser(username, password);

            return ResponseEntity.ok(Map.of(
                    "message", "Login successful",
                    "userId", user.getId(),
                    "username", user.getUsername(),
                    "displayName", user.getDisplayName()));

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @PutMapping("/{userId}/display-name")
    public ResponseEntity<Map<String, String>> updateDisplayName(
            @PathVariable Long userId,
            @RequestBody Map<String, String> request) {

        try {
            String newDisplayName = request.get("displayName");
            userService.updateDisplayName(userId, newDisplayName);

            return ResponseEntity.ok(Map.of("message", "Display name updated successfully"));

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", e.getMessage()));
        }
    }
}
