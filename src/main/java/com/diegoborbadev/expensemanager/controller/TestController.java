package com.diegoborbadev.expensemanager.controller;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class TestController {
    @GetMapping("/")
    public ResponseEntity<Dto> getTest() {
        return ResponseEntity.ok(new Dto());
    }
}

@Data
class Dto {
    private String message = "Hello World!";
}