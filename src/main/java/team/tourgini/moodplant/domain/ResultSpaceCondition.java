package team.tourgini.moodplant.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ResultSpaceCondition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Result result;

    @ManyToOne(fetch = FetchType.LAZY)
    private SpaceCondition spaceCondition;

    public ResultSpaceCondition(Result result, SpaceCondition spaceCondition) {
        this.result = result;
        this.spaceCondition = spaceCondition;
    }
}
