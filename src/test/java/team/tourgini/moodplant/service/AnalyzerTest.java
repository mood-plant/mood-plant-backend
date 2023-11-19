package team.tourgini.moodplant.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import team.tourgini.moodplant.service.dto.RecommendDto;

@SpringBootTest
@TestPropertySource("classpath:application.yml")
class AnalyzerTest {

    @Autowired
    Analyzer analyzer;

    @Test
    void test() {
        String uri = "https://www.josunhotel.com/intro.do";
        RecommendDto analyze = analyzer.analyze(uri);
        System.out.println("Test Result: " + analyze.toString());
    }
}
