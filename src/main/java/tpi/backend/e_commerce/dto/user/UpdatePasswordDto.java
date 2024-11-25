package tpi.backend.e_commerce.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePasswordDto {
    
    @NotBlank(message = "No puede estar vacio")
    @Size(min = 8, max = 22, message = "Debe tener entre 8 y 22 caracteres")
    private String currentPassword;

    @NotBlank(message = "No puede estar vacio")
    @Size(min = 8, max = 22, message = "Debe tener entre 8 y 22 caracteres")
    private String newPassword;
}
