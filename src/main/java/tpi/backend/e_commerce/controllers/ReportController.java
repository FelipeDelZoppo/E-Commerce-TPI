package tpi.backend.e_commerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tpi.backend.e_commerce.services.report.interfaces.ISalesReportService;

@RestController
@RequestMapping("/reports")
@CrossOrigin(origins = "http://localhost:5173")
public class ReportController {

    @Autowired
    private ISalesReportService salesReportService;

    //Endpoint que devuelve un informe de las ventas de la fecha ingresada
    @GetMapping("/{date}")
    public ResponseEntity<?> salesReportByDate(@PathVariable String date){
        return salesReportService.salesReportByDate(date);
    }
}
