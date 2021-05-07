package com.validator.passwordvalidator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PasswordValidatorController {
  private static final Logger LOGGER = LoggerFactory.getLogger(PasswordValidatorController.class);

  @Autowired
  ValidatorStrategy validatorStrategy;

  @GetMapping("validator/{password}")
  public ResponseEntity<Boolean> validatePassword(@PathVariable String password) {
    final boolean validationResult = validatorStrategy.pickUpValidator("weakkk").isValidPassword(password);

    LOGGER.info("Password validation response --> is the password valid?: " + validationResult);
    return new ResponseEntity<>(validationResult, HttpStatus.OK);
  }
}
