package sports_betting;

import jakarta.persistence.*;

@Entity
@Table(
    name = "favorite",
    uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "team_id"})
)
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private AppUser user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "team_id")
    private Team team;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id; 
    }

    public AppUser getUser() {
        return user; 
    }
    public void setUser(AppUser user) {
        this.user = user; 
    }

    public Team getTeam() {
        return team; }
    public void setTeam(Team team) {
        this.team = team; 
    }
}