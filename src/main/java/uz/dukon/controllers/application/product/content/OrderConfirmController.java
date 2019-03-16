package uz.dukon.controllers.application.product.content;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import uz.dukon.App;
import uz.dukon.controllers.application.product.events.StartAgainEvent;
import uz.dukon.controllers.application.product.print.Print;
import uz.dukon.controllers.application.product.print.PrintModel;
import uz.dukon.controllers.model.OrderDto;
import uz.dukon.controllers.model.TransactionDto;
import uz.dukon.controllers.newmodel.CartTable;
import uz.dukon.service.OrderService;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Jack on 22.09.2018.
 */
public class OrderConfirmController implements Initializable {
    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnCancel;


    @FXML
    private JFXTextField cashTextField;

    @FXML
    private JFXTextField plasticTextField;

    @FXML
    private JFXRadioButton cashRadioBtn;

    @FXML
    private JFXRadioButton plasticRadioBtn;

    @FXML
    private JFXRadioButton bothTypeRadioBtn;

    private List<CartTable> productsToSave;

    @FXML
    private Label totalProductSumm;

    @FXML
    private ImageView plasticImage;

    @FXML
    private Label plasticLabel;

    @FXML
    private ImageView cashImage;

    @FXML
    private Label cashLabel;

    private OrderDto orderDto;

    @FXML
    private Label paidTotalSumm;

