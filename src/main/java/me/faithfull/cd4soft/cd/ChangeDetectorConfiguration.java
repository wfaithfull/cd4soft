package me.faithfull.cd4soft.cd;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Will Faithfull
 */
@Configuration
public class ChangeDetectorConfiguration {

    @Bean
    ChangeDetector<Double> changeDetector() {
        return new CUSUMChangeDetector();
    }

}
