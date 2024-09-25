package com.neoflexapplicant.vacpaycalc4neoflex.service;

import com.neoflexapplicant.vacpaycalc4neoflex.dto.VacPayEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CalcService {

    List<VacPayEntity> calcAll(BigDecimal avgSalaryPerYear, int numOfVacationDays);

    List<VacPayEntity> calcAll(BigDecimal avgSalaryPerYear, int numOfVacationDays, Optional<LocalDate> dateofVacationStart);
}
