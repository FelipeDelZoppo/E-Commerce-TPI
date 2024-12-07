package tpi.backend.e_commerce.orderTests.unitTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import tpi.backend.e_commerce.mapper.OrderMapper;
import tpi.backend.e_commerce.models.Order;
import tpi.backend.e_commerce.models.User;
import tpi.backend.e_commerce.repositories.IOrderRepository;
import tpi.backend.e_commerce.repositories.IUserRepository;
import tpi.backend.e_commerce.services.order.FindOrderService;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertTrue;

@ExtendWith(MockitoExtension.class)
public class Test15OrderHistoryEmpty {

    @InjectMocks
    private FindOrderService findOrderService;

    @Mock
    private IOrderRepository orderRepository;

    @Mock
    private IUserRepository userRepository;

    private User user;

    private List<Order> listOfOrders;

    @BeforeEach
    public void init() {
        user = User.builder()
                .id(123L)
                .firstName("Ariel")
                .lastName("Cometto")
                .email("arielcometto@gmail.com")
                .build();

        Order order_one = Order.builder()
                .id(1L)
                .user(user)
                .orderDetails(List.of())
                .total(1500.00)
                .build();

        Order order_two = Order.builder()
                .id(2L)
                .user(user)
                .orderDetails(List.of())
                .total(5500.00)
                .build();

        listOfOrders = List.of(order_one, order_two);
    }

    @Test
    public void testStatusWithoutPurchases() {
        when(userRepository.findByEmail("arielcometto@gmail.com")).thenReturn(Optional.of(user));
        when(orderRepository.findOrdersByUserEmail(user.getEmail())).thenReturn(List.of());

        ResponseEntity<?> result = findOrderService.findOrdersByUserEmail("arielcometto@gmail.com");

        assertTrue("El statusCode es 200", result.getStatusCode().is2xxSuccessful());
        assertEquals("El mensaje es correcto",
                "No se han encontrado pedidos. ¡Explora nuestros productos y realiza tu primera compra!",
                result.getBody());
    }

    @Test
    public void testStatusWithPurchases() {
        when(userRepository.findByEmail("arielcometto@gmail.com")).thenReturn(Optional.of(user));
        when(orderRepository.findOrdersByUserEmail(user.getEmail())).thenReturn(listOfOrders);

        ResponseEntity<?> result = findOrderService.findOrdersByUserEmail("arielcometto@gmail.com");

        assertTrue("El statusCode es 200", result.getStatusCode().is2xxSuccessful());
        assertEquals("Los bodys son iguales", OrderMapper.toDtoList(listOfOrders), result.getBody());
    }

}
