package tpi.backend.e_commerce.ProductTests.UnitTests;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tpi.backend.e_commerce.dto.ProductDTO.CreateProductDTO;
import tpi.backend.e_commerce.repositories.IBrandRepository;
import tpi.backend.e_commerce.repositories.IProductRepository;
import tpi.backend.e_commerce.repositories.ISubCategoryRepository;
import tpi.backend.e_commerce.services.product.interfaces.ISaveProductService;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
public class Test29RegisterProductWithPriceUpToZero {

    private static Validator validator;

    @BeforeAll
    static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testCreateProductWithPriceDownToOne() {
        CreateProductDTO productDTO = new CreateProductDTO(
                "Producto de prueba",     // name
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

        assertFalse(violations.isEmpty(), "El producto debería tener un valor mayor o igual a 1");
    }

    @Test
    void testCreateProductWithPriceEqualsToOne() {
        CreateProductDTO productDTO = new CreateProductDTO(
                "Producto de prueba",     // name
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

        assertTrue(violations.isEmpty(), "El producto debería tener un valor mayor o igual a 1");
    }

    @Test
    void testCreateProductWithPriceUpToOne() {
        CreateProductDTO productDTO = new CreateProductDTO(
                "Producto de prueba",     // name
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

        assertTrue(violations.isEmpty(), "El producto debería tener un valor mayor o igual a 1");
    }
}
