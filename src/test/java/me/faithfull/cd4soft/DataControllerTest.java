package me.faithfull.cd4soft;

import lombok.extern.slf4j.Slf4j;
import me.faithfull.cd4soft.cd.ChangeDetector;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Will Faithfull
 */

@RunWith(SpringRunner.class)
@ActiveProfiles({"test"})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = Application.class)
@Slf4j
public class DataControllerTest {

    @Autowired
    protected WebApplicationContext wac;
    protected MockMvc               mockMvc;

    @Autowired
    ChangeDetector<Double> detector;

    @Autowired
    DataProvider provider;

    @Before
    public void before() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(wac)
                .build();
    }

    @Test
    public void changeDetectionTest() throws Exception {
        for(int i=0;i<200;i++) {
            mockMvc.perform(MockMvcRequestBuilders.get("/data")).andExpect(status().isOk());
            Assert.assertFalse(detector.isChange());
        }

        int at = 0;
        boolean detected = false;

        long delay = 0;
        for(int i=0;i<1000;i++) {

            // Introduce abrupt change
            if(i % 100 == 0) {
                delay += 100;
                provider.setDelay(delay);
                log.info("Artificially slowed requests by {}ms", delay);
            }

            mockMvc.perform(MockMvcRequestBuilders.get("/data")).andExpect(status().isOk());

            if(detector.isChange()) {
                at = i;
                detected = true;
                break;
            }
        }

        Assert.assertTrue(detected);
        if(detected) {
            log.info("Detected change {} observations after change point", at);
        }
    }

}