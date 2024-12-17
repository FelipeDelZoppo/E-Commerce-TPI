package tpi.backend.e_commerce.UserTests.UnitTests;

import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tpi.backend.e_commerce.controllers.OrderController;
import tpi.backend.e_commerce.repositories.IOrderRepository;
import tpi.backend.e_commerce.services.order.interfaces.IFindOrderService;

@SpringBootTest
public class Test15OrderHistory {
    
    @Autowired
    private OrderController orderController;

    @Autowired
    private IFindOrderService findOrderService;

    @Autowired
    private IOrderRepository orderRepository;

    
    
}
