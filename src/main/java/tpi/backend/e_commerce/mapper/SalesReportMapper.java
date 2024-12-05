package tpi.backend.e_commerce.mapper;

import java.time.LocalDate;
import java.util.List;

import tpi.backend.e_commerce.dto.reportDto.DailySalesReportDto;
import tpi.backend.e_commerce.models.Order;

public class SalesReportMapper {
    
    public static DailySalesReportDto toDailySalesReportDto(LocalDate date, double incomeOfDay, List<Order> orders) {
        return DailySalesReportDto
            .builder()
            .date(date)
            .incomeOfDay(incomeOfDay)
            .orders(OrderMapper.toDtoList(orders))
            .build();
    }
}
