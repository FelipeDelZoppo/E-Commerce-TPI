package tpi.backend.e_commerce.dto.ProductDTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateProductDTO {

    @NotBlank(message = "No puede estar vacio")
    @Size(min = 3, max = 20, message = "Debe tener entre 3 y 20 caracteres")
    private String name;

    @Size(max = 100)
    private String description;

    @Min(0)
    @NotNull(message = "Debe ingresar un precio")
    private Double price;

    @Min(0)
    @NotNull(message = "Debe ingresar un stock")
    private Long stock = 0L;

    @Min(0)
    @NotNull(message="Debe ingresar un stock minimo")
    private Long stockMin;

    private String imageURL;

    @NotNull(message = "Debe ingresar un id de marca")
    private Long brandId;
    @NotNull(message = "Debe ingresar un id de subcategoria")
    private Long subCategoryId;
    
    public CreateProductDTO(String name, String description, Double price, Long stock, 
        Long stockMin, String imageURL, Long brandId, Long subCategoryId) {
        
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.stockMin = stockMin;
        this.imageURL = imageURL;
        this.brandId = brandId;
        this.subCategoryId = subCategoryId;
    }
    
}
