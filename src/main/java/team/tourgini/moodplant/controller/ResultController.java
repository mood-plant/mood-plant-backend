package team.tourgini.moodplant.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.tourgini.moodplant.service.dto.ResultDto;
import team.tourgini.moodplant.service.impl.ResultService;

import java.net.URI;

@Slf4j
@RequestMapping("/api/results")
@RestController
@RequiredArgsConstructor
public class ResultController {

    private final ResultService resultService;

    @GetMapping("/{id}")
    public ResultDto retrieveResult(@PathVariable Long id) {
        return resultService.readResult(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> modifyResult(@PathVariable Long id, @RequestBody ResultDto result) {
        Long resultId = resultService.updateResult(id, result);
        URI uri = URI.create("/api/results/" + resultId);
        return ResponseEntity.created(uri)
                .build();
    }
}
