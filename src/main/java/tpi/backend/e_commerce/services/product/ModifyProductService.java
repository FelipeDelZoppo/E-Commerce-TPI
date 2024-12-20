package tpi.backend.e_commerce.services.product;

import org.springframework.stereotype.Service;

import tpi.backend.e_commerce.models.Product;
import tpi.backend.e_commerce.services.product.interfaces.IModifyProductService;

@Service
public class ModifyProductService implements IModifyProductService {

    @Override
    public Product discountStock(Product product, Integer discount) throws RuntimeException{
        if (product.getStock() < discount) {
            throw new RuntimeException("No hay suficiente stock del producto con id " + product.getId());
        }
        product.setStock(product.getStock() - discount);
        return product;
    }
}
