package uz.dukon.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.dukon.controllers.model.ReportData;
import uz.dukon.repository.TransactionRepository;
import uz.dukon.service.TransactionsService;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
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
        HashMap<BigInteger,ReportData> hashMap = new HashMap<>();
        List<Object[]> list = transactionRepository.reportForTable(start,end);
        list.forEach(objects -> {
            if(hashMap.containsKey(objects[0]))
            {
                if(objects[3] != null)
                     hashMap.get(objects[0]).setCount(hashMap.get(objects[0]).getCount().add((BigDecimal) objects[3]));
                if(objects[5] != null)
                     hashMap.get(objects[0]).setTotalMetirP(hashMap.get(objects[0]).getTotalMetirP().add((BigDecimal)objects[5]));
                hashMap.get(objects[0]).setTotalPrice(hashMap.get(objects[0]).getTotalPrice().add((BigDecimal) objects[4]));
            }
            else{
                ReportData reportData = new ReportData();
                reportData.setTotalPrice((BigDecimal) objects[4]);
                if(objects[3] != null)
                    reportData.setCount((BigDecimal)objects[3]);
                else
                    reportData.setCount(new BigDecimal("0"));
                reportData.setProductName((String) objects[1]);
                reportData.setDimensionType((String) objects[2]);
                if(objects[5] != null)
                     reportData.setTotalMetirP((BigDecimal)objects[5]);
                else
                    reportData.setTotalMetirP(new BigDecimal("0"));
                hashMap.put((BigInteger) objects[0],reportData);
            }
        });
        List<ReportData> list1 = new ArrayList<>();
        hashMap.forEach((aLong, reportData) ->
        {
            ReportData reportData1 = new ReportData();
            reportData1.setId((long) (list1.size() + 1));
            reportData1.setDimensionType(reportData.getDimensionType());
            reportData1.setProductName(reportData.getProductName());
            reportData1.setCount(reportData.getCount());
            reportData1.setTotalPrice(reportData.getTotalPrice());
            reportData1.setTotalMetirP(reportData.getTotalMetirP());
            list1.add(reportData1);

        });
        return list1;
    }

    //Parolni tekshirish bazadaki parol bilan

    @Override
    public void updateDeletedAll() {
        transactionRepository.updateDelete();
    }
}
