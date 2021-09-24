package com.siddhu;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

@RestController
public class SiddhuController {

    Counter userCounter;

    public SiddhuController(MeterRegistry registry) {
    	//registering the counter
        userCounter = Counter.builder("user_counter")
            .description("Number of times user visits to the site")
            .register(registry);
    }

    @GetMapping("/")
    public String index() {
        userCounter.increment();
        return "Default page that we are showing to the users.";
    }

    @GetMapping("/uservisit")
    public double visitCount() {
        return userCounter.count();
    }
    
}