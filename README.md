##Intro and rationale

This API validates password according to the type validator chosen by the API caller.

The implementation uses polymorphism to achieve the choice of the right validator for the
password's validation at runtime, avoiding the use of conditionals to achieve such decision.

The code was made so that it's extensible. So the insertion of new validators can be easily made
by extending the abstract class, which works as an abstraction/interface to make the code easy to be
extended. So in other words, no needing an existent validator to be altered.

##Setup & installment

The API assumes that you have installed the following tools on your environment:
- Java 8+
- Gradle

To install those tools, just look for tutorials to set them up on internet.


##How to use
To start the API server locallt, run `gradle bootRun`. The API will be served on `localhost:8080`


The endpoint is provided in this way:
`http://localhost:8080/validator/{typeValidator}/{password}`

where `typeValidator` is the validator picked up by the client
and `password` is the password to be validated. The output will be `true`
in case of being a valid password and `false` otherwise.

A default type validator is given by the code, so if the client provides 
a missing type validator, the code is prepare to choose a default validator as its fallback.
This default validator is called `strong`.

This default validator will validate a password obeying the following rules:
- at least one digit.
- at least one upper case alphabet.
- at least one lower case alphabet.
- at least one special character which includes !@#$%&*()-+=^.
- it doesn't contain any white space.
- it doesn't contain repeated characters.

So below it follows a valid example of API password validation request:

```
curl --request GET \
--url http://localhost:8080/validator/strong/Geks@portal20
```

##Tests

The API has business tests to validate its main use cases and also a basic integration test, 
just to test the entrypoint/controller request-response. 

To run the tests, run `gradle clean tests`. 


