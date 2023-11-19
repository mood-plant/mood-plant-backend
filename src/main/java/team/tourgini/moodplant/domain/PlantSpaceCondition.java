package team.tourgini.moodplant.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class PlantSpaceCondition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Plant plant;

    @ManyToOne(fetch = FetchType.LAZY)
    private SpaceCondition spaceCondition;

    public PlantSpaceCondition(Plant plant, SpaceCondition spaceCondition) {
        this.plant = plant;
        this.spaceCondition = spaceCondition;
    }
}
