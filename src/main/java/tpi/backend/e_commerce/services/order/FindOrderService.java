package tpi.backend.e_commerce.services.order;

import java.util.List;
import java.util.Optional;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import tpi.backend.e_commerce.dto.orderDTO.ResponseOrderDto;
import tpi.backend.e_commerce.mapper.OrderMapper;
import tpi.backend.e_commerce.models.User;
import tpi.backend.e_commerce.repositories.IOrderRepository;
import tpi.backend.e_commerce.repositories.IUserRepository;
import tpi.backend.e_commerce.services.order.interfaces.IFindOrderService;
import tpi.backend.e_commerce.validation.Validation;

@Service
public class FindOrderService implements IFindOrderService{

    @Autowired
    private IOrderRepository orderRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private Validation validation;
    
    @Override
    public ResponseEntity<?> findOrdersByUserEmail(String email) {

        //Verifica que el id corresponda a un usuario existente
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty()){
            return validation.validate(
                "id",
                "No existe un usuario con ese email en la base de datos",
                404
            );
        }

        List<ResponseOrderDto> ordersOfUser = getOrdersOfUser(email);

        if (ordersOfUser.isEmpty()) {
            return ResponseEntity.ok("No se han encontrado pedidos. ¡Explora nuestros productos y realiza tu primera compra!");
        }

        return ResponseEntity.ok(ordersOfUser);

    }

    private List<ResponseOrderDto> getOrdersOfUser(String email) {
        return OrderMapper.toDtoList(orderRepository.findOrdersByUserEmail(email));
    }
    
}
