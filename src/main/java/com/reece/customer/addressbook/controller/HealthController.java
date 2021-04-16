package com.reece.customer.addressbook.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    private static final Logger log= LoggerFactory.getLogger(HealthController.class);

    @GetMapping(path = "health")
    public ResponseEntity<String> health() {
        log.info("health check");
        return new ResponseEntity<>("I am up!!", HttpStatus.OK);
    }
}
