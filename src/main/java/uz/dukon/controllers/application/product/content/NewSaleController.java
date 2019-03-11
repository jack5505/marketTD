package uz.dukon.controllers.application.product.content;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.StageStyle;
import uz.dukon.App;
import uz.dukon.controllers.application.product.events.AddCartEvent;
import uz.dukon.controllers.application.product.events.AddProductEvent;
import uz.dukon.controllers.application.product.events.StartAgainEvent;
import uz.dukon.controllers.application.product.print.Print;
import uz.dukon.controllers.application.product.print.PrintModel;
import uz.dukon.controllers.application.widgets.WPopup;
import uz.dukon.controllers.model.TypeDto;
import uz.dukon.controllers.newmodel.CartTable;
import uz.dukon.service.ProductService;
import uz.dukon.utils.FxmlUrl;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Jack on 18.02.2019.
 */
public class NewSaleController implements Initializable
{
    /*
    TODO agar bir produqtni boshinda dona atib girizib keyin shu produqtni metir atib girizajak bo`lsa nichik bo`ladi
    shuni o`ylanib go`rish garak
     */

    @FXML
    private TabPane menuPane;

    @FXML
    private JFXButton btnSell;

    @FXML
    private JFXButton printCheck;

    @FXML
    private TableView<CartTable> cardTable;

    @FXML
    private TableColumn<CartTable,BigDecimal> countP;

    @FXML
    private TableColumn<CartTable,String> nameP;

    @FXML
    private TableColumn<CartTable,Long> sellPrice;

    @FXML
    private TableColumn<CartTable,BigDecimal> totalSumm;

    @FXML
    private TableColumn<CartTable,HBox> operations;

    @FXML
    private Label totalSumma;

    private static  int orderedId;
    //sold products calculation for locate cart items
    private List<CartTable> soldItems;

    //bunda butun savdoni puli saqlanadi
    private BigDecimal totalSellPrice;

    //bu bazada bor hamma danniylar buttona set atish uchun garak
    private List<CartTable> productList;

    private List<TypeDto> typeList;

