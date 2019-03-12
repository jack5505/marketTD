package uz.dukon.controllers.application.product.content;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import uz.dukon.App;
import uz.dukon.controllers.application.widgets.WPopup;
import uz.dukon.controllers.model.ReportData;
import uz.dukon.service.TransactionsService;
import uz.dukon.utils.FxmlUrl;

import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Jack on 23.02.2019.
 */
public class TotalReportController implements Initializable {

    @FXML
    private JFXButton btnAdd;

    @FXML
    private AnchorPane anchor;


    @FXML
    private DatePicker startDate;

    @FXML
    private DatePicker endDate;

    @FXML
    private TableView<ReportData> tableReport;

    @FXML
    private TableColumn<ReportData,Long> columnId;

    @FXML
    private TableColumn<ReportData,String> columnProductN;

    @FXML
    private TableColumn<ReportData,String> columnCount;

    @FXML
    private TableColumn<ReportData,BigDecimal>  columnTotal;

    @FXML
    private TableColumn<ReportData,String> totalMetirP;

    private List<ReportData> tablesProta;

    //here is all sold value total sell
    @FXML
    private Label totalValue;

    @FXML
    private JFXButton pieChart;

    //bu jami sotilgan tovarlar summasi
    private BigDecimal totalMarkPrice;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        totalMarkPrice = new BigDecimal("0");
        prepareTables();
        events();
        startDate.setValue(LocalDate.now());
        endDate.setValue(LocalDate.now());
        tablesProta = App.ctx.getBean(TransactionsService.class).reportDataFill(startDate.getValue(),endDate.getValue());
        generateTablesData();



    }

    private void generateTablesData() {
        tablesProta.forEach(reportData -> {
            ReportData reportData1 = new ReportData();
            reportData1.setId(reportData.getId());
            reportData1.setTotalPrice(reportData.getTotalPrice());
            reportData1.setProductName(reportData.getProductName());
            reportData1.setCountToShow(reportData.getCountToShow());
            reportData1.setTotalMetirToShow(reportData.getDimensionType());
            totalMarkPrice = totalMarkPrice.add(reportData.getTotalPrice());
            tableReport.getItems().add(reportData1);
        });
        totalValue.setText(totalMarkPrice.toString());
    }

    private void events()
    {
        btnAdd.setOnAction(event ->
        {
           //here takes two value from DatePicker and gets all the information from DB
            totalValue.setText("0");
            totalMarkPrice = BigDecimal.ZERO;
            tablesProta = App.ctx.getBean(TransactionsService.class).reportDataFill(startDate.getValue(),endDate.getValue());
            tableReport.getItems().clear();
            generateTablesData();
        });

        anchor.setOnKeyPressed(event ->
        {
            Stage stage = (Stage) ((AnchorPane)event.getSource()).getScene().getWindow();
            stage.getScene().getAccelerators().put(KeyCodeCombination.keyCombination("ctrl+enter"),() ->
            {
                WPopup wPopup = new WPopup(FxmlUrl.Product.askDeletedView,"Ўчириш");
                AskDeletedController askDeletedController = wPopup.getController();
                askDeletedController.deleteReport();
                wPopup.show();
            });

        });

        //Call Pie Chart
        pieChart.setOnAction(event ->
        {

            WPopup wPopup = new WPopup(FxmlUrl.Product.pieChartView,"");
            PieChartController pieChartController = wPopup.getController();
            pieChartController.prepareToShow(tablesProta,startDate.getValue(),endDate.getValue());
            wPopup.show();

        });
    }

    private void prepareTables() {
        columnId.setCellValueFactory(new PropertyValueFactory<ReportData, Long>("id"));
        columnProductN.setCellValueFactory(new PropertyValueFactory<ReportData, String>("productName"));
        columnCount.setCellValueFactory(new PropertyValueFactory<ReportData, String>("countToShow"));
        columnTotal.setCellValueFactory(new PropertyValueFactory<ReportData, BigDecimal>("totalPrice"));
        totalMetirP.setCellValueFactory(new PropertyValueFactory<ReportData, String>("totalMetirToShow"));
        totalValue.setText("0");
    }
}
