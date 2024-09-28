package tpi.backend.e_commerce.services.product;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import tpi.backend.e_commerce.mapper.ProductMapper;
import tpi.backend.e_commerce.models.Product;
import tpi.backend.e_commerce.repositories.IProductRepository;
import tpi.backend.e_commerce.services.product.interfaces.IDeleteProductService;

@Service
public class DeleteProductService implements IDeleteProductService{
    @Autowired
    private IProductRepository productRepository;

    @Override
    public ResponseEntity<?> delete(Long id) {

        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            return ResponseEntity.status(404).body("Error: El producto ingresado no existe");
        }
        
        Product product = optionalProduct.get();
        if (product.getStock() > 0) {
            return ResponseEntity.status(409).body(
                "Error: No se puede eliminar un producto que no tenga stock en 0"
            );
        }

        product.setDeleted(true);
        product.setDeleteDatetime(LocalDateTime.now());
        productRepository.save(product);
        return ResponseEntity.noContent().build();
       
    }

    @Override
    public ResponseEntity<?> recover(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            return ResponseEntity.status(404).body("Error: El producto ingresado no existe");
        }
        Product product = optionalProduct.get();
        product.setDeleted(false);
        product.setDeleteDatetime(null);
        productRepository.save(product);
        return ResponseEntity.ok(ProductMapper.toDTO(product));
         /* 
        Si se ingresa el id de un producto que existe y esta activo,
        se tratara de la misma manera que si estuviera eliminado. Se setea el atributo
        deleted en false (Ya estaba en false porque esta activo)
        */
    }

}
