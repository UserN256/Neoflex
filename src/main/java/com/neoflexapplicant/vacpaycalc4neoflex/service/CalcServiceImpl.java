package com.neoflexapplicant.vacpaycalc4neoflex.service;

import com.neoflexapplicant.vacpaycalc4neoflex.dto.VacPayEntity;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CalcServiceImpl implements CalcService {
    // ** TODO: разобраться почему @Value не подхватываются из application.properties файла */
    //@Value("${avgWorkingDaysPerMonthString}")
    //private String avgWorkingDaysPerMonthString;

    public final BigDecimal avgWorkingDaysPerMonth = new BigDecimal( "29.3");
    public final BigDecimal numberOfMonths = new BigDecimal("12");
    public final BigDecimal MROT = new BigDecimal("19242");
    /* Чтобы отнять 13% НДФЛ нужно умножить на 0,87*/
    public final BigDecimal NDFLmultiplier = new BigDecimal("0.87");

    @Override
    public List<VacPayEntity> calcAll(BigDecimal avgSalaryPerYear, int numOfVacationDays){
        List<VacPayEntity> list = new ArrayList<>();

        /* Поиск дневной зарплаты на основании годовой и среднего количества дней */
        BigDecimal value = avgSalaryPerYear.divide(numberOfMonths,2, RoundingMode.HALF_UP);
        /* Проверка что не ниже МРОТ, тогда замена на МРОТ */
        if (value.compareTo(MROT) == 1) value = MROT;

        value = value.divide(avgWorkingDaysPerMonth, 2, RoundingMode.HALF_UP);
        value = value.multiply(new BigDecimal(String.valueOf(numOfVacationDays)));

        /* Вычитаине НДФЛ налога у работника*/
        value = value.multiply(NDFLmultiplier);
        list.add(new VacPayEntity("",value));

        return list;
    }

    @Override
    public List<VacPayEntity> calcAll(BigDecimal avgSalaryPerYear, int numOfVacationDays, Optional<LocalDate> dateofVacationStart){
        List<VacPayEntity> list = new ArrayList<>();

        /* Поиск дневной зарплаты на основании годовой и среднего количества дней */
        BigDecimal value = avgSalaryPerYear.divide(numberOfMonths,2, RoundingMode.HALF_UP);
        /* Проверка что не ниже МРОТ, тогда замена на МРОТ */
        if (value.compareTo(MROT) == 1) value = MROT;

        value = value.divide(avgWorkingDaysPerMonth, 2, RoundingMode.HALF_UP);
        value = value.multiply(new BigDecimal(String.valueOf(numOfVacationDays)));

        /* Вычитаине НДФЛ налога у работника*/
        value = value.multiply(NDFLmultiplier);
        list.add(new VacPayEntity("",value));

        return list;
    }
}
