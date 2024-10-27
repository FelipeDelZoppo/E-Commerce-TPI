package tpi.backend.e_commerce.services.brand;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertFalse;
import static org.springframework.test.util.AssertionErrors.assertTrue;

import tpi.backend.e_commerce.models.Brand;
import tpi.backend.e_commerce.repositories.IBrandRepository;
import tpi.backend.e_commerce.services.brand.interfaces.IDeleteBrandService;
import tpi.backend.e_commerce.validation.Validation;

@SpringBootTest
public class DeleteBrandServiceTest {

@Mock
private static IBrandRepository brandRepositoryMock;

@Mock
private static Validation validationMock;

private static IDeleteBrandService deleteBrandService;

@BeforeAll
public void init() {
    deleteBrandService = new DeleteBrandService();
}

@Test
public void testDeleteSuccessfully() {

    Brand brandExpected = Brand.builder()
        .name("testBrand")
        .id(123L)
        .build();

    when(brandRepositoryMock.findById(123L)).thenReturn(Optional.of(brandExpected));
    when(brandRepositoryMock.hasBrandProducts(123L)).thenReturn(false);
    doNothing().when(brandRepositoryMock.save(any()));

    ResponseEntity<?> response = deleteBrandService.delete(123L);


    assertTrue("", brandExpected.isDeleted());

}

@Test
public void testDeleteBrandNonExistent() {

    when(brandRepositoryMock.findById(123L)).thenReturn(Optional.empty());

    ResponseEntity<?> expectedResult = validationMock.validate("id",
                "No existe una marca con ese id",
                404);

    ResponseEntity<?> response = deleteBrandService.delete(123L);

    assertEquals("", expectedResult, response);

}

@Test
public void testDeleteBrandWithProductsAssociates() {
    Brand brandExpected = Brand.builder()
    .name("testBrand")
    .id(123L)
    .build();

    when(brandRepositoryMock.findById(123L)).thenReturn(Optional.of(brandExpected));
    when(brandRepositoryMock.hasBrandProducts(123L)).thenReturn(true);

    ResponseEntity<?> expectedResult = validationMock.validate( "id",
        "La marca tiene productos asociados",
        409);
    
    ResponseEntity<?> response = deleteBrandService.delete(123L);

    assertEquals("", expectedResult, response);
}

@Test
public void testRecoverBrandSuccesfully() {
    Brand brandExpected = Brand.builder()
    .name("testBrand")
    .id(123L)
    .build();

    when(brandRepositoryMock.findById(123L)).thenReturn(Optional.of(brandExpected));

    ResponseEntity<?> response = deleteBrandService.delete(123L);

    assertFalse("", brandExpected.isDeleted());
}

@Test
public void testRecoverBrandNonExists() {
    when(brandRepositoryMock.findById(123L)).thenReturn(Optional.empty());

    ResponseEntity<?> expectedResult = validationMock.validate( 
        "id", 
        "No existe una marca con ese id", 
        404);
    
    ResponseEntity<?> response = deleteBrandService.delete(123L);

    assertEquals("", expectedResult, response);
}

}
