package uz.dukon.controllers.application.product.content;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import uz.dukon.App;
import uz.dukon.controllers.application.widgets.WPopup;
import uz.dukon.controllers.model.ProductDtoList;
import uz.dukon.service.ProductService;
import uz.dukon.utils.FxmlUrl;
import uz.dukon.utils.HelpfullUtils;

import java.math.BigDecimal;
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
    private TableColumn<ProductDtoList,BigDecimal> productPriceC;

    private List<ProductDtoList> productDtoListList;


    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        productDtoListList = new ArrayList<>();
        prepare();
        events();
        App.ctx.getBean(ProductService.class).getProductsForContent(productDtoListList);
        fillTable();




    }

    private void fillTable() {

    }

    private void events() {
        addProduct.setOnAction(event -> {
            //open new Add window for new product
            new WPopup(FxmlUrl.Product.productModal,"Янги махсулот кўшиш").show();
        });


    }

    private void prepare() {
        numC.setCellValueFactory(new PropertyValueFactory<ProductDtoList, Long>("id"));
        productNameC.setCellValueFactory(new PropertyValueFactory<ProductDtoList, String>("productName"));
        productDimenstionC.setCellValueFactory(new PropertyValueFactory<ProductDtoList, String>("dimension"));
        productPriceC.setCellValueFactory(new PropertyValueFactory<ProductDtoList, BigDecimal>("sellPrice"));
        productImageC.setCellValueFactory(new PropertyValueFactory<ProductDtoList, ImageView>("image"));
        HelpfullUtils.setCenterText(productNameC);
        HelpfullUtils.setCenterText(productDimenstionC);
        HelpfullUtils.setCenterGraphic(productImageC);
        HelpfullUtils.setCenterBigDecimal(productPriceC);
    }
}
