package pl.polsl.zbdihd.wss.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;

@ConfigurationPropertiesScan(basePackages = "pl.polsl.zbdihd.wss.config")
@ComponentScan(basePackages = {
        "pl.polsl.zbdihd.wss.simulation",
        "pl.polsl.zbdihd.wss.scheduling"
})
@SpringBootApplication
public class WarehouseStalenessSimulatorApplication {

    public static void main(final String[] args) {
        SpringApplication.run(WarehouseStalenessSimulatorApplication.class, args);
    }

}