    private HashMap<Long,List<CartTable>> createMenu;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        createMenu = new HashMap<Long,List<CartTable>>();
        totalSumma.setText("0");
        soldItems = new ArrayList<>();
        totalSellPrice  = BigDecimal.ZERO;
        productsGetFromDB();
        prepareTableToStartWork();
        events();

    }

    private void events()
    {
        /*
        When add new good for cart
        this event comes from ItemInfoController
         */
        App.eventBus.addEventHandler(AddCartEvent.ANY,event ->
        {
            if(!event.getCartTable().getChange())
            {
            //tablitsaga yozilib duribdi
            cardTable.getItems().add(event.getCartTable());
            //biza danniy atib yuvaradon listga yozilib duribdi
            soldItems.add(event.getCartTable());
            calcTotal();
            }
            else
            {
                cardTable.getItems().forEach(cartTable -> {
                    if(cartTable.getOrderedId() == event.getCartTable().getOrderedId()){
                        cartTable = event.getCartTable();
                    }
                });
                calcTotal();
            }
        });

        //this is after saling products into db it clear cardTable and hashmap
        App.eventBus.addEventHandler(StartAgainEvent.ANY,event ->
        {
            cardTable.getItems().clear();
            soldItems.clear();
            totalSumma.setText("0");
            totalSellPrice = new BigDecimal("0");
        });

        //When we try to sell all products which located in cartTabel
        btnSell.setOnAction(event ->
        {

                WPopup wPopup  = new WPopup(FxmlUrl.Product.sellConfirm,"");
                OrderConfirmController orderConfirmController = wPopup.getController();
                orderConfirmController.setListProducts(soldItems);
                wPopup.show();


        });
        //When click print check button
        printCheck.setOnAction(event ->
        {

            List<PrintModel> printModels = new ArrayList<>();
            cardTable.getItems().forEach(data ->
            {
                PrintModel printModel = new PrintModel();
                printModel.setProductName(data.getNameP());
                printModel.setDimension(data.getTypeDimension());
                printModel.setProductCount(data.getCountP());
                printModel.setChange(new BigDecimal("0"));
                printModel.setPaidSumma(new BigDecimal("0"));
                printModel.setTotalPrice(new BigDecimal(totalSumma.getText()));
                printModel.setPrice(data.getSellPrice());
                printModel.setTotal(data.getTotalSumm());
                printModels.add(printModel);
            });
            Print print = new Print(printModels);

        });
        //When added new product into DB
        App.eventBus.addEventHandler(AddProductEvent.ANY,event ->
        {
            productList = new ArrayList<>();
            createMenu = new HashMap<Long,List<CartTable>>();
            menuPane.getTabs().clear();
            productsGetFromDB();
        });

    }

    //produqtlar joylashtirib chiqiladi
    private void productsGetFromDB()
    {
        int data = App.ctx.getBean(ProductService.class).productCount();
        productList = App.ctx.getBean(ProductService.class).getProducts();
        typeList = App.ctx.getBean(ProductService.class).getAllType();

//        generateMenus(data);
          fillWithData();
          generateMenusNew();
    }


    //declare value of TableView
    private void prepareTableToStartWork() {
        countP.setCellValueFactory(new PropertyValueFactory<CartTable, BigDecimal>("countP"));
        nameP.setCellValueFactory(new PropertyValueFactory<CartTable, String>("nameP"));
        sellPrice.setCellValueFactory(new PropertyValueFactory<CartTable, Long>("sellPrice"));
        totalSumm.setCellValueFactory(new PropertyValueFactory<CartTable, BigDecimal>("totalSumm"));
        operations.setCellValueFactory(new PropertyValueFactory<CartTable, HBox>("boxes"));
    }

    //berda butun buttonla generatsiya bo`ladi
    //old version of generateMenus
    private void generateMenus(int data)
    {

        int m = 16 * 7;
        int tabs = (data)/ m + (data % m == 0 ? 0 : 1);
        data --;
            for(int i = 1 ; i <= tabs; i ++)
            {
                Tab tab = new Tab();
                tab.setText("Янги Товарлар " + i);
                tab.setStyle("-fx-font-size:18px");
                Node first = tab.getContent();
                AnchorPane anchorPane = new AnchorPane();
                anchorPane.setStyle("-fx-background-color: red");
                VBox vBox = new VBox();
                            anchorPane.setTopAnchor(vBox,0.);
                            anchorPane.setLeftAnchor(vBox,0.);
                            anchorPane.setRightAnchor(vBox,0.);
                            anchorPane.setBottomAnchor(vBox,0.0);
                for(int j = 1 ; j <= 16 && data >= 0 ;j ++)
                {
                    HBox hBox = new HBox();
                    for(int k = 1 ; k <= 7 && data >= 0 ; k ++)
                    {
                        Button button = new Button();
                        button.setId(productList.get(data).getProductId().toString());
                        button.setText(productList.get(data).getNameP());
                        button.setFont(new Font(15));
                        button.setPrefSize(220,50);
                        button.setOnAction(this::openClickedButtonMenu);
                        hBox.getChildren().addAll(button);
                        data --;
                    }
                    vBox.getChildren().add(hBox);

                }
                anchorPane.getChildren().add(vBox);
                tab.setContent(anchorPane);
                menuPane.getTabs().add(tab);


            }






    }

    //berda yangi buttun buttonla tablani generatsiya metodi
    private void generateMenusNew()
    {

            typeList.forEach(typeDto ->
            {
                int data = 0;
                if(createMenu.containsKey(typeDto.getTypeId()))
                    data = createMenu.get(typeDto.getTypeId()).size();
                int rp = (data / (49));
                rp += data % (49) == 0 ? 0 : 1;
                for(int k = 1; k <= rp ; k ++)
                {
                    Tab tab = null;

                    if(k == 1)
            tab  = new Tab(typeDto.getTypeName());
                    else
                        tab = new Tab(typeDto.getTypeName() + k);
            tab.setId(""+typeDto.getTypeId());
            menuPane.setTabMaxHeight(38);
            AnchorPane anchorPane = new AnchorPane();
            anchorPane.setStyle("-fx-background-color: rgb(232,232,232)");
            tab.setStyle("-fx-font-size:26px");
            VBox hBox = new VBox();
            anchorPane.setTopAnchor(hBox,0.);
            anchorPane.setLeftAnchor(hBox,0.);
            anchorPane.setRightAnchor(hBox,0.);
            anchorPane.setBottomAnchor(hBox,0.0);
            data --;
            for(int i = 1 ; i <= 7 && data >= 0 ; i ++)
            {
                HBox vBox = new HBox();
                for(int j = 1 ; j <= 7 && data >= 0 ; j ++)
                {
                    Button button = new Button();
                    button.setId(""+createMenu.get(typeDto.getTypeId()).get(data).getProductId());
                    button.setText(createMenu.get(typeDto.getTypeId()).get(data).getNameP());
                    String s = "C:/images/images1.jpg";
                    String temp = "file:///";
                    temp+=s;
//                    button.setStyle("-fx-background-image: url("+temp+")");
                    Image image = new Image(temp);
                    ImageView imageView = new ImageView(image);
                    imageView.setFitWidth(60);
                    imageView.setFitHeight(60);
                    button.setGraphic(imageView);
                    button.setContentDisplay(ContentDisplay.TOP);
                    button.setPrefSize(160,80);
                    button.setOnAction(this::openClickedButtonMenu);
                    vBox.getChildren().addAll(button);
                    data --;
                }
                hBox.getChildren().add(vBox);
            }
            anchorPane.getChildren().add(hBox);
            tab.setContent(anchorPane);
            menuPane.getTabs().add(tab);
                }
            });

    }
    //Har bir buttoni bosgandaki holat menyudan durib
    private void openClickedButtonMenu(ActionEvent actionEvent)
    {
        orderedId ++;
        Button button = ((Button) actionEvent.getSource());
        WPopup wPopup = new WPopup(FxmlUrl.Product.count,"");
        wPopup.setStageStyle(StageStyle.UNDECORATED);
        CountController countController = wPopup.getController();
        Long productId = Long.parseLong(button.getId());
        System.out.println(productId);
        CartTable cartTable = new CartTable();
        for (CartTable table : productList) {
            if(table.getProductId().equals(productId)){
                cartTable = table;
                break;
            }
        }
        Button deleted = new Button("",new ImageView(
                new Image(getClass().getResourceAsStream("/images/delete1.png"))));
        deleted.setStyle("-fx-padding: 5");
        deleted.setId(orderedId+"");
        Button edit = new Button("",new ImageView(
                new Image(getClass().getResourceAsStream("/images/edit1.png"))));
        edit.setStyle("-fx-padding: 5");
        edit.setId(orderedId+"");
        //produqtni berdan o`chiramiz
        deleted.setOnAction(event ->
        {
            int id = Integer.parseInt(((Button)event.getSource()).getId());
            final CartTable[] cartTable1 = {new CartTable()};
            cardTable.getItems().forEach(cartTable2 -> {
                if(cartTable2.getOrderedId() == id)
                {
                    cartTable1[0] = cartTable2;
                }
            });
            cardTable.getItems().remove(cartTable1[0]);
            calcTotal();
        });
        edit.setOnAction(event -> {
            int id = Integer.parseInt(((Button)event.getSource()).getId());
            final CartTable[] cartTable1 = {new CartTable()};
            cardTable.getItems().forEach(cartTable2 -> {
                if(cartTable2.getOrderedId() == id)
                {
                    cartTable1[0] = cartTable2;
                }
            });
            System.out.println(cartTable1[0]);
            WPopup wPopup1 = new WPopup(FxmlUrl.Product.count,"");
            CountController countController1 = wPopup1.getController();
            countController.prepareToAdd(cartTable1[0]);
            wPopup1.show();
        });
        HBox hBox = new HBox();
        hBox.getChildren().add(deleted);
        hBox.getChildren().add(edit);
        cartTable.setBoxes(hBox);
        cartTable.setOrderedId(orderedId);
        countController.prepareToAdd(cartTable);
        wPopup.show();

    }


    private void fillWithData()
    {
        //berda barilgan tovarlarni tab menyu bo`yicha joylab chiqamiz
        productList.forEach(cartTable ->
        {
          if(!createMenu.containsKey(cartTable.getTypeId())){
              createMenu.put(cartTable.getTypeId(),new ArrayList<CartTable>());
          }
          createMenu.get(cartTable.getTypeId()).add(cartTable);
        });
    }
    //total summa hisoblanadi bu yerda
    private void calcTotal()
    {
        //bu har doim o`zgaradi akan edit atgandan keyin
        totalSellPrice = new BigDecimal("0");
        cardTable.getItems().forEach(cartTable ->
        {
            totalSellPrice = totalSellPrice.add(cartTable.getTotalSumm());
        });
        totalSumma.setText(totalSellPrice.toString());
    }
}
