package uz.dukon.controllers.application.product.content;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.AnchorPane;
import uz.dukon.controllers.model.ReportData;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Jack on 12.03.2019.
 */
public class PieChartController implements Initializable
{
    @FXML
    private AnchorPane anchor;

    private ObservableList<PieChart.Data> pieData;
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {



    }
    public void prepareToShow(List<ReportData> tablesProta, LocalDate start,LocalDate end)
    {

        pieData = FXCollections.observableArrayList();
        tablesProta.forEach(reportData -> {
               pieData.add(new PieChart.Data(reportData.getProductName(),Double.parseDouble(reportData.getCountToShow().toString())));
        });
        pieData.forEach(data -> {
            data.nameProperty().bind(Bindings.concat(data.getName()," ",data.pieValueProperty()));
        });
        PieChart budget = new PieChart(pieData);
        budget.setTitle("Shu sanalar  "+start+"   "+end +" oraligidagi ma'lumot");
        anchor.getChildren().add(budget);

    }
}
