package tpi.backend.e_commerce.repositories;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import tpi.backend.e_commerce.models.Brand;

public interface IBrandRepository extends CrudRepository<Brand,Long>{
    @Query("select b from Brand b where b.deleted = false")
    List<Brand> findAllActive(); 

    @Query("select b from Brand b where b.deleted = true")
    List<Brand> findAllDeleted();
    
    @Query("select b from Brand b where b.id = ?1 and b.deleted = false")
    Optional<Brand> findActiveById(Long id);
    
    @Query("select b from Brand b where b.id = ?1 and b.deleted = true")
    Optional<Brand> findDeletedById(Long id);

    boolean existsByName(String name);

    @Query("select CASE when COUNT(b) > 0 then true else false end from Brand b where b.name = ?1 and b.id <> ?2" )
    boolean existsByNameExceptId(String name, Long id);

}
