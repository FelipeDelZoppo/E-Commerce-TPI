package tpi.backend.e_commerce.services.product;

import java.util.Optional;
import java.util.Collections;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;


import tpi.backend.e_commerce.dto.ProductDTO.CreateProductDTO;
import tpi.backend.e_commerce.mapper.ProductMapper;
import tpi.backend.e_commerce.models.Brand;
import tpi.backend.e_commerce.models.Product;
import tpi.backend.e_commerce.models.SubCategory;
import tpi.backend.e_commerce.repositories.IBrandRepository;
import tpi.backend.e_commerce.repositories.IProductRepository;
import tpi.backend.e_commerce.repositories.ISubCategoryRepository;
import tpi.backend.e_commerce.services.product.interfaces.ISaveProductService;
import tpi.backend.e_commerce.validation.Validation;

@Service
public class SaveProductService implements ISaveProductService{

    @Autowired
    private IProductRepository productRepository;
    @Autowired
    private ISubCategoryRepository subCategoryRepository;
    @Autowired
    private IBrandRepository brandRepository;
    @Autowired
    private Validation validation;
    
    @Override
    public ResponseEntity<?> save(CreateProductDTO createProductDTO , BindingResult result) {

        if (productRepository.existsByName(createProductDTO.getName())) {
            result.rejectValue(
                "name", 
                "", 
                "Ya existe un producto con ese nombre"
            );
        }

        result = nameProductValidation(result, createProductDTO); 
        //Las validaciones de producto estan en un metodo privado aparte
        
        if (result.hasFieldErrors()) {
            return validation.validate(result);
        }
        
        Optional<SubCategory> optionalSubCategory = subCategoryRepository.findActiveById(createProductDTO.getSubCategoryId()); 
        if (optionalSubCategory.isEmpty()) { //Si no existe la categoria mandada por la peticion, retorno un 404
            return ResponseEntity.status(404).body(
                Collections.singletonMap("subCategoryId","La sub categoria ingresada no existe")
            );
        }
        Optional<Brand> optionalBrand = brandRepository.findActiveById(createProductDTO.getBrandId()); 
        if (optionalBrand.isEmpty()) { //Si no existe la marca mandada por la peticion, retorno un 404
            return ResponseEntity.status(404).body(
                Collections.singletonMap("brandId","La marca ingresada no existe")
            );
        }
        Product productToSave = ProductMapper.toEntity(createProductDTO, optionalSubCategory.get(), optionalBrand.get()); 
        //Convierto el product de la peticion en un product de la bd a traves del mapper

        return ResponseEntity.status(HttpStatus.CREATED).body(ProductMapper.toDTO(productRepository.save(productToSave)));
    }


    @Override
    public ResponseEntity<?> update(Long id, CreateProductDTO createProductDTO, BindingResult result) {

        if (productRepository.existsByNameExceptId(createProductDTO.getName(),id)) {
            result.rejectValue(
                "name", 
                "", 
                "Ya existe un producto con ese nombre"
            );
        }

        result = nameProductValidation(result, createProductDTO); 

        if (result.hasFieldErrors()) {
            return validation.validate(result);
        }
        Optional<Product> optionalProduct = productRepository.findActiveById(id); 
        if (optionalProduct.isEmpty()) { //Si no existe la categoria mandada por la peticion, retorno un 404
            return ResponseEntity.status(404).body(
                Collections.singletonMap("id","El id ingresado no corresponde a ningun producto")
            );
        }
        Optional<SubCategory> optionalSubCategory = subCategoryRepository.findActiveById(createProductDTO.getSubCategoryId()); 
        if (optionalSubCategory.isEmpty()) { //Si no existe la categoria mandada por la peticion, retorno un 404
            return ResponseEntity.status(404).body(
                Collections.singletonMap("subCategoryId","La sub categoria ingresada no existe")
            );        }
        Optional<Brand> optionalBrand = brandRepository.findActiveById(createProductDTO.getBrandId()); 
        if (optionalBrand.isEmpty()) { //Si no existe la marca mandada por la peticion, retorno un 404
            return ResponseEntity.status(404).body(
                Collections.singletonMap("brandId","La marca ingresada no existe")
            );
        }

        Product productToSave = ProductMapper.toUpdate(createProductDTO, id ,optionalSubCategory.get(), optionalBrand.get()); 
        //Convierto el product de la peticion en un product de la bd a traves del mapper

        productToSave.setCreationDatetime(optionalProduct.get().getCreationDatetime());
        //Asigno al producto a guardar en la bd la fecha de creacion que tenia el producto antes de ser actualizado

        return ResponseEntity.ok(ProductMapper.toDTO(productRepository.save(productToSave)));

    }

    private BindingResult nameProductValidation(BindingResult result, CreateProductDTO createProductDTO) {

        //Chequea que el primer caracter sea un digito o una letra
        char firstChar = createProductDTO.getName().charAt(0);
        if (!Character.isLetterOrDigit(firstChar)) {
            result.rejectValue(
                "name", 
                "", 
                "El primer caracter debe ser un numero o una letra"
            );     
        }

        //Chequea que al menos un caracter sea una letra
        boolean letra = false;
        for (int i = 0; i < createProductDTO.getName().length(); i++) {
            if (Character.isLetter(createProductDTO.getName().charAt(i))) {
                letra = true;
            }
        }
        if (!letra) {
            result.rejectValue(
                "name", 
                "", 
                "El nombre debe contener al menos una letra"
            );
        }

        return result;
    }
    
}
