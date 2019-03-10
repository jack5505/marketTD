package uz.dukon.service;

import uz.dukon.controllers.model.ReportData;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Jack on 23.02.2019.
 */
public interface TransactionsService {
    List<ReportData> reportDataFill(LocalDate start, LocalDate  end);
    void updateDeletedAll();
}
