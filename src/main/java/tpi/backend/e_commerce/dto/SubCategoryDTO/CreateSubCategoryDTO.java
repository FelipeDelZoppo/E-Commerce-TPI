package tpi.backend.e_commerce.dto.SubCategoryDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateSubCategoryDTO {

    @NotBlank(message = "No puede estar vacio")
    @Size(min = 3, max = 20, message = "Debe tener entre 3 y 20 caracteres")
    private String name; 

    @Size(max = 100)
    private String description; 
    
    @NotNull
    private Long categoryId;

    public CreateSubCategoryDTO(String name, String description, Long categoryId) {
        this.name = name;
        this.description = description;
        this.categoryId = categoryId;
    }
    
    
}
