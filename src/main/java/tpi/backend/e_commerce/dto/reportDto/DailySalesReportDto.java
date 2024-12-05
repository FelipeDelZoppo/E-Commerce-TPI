package tpi.backend.e_commerce.dto.reportDto;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tpi.backend.e_commerce.dto.orderDTO.ResponseOrderDto;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DailySalesReportDto {
    
    private LocalDate date; //yyyy-MM-dd
    private double incomeOfDay;
    private List<ResponseOrderDto> orders;
}
