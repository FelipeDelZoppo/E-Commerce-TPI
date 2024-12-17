package tpi.backend.e_commerce.UserTests.UnitTests;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import tpi.backend.e_commerce.dto.auth.request.SignUpRequest;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class Test262CheckAllCustomerRegisterAttributes {

    private static Validator validator;

    @BeforeAll
    static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidAllAttributesAndMissingAny() {
        LocalDate validBirthDate = LocalDate.now().minusYears(25);

        SignUpRequest request = SignUpRequest.builder()
                .firstName("John")
                .dateBirth(validBirthDate)
                .email("test@example.com")
                .password("securePassword123")
                .build();

        Set<ConstraintViolation<SignUpRequest>> violations = validator.validate(request);

        assertFalse(violations.isEmpty(), "Faltan datos obligatorios");
    }

}
