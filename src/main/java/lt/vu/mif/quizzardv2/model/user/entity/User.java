package lt.vu.mif.quizzardv2.model.user.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lt.vu.mif.quizzardv2.model.gameInstance.entity.GameInstance;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "\"user\"")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "username", length = 20)
    private String username;

    @Column(name = "password", length = 100)
    private String password;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "role", length = 20)
    private String role;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "total_games_played")
    private Integer totalGamesPlayed;

    @OneToMany(mappedBy = "user")
    private Set<GameInstance> gameInstances = new LinkedHashSet<>();
}