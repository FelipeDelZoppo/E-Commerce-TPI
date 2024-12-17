package tpi.backend.e_commerce.ProductTests.UnitTests;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import tpi.backend.e_commerce.dto.ProductDTO.CreateProductDTO;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class Test301ValidateNameOfProduct {

    private static Validator validator;

    @BeforeAll
    static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testCreateProductWithNameWithOneCharacter() {
        CreateProductDTO productDTO = new CreateProductDTO(
                "P",     // name
                "Descripción de prueba",   // description
                0.5,                     // price
                10L,                       // stock
                1L,                        // stockMin
                null,                      // imageURL (opcional)
                "Rojo",                    // color
                null,                      // size (opcional)
                1L,                        // brandId
                1L                         // subCategoryId
        );

        Set<ConstraintViolation<CreateProductDTO>> violations = validator.validate(productDTO);

        assertFalse(violations.isEmpty(), "El producto debería tener un un nombre mayor a 2 caracteres");
    }

    @Test
    void testCreateProductWithNameWithTwoCharacter() {
        CreateProductDTO productDTO = new CreateProductDTO(
                "Pr",     // name
                "Descripción de prueba",   // description
                1.0,                     // price
                10L,                       // stock
                1L,                        // stockMin
                null,                      // imageURL (opcional)
                "Rojo",                    // color
                null,                      // size (opcional)
                1L,                        // brandId
                1L                         // subCategoryId
        );

        Set<ConstraintViolation<CreateProductDTO>> violations = validator.validate(productDTO);

        assertTrue(violations.isEmpty(), "El producto debería tener un un nombre mayor a 2 caracteres");
    }

    @Test
    void testCreateProductWithNameWithThreeCharacter() {
        CreateProductDTO productDTO = new CreateProductDTO(
                "Pro",     // name
                "Descripción de prueba",   // description
                22.0,                     // price
                10L,                       // stock
                1L,                        // stockMin
                null,                      // imageURL (opcional)
                "Rojo",                    // color
                null,                      // size (opcional)
                1L,                        // brandId
                1L                         // subCategoryId
        );

        Set<ConstraintViolation<CreateProductDTO>> violations = validator.validate(productDTO);

        assertTrue(violations.isEmpty(), "El producto debería tener un un nombre mayor a 2 caracteres");
    }

}
