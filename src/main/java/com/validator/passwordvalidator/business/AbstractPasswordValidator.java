package com.validator.passwordvalidator.business;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractPasswordValidator {
  private static final Logger LOGGER = LoggerFactory.getLogger(AbstractPasswordValidator.class);

  protected boolean matchPasswordValidation(String password, String regex) {
    LOGGER.info("Regex used in mother-class: " + regex);
    if (password.isBlank() || regex.isBlank()) {
      return false;
    }

    final Pattern pattern = Pattern.compile(regex);
    final Matcher matcher = pattern.matcher(password);
    return matcher.matches();
  }

  public boolean isValidPassword(String password) {
    final String regex = "^(?=.*[0-9])";
    return matchPasswordValidation(password, regex);
  }
}
