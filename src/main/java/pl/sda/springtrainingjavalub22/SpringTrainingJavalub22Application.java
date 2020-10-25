package pl.sda.springtrainingjavalub22;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import pl.sda.springtrainingjavalub22.config.CompanyInfo;

@SpringBootApplication
@EnableConfigurationProperties(value = {CompanyInfo.class}) // dołączenie konfiguracji z application.properties , dodanie beana CompanyInfo
public class SpringTrainingJavalub22Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringTrainingJavalub22Application.class, args);
    }

}
