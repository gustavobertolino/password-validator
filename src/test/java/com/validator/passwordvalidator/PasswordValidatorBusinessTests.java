package com.validator.passwordvalidator;

import com.validator.passwordvalidator.business.AbstractPasswordValidator;
import com.validator.passwordvalidator.business.PasswordWeakValidator;
import com.validator.passwordvalidator.business.ValidatorStrategy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PasswordValidatorBusinessTests {

  @Autowired
  ValidatorStrategy validatorStrategy;

  @Test
  void shouldNotBeValidWhenPasswordMissingLetters() {
    final String passwordToTest = "1234";
    final boolean result = validatorStrategy.pickUpValidator("strong").isValidPassword(passwordToTest);

    Assertions.assertThat(result).isFalse();
  }

  @Test
  void shouldBeValidWhenPasswordMissingNumbers() {
    final String passwordToTest = "Geksfor@Dav!";
    final boolean result = validatorStrategy.pickUpValidator("strong").isValidPassword(passwordToTest);

    Assertions.assertThat(result).isFalse();
  }

  @Test
  void shouldBeValidWhenPasswordMissingUpperCaseLetter() {
    final String passwordToTest = "geks@dav20!";
    final boolean result = validatorStrategy.pickUpValidator("strong").isValidPassword(passwordToTest);

    Assertions.assertThat(result).isFalse();
  }

  @Test
  void shouldBeValidWhenPasswordMissingLowerCaseLetter() {
    final String passwordToTest = "GEKS@DAV20!";
    final boolean result = validatorStrategy.pickUpValidator("strong").isValidPassword(passwordToTest);

    Assertions.assertThat(result).isFalse();
  }

  @Test
  void shouldBeValidWhenPasswordHasSpace() {
    final String passwordToTest = "Geks@ dvlopr9";
    final boolean result = validatorStrategy.pickUpValidator("strong").isValidPassword(passwordToTest);

    Assertions.assertThat(result).isFalse();
  }

  @Test
  void shouldBeValidWhenValidatorIsStrongAndPasswordIsCorrect() {
    final String passwordToTest = "Gek@Dav20!";
    final boolean result = validatorStrategy.pickUpValidator("strong").isValidPassword(passwordToTest);

    Assertions.assertThat(result).isTrue();
  }

  @Test
  void shouldBeValidWhenPasswordHasRepeatingCharacters() {
    final String passwordToTest = "Geek@Daev20!";
    final boolean result = validatorStrategy.pickUpValidator("strong").isValidPassword(passwordToTest);

    Assertions.assertThat(result).isFalse();
  }

  @Test
  void shouldBeValidWhenValidatorIsWeakAndPasswordIsCorrect() {
    final String passwordToTest = "Gek@Dav20!";
    final boolean result = validatorStrategy.pickUpValidator("weak").isValidPassword(passwordToTest);

    Assertions.assertThat(result).isTrue();
  }

  @Test
  void shouldBeInvalidWhenValidatorIsWeakAndPasswordIsIncorrect() {
    final String passwordToTest = "Gek!@";
    final boolean result = validatorStrategy.pickUpValidator("weak").isValidPassword(passwordToTest);

    Assertions.assertThat(result).isFalse();
  }

  @Test
  void shouldBePickedUpCorrectValidator() {
    final Class<? extends AbstractPasswordValidator> result = validatorStrategy.pickUpValidator("weak").getClass();
    Assertions.assertThat(result).isEqualTo(PasswordWeakValidator.class);
  }
}
