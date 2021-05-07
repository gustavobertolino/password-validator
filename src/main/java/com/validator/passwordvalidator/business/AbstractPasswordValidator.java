package com.validator.passwordvalidator.business;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractPasswordValidator {
  private static final Logger LOGGER = LoggerFactory.getLogger(AbstractPasswordValidator.class);

  protected boolean matchPasswordValidation(String password, String regex) {
    // Compile the ReGex
    LOGGER.info("Regex used in mother-class: " + regex);
    final Pattern pattern = Pattern.compile(regex);

    // If the password is empty
    // return false
    if (password == null) {
      return false;
    }

    // Pattern class contains matcher() method
    // to find matching between given password
    // and regular expression.
    final Matcher m = pattern.matcher(password);

    // Return if the password
    // matched the ReGex
    return m.matches();
  }

  public boolean isValidPassword(String password) {
    final String regex = "^(?=.*[0-9])";
    return matchPasswordValidation(password, regex);
  }
}
