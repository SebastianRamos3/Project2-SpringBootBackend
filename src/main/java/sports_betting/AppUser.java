package sports_betting;

import jakarta.persistence.*;

@Entity
@Table(name = "app_user")
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String displayName;

    @Column(nullable = false, unique = true)
    private String email;

    public Long getId() { 
        return id; 
    }
    public void setId(Long id) {
        this.id = id; 
    }   

    public String getDisplayName() {
        return displayName; 
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName; 
    }

    public String getEmail() {
        return email; 
    }
    public void setEmail(String email) {
        this.email = email; 
    }
}