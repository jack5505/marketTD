package uz.dukon.controllers.application.product.content;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import uz.dukon.App;
import uz.dukon.controllers.application.product.events.AddProductEvent;
import uz.dukon.controllers.application.widgets.WPopup;
import uz.dukon.controllers.model.ProductDtoList;
import uz.dukon.service.ProductService;
import uz.dukon.utils.FxmlUrl;
import uz.dukon.utils.HelpfullUtils;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Jack on 10.03.2019.
 */
public class ProductContentController implements Initializable
{
    @FXML
    private JFXButton addProduct;

    @FXML
    private JFXButton deletProduct;

    @FXML
    private JFXButton editProduct;

    @FXML
    private JFXButton exit;

    @FXML
    private TableView<ProductDtoList> tableView;

    @FXML
    private TableColumn<ProductDtoList,Long> numC;

    @FXML
    private TableColumn<ProductDtoList,ImageView> productImageC;

    @FXML
    private TableColumn<ProductDtoList,String> productNameC;

    @FXML
    private TableColumn<ProductDtoList,String> productDimenstionC;

    @FXML
    private TableColumn<ProductDtoList,Long> productPriceC;

    private List<ProductDtoList> productDtoListList;


    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        productDtoListList = new ArrayList<>();
        prepare();
        events();
        App.ctx.getBean(ProductService.class).getProductsForContent(productDtoListList);
        fillTable();
        deletProduct.setDisable(true);
        editProduct.setDisable(true);




    }

    private void fillTable()
    {
        productDtoListList.forEach(productDtoList -> {
            System.out.println(productDtoList.getPathImage());
            Image image = new Image("file:///"+productDtoList.getPathImage());
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(60);
            imageView.setFitWidth(60);
            productDtoList.setImage(imageView);
        });
        tableView.getItems().addAll(productDtoListList);
    }

    private void events() {
        addProduct.setOnAction(event -> {
            //open new Add window for new product
            new WPopup(FxmlUrl.Product.productModal,"Янги махсулот кўшиш").show();
        });

        //After adding some products
        App.eventBus.addEventHandler(AddProductEvent.ANY,event ->
        {
            tableView.getItems().clear();
            productDtoListList.clear();
            App.ctx.getBean(ProductService.class).getProductsForContent(productDtoListList);
            fillTable();
        });

        //o`chirish
        deletProduct.setOnAction(event -> {

        });
        //o`zgartirish
        editProduct.setOnAction(event -> {
            if(!tableView.getSelectionModel().isEmpty()){
                System.out.println(tableView.getSelectionModel().getSelectedItem().getProductName());
                ProductDtoList productDtoList = tableView.getSelectionModel().getSelectedItem();
                System.out.println(productDtoList.getProductId());
                WPopup wPopup = new WPopup(FxmlUrl.Product.productModal,"Махсулотни Ўзгартириш");
                AddProductModal addProductModal = wPopup.getController();
                addProductModal.setEditProduct(productDtoList);
                wPopup.show();
            }
        });

        exit.setOnAction(event ->
        {
            AddProductEvent addProductEvent = new AddProductEvent(AddProductEvent.ANY);
            App.eventBus.fireEvent(addProductEvent);
            Stage stage = (Stage)((Button)(event).getSource()).getScene().getWindow();
            stage.close();
        });
        tableView.setOnMouseClicked(event -> {
            deletProduct.setDisable(false);
            editProduct.setDisable(false);
        });

    }

    private void prepare() {
        numC.setCellValueFactory(new PropertyValueFactory<ProductDtoList, Long>("id"));
        productNameC.setCellValueFactory(new PropertyValueFactory<ProductDtoList, String>("productName"));
        productDimenstionC.setCellValueFactory(new PropertyValueFactory<ProductDtoList, String>("dimension"));
        productPriceC.setCellValueFactory(new PropertyValueFactory<ProductDtoList, Long>("sellPrice"));
        productImageC.setCellValueFactory(new PropertyValueFactory<ProductDtoList, ImageView>("image"));
        HelpfullUtils.setCenterText(productNameC);
        HelpfullUtils.setCenterText(productDimenstionC);
        HelpfullUtils.setCenterGraphic(productImageC);
        HelpfullUtils.setCenterLong(productPriceC);
        HelpfullUtils.setCenterLong(numC);

    }
}
