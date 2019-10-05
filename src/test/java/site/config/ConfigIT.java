package site.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import site.ScenarioContext;

@Configuration
@ComponentScan("site.beans")
public class ConfigIT {
    @Bean
    public ScenarioContext scenarioContext() {
        return new ScenarioContext();
    }
}
