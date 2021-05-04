package com.validator.passwordvalidator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class PasswordValidatorController {
  private static final Logger LOGGER = LoggerFactory.getLogger(PasswordValidatorController.class);

  @GetMapping("/{password}")
  public ResponseEntity<Map<String, Boolean>> validatePassword(@PathVariable String password) {
    System.out.println(password);
    Map<String, Boolean> mapPassword = new HashMap<>();
    mapPassword.put(password, true);

    LOGGER.info("Success! Response: " + mapPassword);
    return new ResponseEntity<>(mapPassword, HttpStatus.OK);
  }
}
