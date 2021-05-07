package com.validator.passwordvalidator.business;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PasswordValidator extends AbstractPasswordValidator {
  private static final Logger LOGGER = LoggerFactory.getLogger(PasswordWeakValidator.class);

  @Override
  public boolean isValidPassword(String password) {
    final String regex = "^(?=.*[0-9])"
      + "(?=.*[a-z])(?=.*[A-Z])"
      + "(?=.*[@#$%^&+=])"
      + "(?=\\S+$)(?!.*(.)\\1).{1,}$";

    LOGGER.info("Regex used in child-class: " + regex);
    return super.matchPasswordValidation(password, regex);
  }
}
