package tpi.backend.e_commerce.services.report.interfaces;

import org.springframework.http.ResponseEntity;

public interface ISalesReportService {
    
    ResponseEntity<?> salesReportByDate(String date); 
}
