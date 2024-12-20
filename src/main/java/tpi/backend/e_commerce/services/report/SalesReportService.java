package tpi.backend.e_commerce.services.report;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import tpi.backend.e_commerce.mapper.SalesReportMapper;
import tpi.backend.e_commerce.models.Order;
import tpi.backend.e_commerce.repositories.IOrderRepository;
import tpi.backend.e_commerce.services.report.interfaces.ISalesReportService;
import tpi.backend.e_commerce.validation.Validation;

@Service    
public class SalesReportService implements ISalesReportService {

    @Autowired
    private Validation validation;
    
    @Autowired
    private IOrderRepository orderRepository;

    @Override
    public ResponseEntity<?> salesReportByDate(String date) {
        LocalDate dateParse;
        try {
            dateParse = LocalDate.parse(date);
        } catch (Exception e) {
            return validation.validate(
                "date",
                "Formato de fecha incorrecto. El formato correcto es yyyy-MM-dd",
                400
            );
        }

        if (dateParse.isAfter(LocalDate.now())) {
            return validation.validate(
                "date",
                "La fecha ingresada es es mayor al dia de hoy",
                400
            );
        }

        List<Order> orders = orderRepository.findOrdersByDate(dateParse);

        if (orders.isEmpty()) {
            return validation.validate(
                "date", 
                "No existen ventas registradas el " + dateParse.toString(), 
                404
            );
        }

        double incomeOfTheDay = calculateIncomeOfDay(orders);

        return ResponseEntity.ok(SalesReportMapper.toDailySalesReportDto(dateParse,incomeOfTheDay ,orders));
    }

    private double calculateIncomeOfDay(List<Order> orders){
        double income = 0;
        for (Order order : orders) {
            income += order.getTotal();
        }
        return income;
    }
}


