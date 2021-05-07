package com.validator.passwordvalidator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PasswordWeakValidator extends AbstractPasswordValidator implements ValidatorInterface {
  private static final Logger LOGGER = LoggerFactory.getLogger(PasswordWeakValidator.class);

  @Override
  public boolean isValidPassword(String password) {
    final String regex = "^(?=.*[0-9])"
      + "(?=\\S+$).{8,20}$";

    LOGGER.info("Regex used in child-class: " + regex);
    return super.matchPasswordValidation(password, regex);
  }
}
