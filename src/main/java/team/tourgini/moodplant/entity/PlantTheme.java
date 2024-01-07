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
public class PlantTheme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Plant plant;

    @ManyToOne(fetch = FetchType.LAZY)
    private Theme theme;

    public PlantTheme(Plant plant, Theme theme) {
        this.plant = plant;
        this.theme = theme;
    }
}
