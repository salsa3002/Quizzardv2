package lt.vu.mif.quizzardv2.model.gameInstance.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lt.vu.mif.quizzardv2.model.gameInstanceQuestion.entity.GameInstanceQuestion;
import lt.vu.mif.quizzardv2.model.user.entity.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "game_instance")
public class GameInstance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "daily_game")
    private Boolean dailyGame;

    @OneToMany(mappedBy = "gameInstance")
    private Set<GameInstanceQuestion> gameInstanceQuestions = new LinkedHashSet<>();
}