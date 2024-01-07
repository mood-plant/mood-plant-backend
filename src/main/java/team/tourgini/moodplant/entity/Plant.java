package team.tourgini.moodplant.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@EqualsAndHashCode
@NoArgsConstructor
public class Plant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer price;

    private String description;

    private String image;

    @OneToMany(mappedBy = "plant")
    private final List<PlantTheme> plantThemes = new ArrayList<>();

    @OneToMany(mappedBy = "plant")
    private final List<PlantSpaceCondition> plantSpaceConditions = new ArrayList<>();

    @OneToMany(mappedBy = "plant")
    private final List<PlantVoiceAndTone> plantVoiceAndTones = new ArrayList<>();

    public Plant(String name, Integer price, String description, String image) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.image = image;
    }
}
