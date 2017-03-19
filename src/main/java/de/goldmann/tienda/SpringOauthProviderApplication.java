package de.goldmann.tienda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringOauthProviderApplication {

    public static void main(final String[] args) {
        SpringApplication.run(SpringOauthProviderApplication.class, args);
        System.out.println("Server started");
    }
}