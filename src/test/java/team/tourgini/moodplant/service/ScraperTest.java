package team.tourgini.moodplant.service;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ScraperTest {

    @Test
    void test() throws IOException {
        String uri = "https://www.ramadasc.co.kr/#";
        Scraper scraper = new Scraper();

        Collection<String> texts = scraper.extractText(uri);

        assertThat(texts).isNotEmpty();
    }
}
