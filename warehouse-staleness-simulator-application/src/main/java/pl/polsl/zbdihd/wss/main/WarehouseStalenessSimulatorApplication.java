package pl.polsl.zbdihd.wss.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@ConfigurationPropertiesScan(basePackages = "pl.polsl.zbdihd.wss.config")
@ComponentScan(basePackages = {
        "pl.polsl.zbdihd.wss.simulation",
        "pl.polsl.zbdihd.wss.scheduling"
})
@EntityScan(basePackages = "pl.polsl.zbdihd.wss.persistence.entity")
@EnableJpaRepositories("pl.polsl.zbdihd.wss.persistence.repository")
@SpringBootApplication
@EnableAsync
public class WarehouseStalenessSimulatorApplication {

    public static void main(final String[] args) {
        SpringApplication.run(WarehouseStalenessSimulatorApplication.class, args);
    }

}
