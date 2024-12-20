package tpi.backend.e_commerce.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import tpi.backend.e_commerce.models.Order;

public interface IOrderRepository extends CrudRepository<Order,Long> {
    
    List<Order> findOrdersByUserEmail(String email);

    @Query("SELECT o FROM Order o WHERE FUNCTION('DATE', o.creation_datetime) = ?1")
    List<Order> findOrdersByDate(LocalDate date);
}
