package br.com.clubecentaury;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "br.com.clubecentaury")
@EntityScan(basePackages = "br.com.clubecentaury.model")
@EnableJpaRepositories(basePackages = "br.com.clubecentaury.repository")
public class MoodleApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(MoodleApiApplication.class, args);
    }
}
