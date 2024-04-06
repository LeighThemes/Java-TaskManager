package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@SpringBootApplication
public class WebBasedTaskManager4Application {

    public static void main(String[] args) {
        SpringApplication.run(WebBasedTaskManager4Application.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void openBrowser() {
        try {
            if (!GraphicsEnvironment.isHeadless()) {
                // Change the URL to your desired localhost URL
                URI uri = new URI("http://localhost:8080");
                Desktop.getDesktop().browse(uri);
            } else {
                // Handle headless mode
                //System.err.println("Unable to open browser: JVM is running in headless mode");
                System.out.println("Application is ready. Please open your browser and navigate to: http://localhost:8080");
            }
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}

