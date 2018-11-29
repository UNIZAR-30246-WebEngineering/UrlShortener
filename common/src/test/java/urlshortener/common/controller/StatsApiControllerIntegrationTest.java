package urlshortener.common.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import urlshortener.common.config.TestConfiguration;
import urlshortener.common.domain.Stats;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfiguration.class})
public class StatsApiControllerIntegrationTest {

    @Autowired
    private StatsApi api;

    @Test
    public void getStatsTest() throws Exception {
        ResponseEntity<Stats> responseEntity = api.getStats();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

}
