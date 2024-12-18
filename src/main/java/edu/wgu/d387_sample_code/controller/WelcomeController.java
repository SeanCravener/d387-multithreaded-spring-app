package edu.wgu.d387_sample_code.controller;

import edu.wgu.d387_sample_code.service.MessageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class WelcomeController {

    private final MessageService messageService;

    public WelcomeController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/welcome")
    public String getWelcomeMessages() {
        StringBuilder result = new StringBuilder();

        // Define English and French locales
        Locale englishLocale = new Locale("en", "US");
        Locale frenchLocale = new Locale("fr", "FR");

        // Create threads for each language
        Thread englishThread = new Thread(() -> {
            String message = messageService.getMessage("welcome.message", englishLocale);
            synchronized (result) {
                result.append("English: ").append(message).append(" | ");
            }
        });

        Thread frenchThread = new Thread(() -> {
            String message = messageService.getMessage("welcome.message", frenchLocale);
            synchronized (result) {
                result.append("French: ").append(message);
            }
        });

        // Start threads
        englishThread.start();
        frenchThread.start();

        // Wait for threads to finish
        try {
            englishThread.join();
            frenchThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return result.toString();
    }
}