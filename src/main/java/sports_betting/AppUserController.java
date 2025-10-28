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
