package com.wynk.application;

import com.wynk.datastore.InMemoryDataStore;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Main class, responsible for bootstrapping application.
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.wynk"})
public class WynkApplication {

    public static void main(String[] args) {
        // Initialising in-memory data store.
        InMemoryDataStore.createDummyData();
        SpringApplication.run(WynkApplication.class, args);
    }
}