    @FXML
    private Label change;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        orderDto = new OrderDto();
        cashRadioBtn.setSelected(true);
        cashTextField.setDisable(true);
        onClick(btnAdd,btnCancel);
        registerEventHandler();
        addToTextFieldHandler();
        change.setText("0");
    }

    private void onClick(JFXButton btnSave, JFXButton btnCancel)
    {
        btnSave.setOnAction(event ->
        {
            if(!cashTextField.getText().isEmpty() || !plasticTextField.getText().isEmpty())
            {
                if(cashTextField.isVisible()){
                    orderDto.setCashSum(new BigDecimal(cashTextField.getText()));
                }
                if(plasticTextField.isVisible()){
                    orderDto.setPcardSuma(new BigDecimal(plasticTextField.getText()));
                }
                orderDto.setTotalAmount(new BigDecimal(totalProductSumm.getText()));
                btnSave.setDisable(true);
                btnSave.setText("Сакланяпти..");
                btnCancel.setDisable(true);
                prepareForPrint(orderDto);
                Stage stage = (Stage) ((JFXButton)event.getSource()).getScene().getWindow();
                stage.close();
            }

        });

        btnCancel.setOnAction(event ->
        {
            Stage stage = (Stage) ((JFXButton)event.getSource()).getScene().getWindow();
            stage.close();
        });

    }

    private void prepareForPrint(OrderDto orderDto)
    {
        //Exit of application printing of check here
        /*
        TODO EXIT PART OF PROGRAM LAST PLACE TO ENTER

         */
        List<PrintModel> printModelList = new ArrayList<>();
        orderDto.getList().forEach(transactionDto ->
        {
            PrintModel printModel = new PrintModel();
            printModel.setId((long) (printModelList.size() + 1));
            printModel.setName(transactionDto.getProductName());
            printModel.setQuantity(transactionDto.getProductCount());
            printModel.setSumma(transactionDto.getProductTotalSellPrice());
            printModel.setJami(new BigDecimal(cashTextField.getText()).add(new BigDecimal(plasticTextField.getText())));
            printModelList.add(printModel);
        });

        Print print = new Print(printModelList);
        App.ctx.getBean(OrderService.class).save(orderDto);
        StartAgainEvent startAgainEvent = new StartAgainEvent(StartAgainEvent.ANY);
        App.eventBus.fireEvent(startAgainEvent);
    }

    private void registerEventHandler() {
        cashRadioBtn.setOnAction(event -> {
           changeOrderPaymentSum();
        });

        plasticRadioBtn.setOnAction(event -> {
            changeOrderPaymentSum();
        });

        bothTypeRadioBtn.setOnAction(event -> {
            changeOrderPaymentSum();
        });

        addToTextFieldHandler();


    }


    private void changeOrderPaymentSum()
    {
        if(cashRadioBtn.isSelected())
        {
            orderDto.setPcardSuma(BigDecimal.ZERO);
            cashTextField.setText(orderDto.getTotalAmount().toString());
            plasticTextField.setDisable(true);
            setPaymentTypeCash();
            paidTotalSumm.setText(orderDto.getTotalAmount().toString());
            differenceChange(new BigDecimal(totalProductSumm.getText()));
        }
        if(plasticRadioBtn.isSelected()) {
            orderDto.setCashSum(BigDecimal.ZERO);
            plasticTextField.setText(orderDto.getTotalAmount().toString());
            cashTextField.setDisable(true);
            setPaymentTypePlastic();
            paidTotalSumm.setText(orderDto.getTotalAmount().toString());
            differenceChange(new BigDecimal(totalProductSumm.getText()));
        }

        if(bothTypeRadioBtn.isSelected())
        {
             orderDto.setCashSum(orderDto.getTotalAmount().divide(new BigDecimal("2"),2, BigDecimal.ROUND_UNNECESSARY));
             orderDto.setPcardSuma(orderDto.getCashSum());
             cashTextField.setText(orderDto.getCashSum().toString());
             cashTextField.setDisable(false);
             plasticTextField.setText(orderDto.getPcardSuma().toString());
             plasticTextField.setDisable(false);
             setPaymentTypeBoth();
             paidTotalSumm.setText(orderDto.getTotalAmount().toString());
            differenceChange(new BigDecimal(totalProductSumm.getText()));
      }
       calcTotalLabel();


    }

    private void setPaymentTypeBoth() {
        cashTextField.setVisible(true);
        cashLabel.setVisible(true);
        cashImage.setVisible(true);
        plasticTextField.setVisible(true);
        plasticLabel.setVisible(true);
        plasticImage.setVisible(true);
        cashTextField.setStyle("-fx-pref-width: 160");
        plasticTextField.setStyle("-fx-pref-width: 160");
        plasticTextField.setLayoutX(314);
        plasticTextField.setLayoutY(124);
        plasticImage.setLayoutX(285);
        plasticImage.setLayoutY(129);
        plasticLabel.setLayoutX(314);
        plasticLabel.setLayoutY(107);
    }

    private void setPaymentTypePlastic() {
        //modify Cash
        cashTextField.setVisible(false);
        cashImage.setVisible(false);
        cashLabel.setVisible(false);
        cashTextField.setText("0");
        //modify and locate plasticTextField
        plasticTextField.setDisable(false);
        plasticImage.setVisible(true);
        plasticLabel.setVisible(true);
        plasticTextField.setVisible(true);
        plasticImage.setLayoutX(42);
        plasticImage.setLayoutY(129);
        plasticLabel.setLayoutX(70);
        plasticLabel.setLayoutY(107);
        plasticTextField.setLayoutX(70);
        plasticTextField.setLayoutY(124);
        plasticTextField.setStyle("-fx-pref-width: 405");

    }

    private void setPaymentTypeCash() {
        plasticImage.setVisible(false);
        plasticLabel.setVisible(false);
        plasticTextField.setText("0");
        plasticTextField.setDisable(true);
        plasticTextField.setVisible(false);
        cashLabel.setVisible(true);
        cashImage.setVisible(true);
        cashTextField.setStyle("-fx-pref-width: 405");
        cashTextField.setVisible(true);
        cashTextField.setDisable(false);
        cashTextField.setLayoutX(70);
        cashTextField.setLayoutY(124);
    }

    private void addToTextFieldHandler() {

        cashTextField.setOnKeyPressed(event -> {
            if(!event.getCode().equals(KeyCode.BACK_SPACE)
                    && !event.getCode().equals(KeyCode.DELETE)
                    && !event.getCode().equals(KeyCode.HOME)
                    && !event.getCode().equals(KeyCode.END)
                    && !event.getCode().isDigitKey()
                    && !event.getCode().isArrowKey()) {
                if(cashTextField.getText().length()!=0) {
                    cashTextField.setText(cashTextField.getText(0, cashTextField.getLength() - 1));
                }
            } else {
                if(!"".equals(cashTextField.getText().trim())) {
                    orderDto.setCashSum(new BigDecimal(cashTextField.getText()));
                } else {
                    orderDto.setCashSum(BigDecimal.ZERO);
                }
                calcTotalLabel();
            }
        });

        plasticTextField.setOnKeyPressed(event -> {
            if(!event.getCode().equals(KeyCode.BACK_SPACE)
                    && !event.getCode().equals(KeyCode.DELETE)
                    && !event.getCode().equals(KeyCode.HOME)
                    && !event.getCode().equals(KeyCode.END)
                    && !event.getCode().isDigitKey()
                    && !event.getCode().isArrowKey()) {
                if(plasticTextField.getText().length()!=0) {
                    plasticTextField.setText(plasticTextField.getText(0, plasticTextField.getLength() - 1));
                }
            } else {
                if(!"".equals(plasticTextField.getText().trim())) {
                    orderDto.setPcardSuma(new BigDecimal(plasticTextField.getText()));
                } else {
                    orderDto.setPcardSuma(BigDecimal.ZERO);
                }
                calcTotalLabel();
            }
        });
    }

    private void calcTotalLabel()
    {
            cashTextField.textProperty().addListener((observable, oldValue, newValue) ->
            {
                BigDecimal answer = new BigDecimal(cashTextField.getText());
                if(plasticTextField.isVisible()){
                    answer = answer.add(new BigDecimal(plasticTextField.getText()));
                }
                  paidTotalSumm.setText(answer.toString());
                 differenceChange(answer);
            });
            plasticTextField.textProperty().addListener((observable, oldValue, newValue) ->
            {
                BigDecimal answer = new BigDecimal(plasticTextField.getText());
                if(cashTextField.isVisible()){
                    answer = answer.add(new BigDecimal(cashTextField.getText()));
                }
                paidTotalSumm.setText(answer.toString());
                differenceChange(answer);
            });




    }

    private void differenceChange(BigDecimal answer)
    {
        answer = answer.subtract(new BigDecimal(totalProductSumm.getText()));
        change.setText(answer.toString());
        if(answer.compareTo(BigDecimal.ZERO) >= 0){
            change.setStyle("-fx-text-fill: green");
        }
        else{
            change.setStyle("-fx-text-fill: red");
        }
    }

    private void calculateTotal(String newValue) {
            BigDecimal bigDecimal = new BigDecimal(newValue);
            BigDecimal bigDecimal1 = BigDecimal.ZERO;
            System.out.println(plasticTextField.isVisible());
            bigDecimal1 = new BigDecimal(plasticTextField.getText());
            bigDecimal = bigDecimal.add(bigDecimal1);

    }

    private void changeSliderValue(Number newValue)
    {
        BigDecimal totalSumm = orderDto.getTotalAmount();
        BigDecimal cash = new BigDecimal(newValue.doubleValue());
        BigDecimal plastic = totalSumm.subtract(cash);
        cashTextField.setText(cash.toString());
        plasticTextField.setText(plastic.toString());

    }
   public void setOrderDto(OrderDto orderDto) {
        this.orderDto = orderDto;
        changeOrderPaymentSum();
    }

    public void setListProducts(List<CartTable> productsToSave){

        this.productsToSave = productsToSave;
        List<TransactionDto> list = new ArrayList<>();
        final BigDecimal[] totalsumm = {BigDecimal.ZERO};
        productsToSave.forEach(cartTable ->
        {
            TransactionDto transactionDto = new TransactionDto();
            transactionDto.setProductTotalSellPrice(cartTable.getTotalSumm());
            totalsumm[0] = totalsumm[0].add(cartTable.getTotalSumm());
            transactionDto.setProductId(cartTable.getProductId());
            transactionDto.setProductCount(cartTable.getCountP());
            transactionDto.setProductName(cartTable.getNameP());
            transactionDto.setDimension(cartTable.getTypeDimension());
            transactionDto.setProductPrice(cartTable.getSellPrice());
            list.add(transactionDto);
        });
        orderDto.setList(list);
        orderDto.setTotalAmount(totalsumm[0]);
        System.out.println(orderDto);
        cashRadioBtn.setSelected(true);
        setPaymentTypeCash();
        cashTextField.setText(totalsumm[0].toString());
        totalProductSumm.setText(totalsumm[0].toString());
        paidTotalSumm.setText(totalsumm[0].toString());
    }




}
