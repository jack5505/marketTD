package uz.dukon.controllers.application.product.content;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import uz.dukon.App;
import uz.dukon.controllers.application.product.events.StartAgainEvent;
import uz.dukon.controllers.application.product.print.Print;
import uz.dukon.controllers.application.product.print.PrintModel;
import uz.dukon.controllers.model.OrderDto;
import uz.dukon.service.OrderService;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PurchaseFinishController implements Initializable {


    @FXML
    private Label totalCost;

    @FXML
    private Label totalPaidSumma;


    @FXML
    private Label change;

    @FXML
    private JFXButton btn;

    private OrderDto orderDto;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        btn.setOnAction(event ->
        {
            Stage stage = (Stage) ((JFXButton)event.getSource()).getScene().getWindow();
            stage.close();
        });
    }
    public void prepareForFinalPart(OrderDto orderDto){
        this.orderDto = orderDto;
        totalCost.setText(orderDto.getTotalAmount().toString());
        totalPaidSumma.setText(orderDto.getCashSum().add(orderDto.getPcardSuma()).toString());
        BigDecimal bigDecimal = orderDto.getTotalAmount();
        bigDecimal = bigDecimal.subtract(new BigDecimal(totalPaidSumma.getText()));
        change.setText(bigDecimal.toString());
        List<PrintModel> printModelList = new ArrayList<>();
        orderDto.getList().forEach(transactionDto ->
        {
            PrintModel printModel  = new PrintModel();
            printModel.setPrice(transactionDto.getProductPrice());
            printModel.setSumma(transactionDto.getProductTotalSellPrice());
            printModel.setPaidSumma(new BigDecimal(totalPaidSumma.getText()));
            printModel.setChange(new BigDecimal(change.getText()));
            printModel.setQuantity(transactionDto.getProductCount());
            printModel.setName(transactionDto.getProductName());
            printModel.setDimension(transactionDto.getDimension());
            printModel.setJami(new BigDecimal(totalCost.getText()));
            printModelList.add(printModel);
        });
        Print print = new Print(printModelList);
        App.ctx.getBean(OrderService.class).save(orderDto);
        StartAgainEvent startAgainEvent = new StartAgainEvent(StartAgainEvent.ANY);
        App.eventBus.fireEvent(startAgainEvent);
    }
}
