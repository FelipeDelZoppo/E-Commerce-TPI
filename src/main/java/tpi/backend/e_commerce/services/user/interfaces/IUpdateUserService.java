package tpi.backend.e_commerce.services.user.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import tpi.backend.e_commerce.dto.user.UpdatePasswordDto;
import tpi.backend.e_commerce.dto.user.UpdateUserDto;


public interface IUpdateUserService {
    ResponseEntity<?> update(Long id, UpdateUserDto userDto, BindingResult result);
    ResponseEntity<?> updatePassword(String email, UpdatePasswordDto passwordDto, BindingResult result);
}
