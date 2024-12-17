package tpi.backend.e_commerce.ProductTests.UnitTests;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import tpi.backend.e_commerce.dto.ProductDTO.CreateProductDTO;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Test302ValidateNameOfProduct {

    private static Validator validator;

    @BeforeAll
    static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testCreateProductWithNameWithFiftyNineCharacters() {
        CreateProductDTO productDTO = new CreateProductDTO(
                "Verifica que el campo nombre permita solo valores con una l",     // name
                "Descripción de prueba",   // description
                100.00,                     // price
                10L,                       // stock
                1L,                        // stockMin
                null,                      // imageURL (opcional)
                "Rojo",                    // color
                null,                      // size (opcional)
                1L,                        // brandId
                1L                         // subCategoryId
        );

        Set<ConstraintViolation<CreateProductDTO>> violations = validator.validate(productDTO);

        assertTrue(violations.isEmpty(), "El producto debería tener un un nombre menor a 60 caracteres");
    }

    @Test
    void testCreateProductWithNameWithFiftySixtyCharacters() {
        CreateProductDTO productDTO = new CreateProductDTO(
                "Verifica que el campo nombre permita solo valores con una lo",     // name
                "Descripción de prueba",   // description
                100.0,                     // price
                10L,                       // stock
                1L,                        // stockMin
                null,                      // imageURL (opcional)
                "Rojo",                    // color
                null,                      // size (opcional)
                1L,                        // brandId
                1L                         // subCategoryId
        );

        Set<ConstraintViolation<CreateProductDTO>> violations = validator.validate(productDTO);

        assertTrue(violations.isEmpty(), "El producto debería tener un un nombre menor a 60 caracteres");
    }

    @Test
    void testCreateProductWithNameWithFiftySixtyOneCharacters() {
        CreateProductDTO productDTO = new CreateProductDTO(
                "Verifica que el campo nombre permita solo valores con una lon",     // name
                "Descripción de prueba",   // description
                100.0,                     // price
                10L,                       // stock
                1L,                        // stockMin
                null,                      // imageURL (opcional)
                "Rojo",                    // color
                null,                      // size (opcional)
                1L,                        // brandId
                1L                         // subCategoryId
        );

        Set<ConstraintViolation<CreateProductDTO>> violations = validator.validate(productDTO);

        assertFalse(violations.isEmpty(), "El producto debería tener un un nombre menor a 60 caracteres");
    }

}
