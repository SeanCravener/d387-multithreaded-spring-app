package edu.wgu.d387_sample_code.controller;

import edu.wgu.d387_sample_code.util.TimeZoneConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class PresentationController {

    @GetMapping("/presentation")
    public String getPresentationTimes() {
        // Example presentation time in ET
        LocalDateTime presentationTime = LocalDateTime.of(2025, 1, 5, 14, 30); 

        return TimeZoneConverter.convertTimeZones(presentationTime);
    }
}