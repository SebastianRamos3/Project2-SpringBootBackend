package sports_betting;

import jakarta.persistence.*;

@Entity

@Table(name = "team", uniqueConstraints = @UniqueConstraint(columnNames = "code"))
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String code; // "LAL", "BOS"

    public Long getId() { 
        return id; 
    }

    public void setId(Long id) { 
        this.id = id; 
    }

    public String getName() { 
        return name; 
    }

    public void setName(String name) { 
        this.name = name; 
    }
    public String getCode() { 
        return code; 
    }

    public void setCode(String code){
        this.code = code;
    }
}