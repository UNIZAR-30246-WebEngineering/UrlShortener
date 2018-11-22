package io.swagger.api;

import io.swagger.model.Stats;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import urlshortener.common.config.TestConfiguration;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfiguration.class})
@Ignore
public class StatsApiControllerIntegrationTest {

    @Autowired
    private StatsApi api;

    @Test
    public void getStatsTest() throws Exception {
        ResponseEntity<Stats> responseEntity = api.getStats();
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

}
