package team.tourgini.moodplant.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "result")
    private List<ResultSpaceCondition> spaceConditions = new ArrayList<>();

    @OneToMany(mappedBy = "result")
    private List<ResultVoiceAndTone> voiceAndTones = new ArrayList<>();

    @OneToMany(mappedBy = "result")
    private List<ResultTheme> themes = new ArrayList<>();

}
