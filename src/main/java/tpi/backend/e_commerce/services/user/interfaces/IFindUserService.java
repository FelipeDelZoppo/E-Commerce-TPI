package tpi.backend.e_commerce.services.user.interfaces;

import org.springframework.http.ResponseEntity;

public interface IFindUserService {

    ResponseEntity<?> findAllActive();
    ResponseEntity<?> findAllDeleted();
    
    ResponseEntity<?> findActiveById(Long id);
}
