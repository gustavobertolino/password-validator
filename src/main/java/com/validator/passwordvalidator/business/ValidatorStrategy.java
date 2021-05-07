package com.validator.passwordvalidator.business;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ValidatorStrategy {
  private final Map<String, AbstractPasswordValidator> mapValidators = new HashMap<>();

  public ValidatorStrategy() {
    mapValidators.put("strong", new PasswordValidator());
    mapValidators.put("weak", new PasswordWeakValidator());
  }

  public AbstractPasswordValidator pickUpValidator(String typeValidator) {
    if (!mapValidators.containsKey(typeValidator)) {
      return new PasswordValidator();
    }
    return mapValidators.get(typeValidator);
  }
}
