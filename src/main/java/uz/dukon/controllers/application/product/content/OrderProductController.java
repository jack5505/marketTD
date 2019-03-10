package uz.dukon.controllers.application.product.content;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import uz.dukon.App;
import uz.dukon.controllers.application.product.events.AddProductEvent;
import uz.dukon.controllers.application.product.events.DeleteProductEvent;
import uz.dukon.controllers.application.widgets.InputFilter;
import uz.dukon.controllers.application.widgets.WImageView;
import uz.dukon.controllers.application.widgets.WPopup;
import uz.dukon.controllers.model.*;
import uz.dukon.utils.FxmlUrl;
import uz.dukon.utils.HelpfullUtils;

import java.math.BigDecimal;
import java.net.URL;
import java.util.*;

/**
 * Created by Jack on 14.09.2018.
 */
public class OrderProductController implements Initializable
    {
        @FXML
        private JFXButton accept;

        @FXML
        private Label summ;

        @FXML
        private TableColumn<ProductTable,Long> number;

        @FXML
        private TableColumn<ProductTable,String> nameProduct;

        @FXML
        private TableColumn<ProductTable,Button> plusButton;

        @FXML
        private TableColumn<ProductTable,BigDecimal> quantityP;

        @FXML
        private TableColumn<ProductTable,Button> minusProduct;


        @FXML
        private TableColumn<ProductTable,Long> sellPrice;

        @FXML
        private TableColumn<ProductTable,Button> deleteP;

        @FXML
        private TableColumn<ProductTable,BigDecimal> totalSumm;

        @FXML
        private TableView<ProductTable> purchaseTableView;

        @FXML
        private TableView<ProductList>tableProductSell;

        @FXML
        private TableColumn<ProductList,Long> numberP;

        @FXML
        private TableColumn<ProductList,String> productNameP;

        @FXML
        private TableColumn<ProductList,String> productType;

        @FXML
        private TableColumn<ProductList,String> productDimension;

        @FXML
        private Label productMessage;

        @FXML
        private Label comeMessage;

        @FXML
        private Label quantityMessage;

        @FXML
        private Label sellMessage;


        @FXML
        private JFXButton addProductToPurchaseTable;

        @FXML
        private JFXButton report;

        @FXML
        private JFXButton addProduct;

        @FXML
        private JFXButton exit;

        @FXML
        private JFXButton mnm;

        @FXML
        private JFXComboBox<String> product;

        private FilteredList<String> filteredList;

        private List<ProductCombo> productCombos;

        private HashMap<String,Long> checkName;

        @FXML
        private JFXTextField purchaseCost;

        @FXML
        private JFXTextField sellCost;

        @FXML
        private JFXComboBox<String> typeSelect;

        @FXML
        private JFXTextField quantity;

        private Map<Long,ProductTable> purchaseTableViewDataMap;

        private BigDecimal totalLastSumma;

        @Override
        public void initialize(URL location, ResourceBundle resources)
        {
            purchaseTableViewDataMap = new LinkedHashMap<>();
            product.setEditable(true);
            registerEvents();
            fillWithData();
            prepareTables();




        }

        private void prepareTables()
        {
            //Prepare tables for receving products data
            number.setCellValueFactory(new PropertyValueFactory<ProductTable, Long>("id"));
            HelpfullUtils.setCenterLong(number);
            nameProduct.setCellValueFactory(new PropertyValueFactory<ProductTable, String>("productName"));
            HelpfullUtils.setCenterText(nameProduct);
            plusButton.setCellValueFactory(new PropertyValueFactory<ProductTable, Button>("plus"));
            HelpfullUtils.setCenterGraphic(plusButton);
            quantityP.setCellValueFactory(new PropertyValueFactory<ProductTable, BigDecimal>("count"));
            HelpfullUtils.setCenterBigDecimal(quantityP);
            minusProduct.setCellValueFactory(new PropertyValueFactory<ProductTable, Button>("minus"));
            HelpfullUtils.setCenterGraphic(minusProduct);
            sellPrice.setCellValueFactory(new PropertyValueFactory<ProductTable, Long>("sellPrice"));
            HelpfullUtils.setCenterLong(sellPrice);
            totalSumm.setCellValueFactory(new PropertyValueFactory<ProductTable, BigDecimal>("totalSellPrice"));
            HelpfullUtils.setCenterBigDecimal(totalSumm);
            deleteP.setCellValueFactory(new PropertyValueFactory<ProductTable, Button>("delete"));
            HelpfullUtils.setCenterGraphic(deleteP);

            //Prepare table for products list
            numberP.setCellValueFactory(new PropertyValueFactory<ProductList, Long>("id"));
            productNameP.setCellValueFactory(new PropertyValueFactory<ProductList, String>("productName"));
            productType.setCellValueFactory(new PropertyValueFactory<ProductList, String>("type"));
            productDimension.setCellValueFactory(new PropertyValueFactory<ProductList, String>("dimension"));

            for(int i = 1 ; i <= 35; i ++){
                ProductList productList = new ProductList();
                productList.setId((long)i);
                productList.setProductName("Salom");
                productList.setDimension("KG");
                tableProductSell.getItems().addAll(productList);
            }
        }
        //All data will fill here
        private void fillWithData()
        {
          checkName = new HashMap<>();
//          productCombos = App.ctx.getBean(ProductService.class).getProducts();
          List<String> forSearch = new ArrayList<>();
          productCombos.forEach(productCombo -> {
              forSearch.add(productCombo.getName());
              checkName.put(productCombo.getName(),productCombo.getProductId());
          });
          ObservableList<String> fff = FXCollections.observableArrayList(forSearch);
          filteredList = new FilteredList<String>(fff);
          product.getEditor().textProperty().addListener(new InputFilter(product,filteredList,false));
          product.setItems(filteredList);
          product.getSelectionModel().selectLast();
        }
        // All events registered here
        //TODO Hamma eventlar shorda
        private void registerEvents()
        {
            accept.setOnAction(event ->
            {
                List<TransactionDto> list = new ArrayList<>();
                purchaseTableViewDataMap.forEach((aLong, productTable) -> {
                    TransactionDto transactionDto = new TransactionDto();
                    transactionDto.setProductId(aLong);
                    transactionDto.setProductCount(productTable.getCount());
                    transactionDto.setProductPrice(productTable.getSellPrice());
                    BigDecimal bigDecimal = new BigDecimal(productTable.getSellPrice());
                    bigDecimal = bigDecimal.multiply(productTable.getCount());
                    transactionDto.setProductTotalSellPrice(bigDecimal);
                    list.add(transactionDto);
                });
                OrderDto orderDto = new OrderDto();
                orderDto.setList(list);
                orderDto.setTotalAmount(totalLastSumma);
                orderDto.setCashSum(totalLastSumma);
                WPopup wPopup = new WPopup(FxmlUrl.Product.sellConfirm,"");
                OrderConfirmController orderConfirmController = wPopup.getController();
                orderConfirmController.setOrderDto(orderDto);
                wPopup.show();

            });
            addProduct.setOnAction(event ->
            {
                new WPopup(FxmlUrl.Product.productModal,"Товар кўшиш").show();
            });
            mnm.setOnAction(event -> {
                Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
                stage.setIconified(true);
            });
            exit.setOnAction(event -> {
                WPopup wPopup = new WPopup(FxmlUrl.Product.exitWindow,"");
                wPopup.show();
            });
            product.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if(checkName.containsKey(newValue))
                {
                    productCombos.forEach(productCombo -> {
                        if(productCombo.getName().equals(newValue)){
                            purchaseCost.setText(productCombo.getGetProduct().toString());
                            sellCost.setText(productCombo.getSellProduct().toString());
                        }
                    });
                }
            });
            addProductToPurchaseTable.setOnAction(event ->
            {
                comeMessage.setVisible(purchaseCost.getText().isEmpty());
                sellMessage.setVisible(sellCost.getText().isEmpty());
                quantityMessage.setVisible(quantity.getText().isEmpty());
                productMessage.setVisible(product.getSelectionModel().isEmpty());
                if(!(comeMessage.isVisible()
                        && sellMessage.isVisible()
                        && quantityMessage.isVisible()
                        && productMessage.isVisible()))
                {
                    //If all data with filled then we start to add data into TableView
                    ProductTable productTable = new ProductTable();
                    productTable.setId((long) (purchaseTableView.getItems().size() + 1));
                    productTable.setCount(new BigDecimal(quantity.getText()));
                    productTable.setProductName(product.getSelectionModel().getSelectedItem());
                    productTable.setProductId(checkName.get(product.getSelectionModel().getSelectedItem()));
                    productTable.setGetPrice(Long.parseLong(purchaseCost.getText()));
                    productTable.setSellPrice(Long.parseLong(sellCost.getText()));
                    BigDecimal mult = new BigDecimal(sellCost.getText());
                    mult = mult.multiply(new BigDecimal(quantity.getText()));
                    productTable.setTotalSellPrice(mult);
                    purchaseTableViewDataMap.put(productTable.getProductId(),productTable);
                    WImageView minuseImage = new WImageView("/images/icons/menu/supplier/minus.png", 16, 16);
                    Button minusButton = new Button("", minuseImage);
                    minusButton.getStyleClass().add("w-button");
                    minusButton.setId(String.valueOf(productTable.getProductId()));
                    minusButton.setOnAction(eventHandler ->
                    {
                        try {
                            long productId = Long.parseLong(((Button) eventHandler.getSource()).getId());
                            if (purchaseTableViewDataMap.containsKey(productId)) {
                                ProductTable minusPurchase = purchaseTableViewDataMap.get(productId);
                                if (minusPurchase.getCount().compareTo(BigDecimal.ONE) >= 1) {
                                    minusPurchase.setCount(minusPurchase.getCount().subtract(BigDecimal.ONE));
                                    BigDecimal bigDecimal = new BigDecimal(minusPurchase.getSellPrice());
                                    bigDecimal = bigDecimal.multiply(minusPurchase.getCount());
                                    minusPurchase.setTotalSellPrice(bigDecimal);
                                    clearTableAndReDraw();
                                    calcTotalPurchaseSumm();
                                }
                            }
                        } catch (NumberFormatException e) {
                            //e.printStackTrace();
                        }

                    });
                    productTable.setMinus(minusButton);
                    WImageView plusImageView = new WImageView("/images/icons/menu/supplier/plus.png", 16, 16);
                    Button plusButton = new Button("", plusImageView);
                    plusButton.getStyleClass().add("w-button");
                    plusButton.setPrefHeight(16);
                    plusButton.setPrefWidth(16);
                    plusButton.setId(String.valueOf(productTable.getProductId()));
                    //TODO setOnAction should write here
                    plusButton.setOnAction(event1 ->
                    {
                        try {
                            long productId = Long.parseLong(((Button) event1.getSource()).getId());
                            if (purchaseTableViewDataMap.containsKey(productId)) {
                                ProductTable plusPurchase = purchaseTableViewDataMap.get(productId);
                                    plusPurchase.setCount(plusPurchase.getCount().add(BigDecimal.ONE));
                                    BigDecimal bigDecimal = new BigDecimal(plusPurchase.getSellPrice());
                                    bigDecimal = bigDecimal.multiply(plusPurchase.getCount());
                                    plusPurchase.setTotalSellPrice(bigDecimal);
                                    clearTableAndReDraw();
                                    calcTotalPurchaseSumm();

                            }
                        } catch (NumberFormatException e) {
                            //e.printStackTrace();
                        }


                    });
                    productTable.setPlus(plusButton);
                    WImageView deleteImage = new WImageView("/images/button/delete1.png", 16, 16);
                    Button deleteButton = new Button("", deleteImage);
                    deleteButton.getStyleClass().add("w-button");
                    deleteButton.setId(String.valueOf(productTable.getProductId()));
                    //TODO setOnAction should write here
                    deleteButton.setOnAction(this::openDeletePopup);
                    productTable.setDelete(deleteButton);
                    calcTotalPurchaseSumm();
                    clearTableAndReDraw();
                    summ.setText(totalLastSumma.toString());
                }

            });

            //Ochirishdan bosilgandan keyingi qilinadigan ish
            App.eventBus.addEventHandler(DeleteProductEvent.ANY,event -> {
                deleteAction(event.getProductId());
            });

            App.eventBus.addEventHandler(AddProductEvent.ANY,event ->
            {
                fillWithData();
            //    product.getSelectionModel().selectLast();
            });

        }

        private void deleteAction(Long productId) {
            if (purchaseTableViewDataMap.containsKey(productId)) {
                purchaseTableViewDataMap.remove(productId);
                clearTableAndReDraw();
                calcTotalPurchaseSumm();
            }
        }

        private void openDeletePopup(ActionEvent actionEvent) {
            Long productId = Long.parseLong(((Button) actionEvent.getSource()).getId());
            WPopup wPopup = new WPopup(FxmlUrl.Product.askAboutDelete,"");
            wPopup.setStageStyle(StageStyle.UNDECORATED);
            NotificationController controller = wPopup.getController();
            controller.setProductId(productId);
            wPopup.show();

        }

        private void calcTotalPurchaseSumm()
        {
            totalLastSumma = new BigDecimal("0");
            purchaseTableViewDataMap.values().forEach(supplierTableForReciev -> {
                totalLastSumma = totalLastSumma.add(supplierTableForReciev.getTotalSellPrice());
            });
            summ.setText(totalLastSumma.toString());
        }

        private void clearTableAndReDraw() {
            purchaseTableView.getItems().clear();
            purchaseTableView.setItems(FXCollections.observableArrayList(purchaseTableViewDataMap.values()));
        }
    }


