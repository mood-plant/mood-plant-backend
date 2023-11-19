package team.tourgini.moodplant.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.tourgini.moodplant.controller.payload.AnalyzeRequest;
import team.tourgini.moodplant.service.AnalyzeUseCase;

import java.net.URI;

@Slf4j
@RequestMapping("/api/analyze")
@RestController
@RequiredArgsConstructor
public class AnalyzeController {

    private final AnalyzeUseCase analyzeUseCase;

    @PostMapping
    ResponseEntity<Void> analyze(@Valid @RequestBody AnalyzeRequest request) {
        Long id = analyzeUseCase.analyze(request.link());
        URI location = URI.create("/api/results/" + id);

//        return ResponseEntity.ok()
//                .header(HttpHeaders.LOCATION, location.toString())
//                .build();

        return ResponseEntity.created(location)
                .build();
    }
}
