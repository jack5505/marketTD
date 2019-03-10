package uz.dukon.controllers.application.product.print;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Jack on 21.02.2019.
 */
public class Print {
    public Print(List<PrintModel> list)
    {
        System.out.println("Start...");
        String printName = null;
        String path = "";
        System.out.println(path);
        path ="c:\\Program Files\\easySqlad\\report.jasper";
        JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(list);
        HashMap hashMap = new HashMap();
        try {
            printName = JasperFillManager.fillReportToFile(path,hashMap,jrBeanCollectionDataSource);
            if(printName != null){
                JasperPrintManager.printReport(printName,false);
            }
        } catch (JRException e) {
            e.printStackTrace();
        }

    }
}
