package uz.dukon.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.dukon.controllers.model.ReportData;
import uz.dukon.repository.TransactionRepository;
import uz.dukon.service.TransactionsService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jack on 23.02.2019.
 */
@Service
public class TransactionsServiceImpl implements TransactionsService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Override
    public List<ReportData> reportDataFill(LocalDate start, LocalDate end)
    {
        List<Object[]> list = transactionRepository.reportForTable(start,end);
        List<ReportData> list1 = new ArrayList<>();
        list.forEach(objects ->
        {
            ReportData reportData = new ReportData();
            reportData.setId((long) (list1.size() + 1));
            reportData.setDimensionType((String) objects[2]);
            reportData.setProductName((String) objects[1]);
            reportData.setCountToShow(((BigDecimal)objects[3]));
            reportData.setTotalPrice((BigDecimal) objects[4]);
            list1.add(reportData);

        });

        return list1;
    }

    //Parolni tekshirish bazadaki parol bilan

    @Override
    public void updateDeletedAll() {
        transactionRepository.updateDelete();
    }
}
