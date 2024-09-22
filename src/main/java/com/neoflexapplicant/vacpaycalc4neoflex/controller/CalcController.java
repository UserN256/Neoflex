package com.neoflexapplicant.vacpaycalc4neoflex.controller;

import com.neoflexapplicant.vacpaycalc4neoflex.dto.VacPayEntity;
import com.neoflexapplicant.vacpaycalc4neoflex.service.CalcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
public class CalcController {
    private final CalcService calcService;

    @Autowired
    public CalcController(CalcService calcService){
        this.calcService = calcService;
    }
    @ResponseBody
    @PostMapping(value = "/calculacte")
    public ResponseEntity<List<VacPayEntity>> calc() {
        final List<VacPayEntity> answer = calcService.calcAll();

        return answer != null &&  !answer.isEmpty()
                ? new ResponseEntity<>(answer, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/calculacte")
    public ResponseEntity<List<VacPayEntity>> calcget(
        @RequestParam("averageSalary") BigDecimal avgSalaryPerYear,
        @RequestParam("numofVacationDays") int numofVacationDays,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Optional<LocalDate> dateofVacationStart) {

        final List<VacPayEntity> answer = calcService.calcAll(avgSalaryPerYear, numofVacationDays);

        return answer != null &&  !answer.isEmpty()
                ? new ResponseEntity<>(answer, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
