package team.tourgini.moodplant.entity;

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
public class ResultPlant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Result result;

    @ManyToOne(fetch = FetchType.LAZY)
    private Plant plant;

    public ResultPlant(Result result, Plant plant) {
        this.result = result;
        this.plant = plant;
    }
}
