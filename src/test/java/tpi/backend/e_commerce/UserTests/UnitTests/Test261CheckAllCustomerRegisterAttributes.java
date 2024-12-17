package tpi.backend.e_commerce.UserTests.UnitTests;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import tpi.backend.e_commerce.dto.auth.request.SignUpRequest;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class Test261CheckAllCustomerRegisterAttributes {

    private static Validator validator;

    @BeforeAll
    static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidAllAttributesAndAreOkey() {
        LocalDate validBirthDate = LocalDate.now().minusYears(25);

        SignUpRequest request = SignUpRequest.builder()
                .firstName("John")
                .lastName("Vanzetti")
                .dateBirth(validBirthDate)
                .email("test@example.com")
                .password("securePassword123")
                .build();

        Set<ConstraintViolation<SignUpRequest>> violations = validator.validate(request);

        assertTrue(violations.isEmpty(), "Faltan datos obligatorios");
    }
}
