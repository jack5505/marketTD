package uz.dukon.controllers.application.product.content;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import uz.dukon.App;
import uz.dukon.controllers.application.product.events.AddProductEvent;
import uz.dukon.controllers.application.product.events.DeleteProductEvent;
import uz.dukon.controllers.application.widgets.WPopup;
import uz.dukon.controllers.model.TypeDto;
import uz.dukon.controllers.newmodel.CartTable;
import uz.dukon.service.ProductService;
import uz.dukon.utils.FxmlUrl;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Jack on 25.02.2019.
 */
public class DeletProductsController implements Initializable {
    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnCancel;

    @FXML
    private JFXComboBox<CartTable> productList;

    @FXML
    private JFXComboBox<TypeDto> typeList;

    @FXML
    private Label info;


    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        info.setText("");
        eventlar();
        updateProduct();
        updateType();
    }

    private void eventlar()
    {
            //Parolni to`g`ri yozgandan keyingi ishliydigan event
            App.eventBus.addEventHandler(DeleteProductEvent.ANY, event -> {
                App.ctx.getBean(ProductService.class).deleteType(event.getProductId());
                info.setText("Категория Ўчирилди!");
                updateType();
            });

            btnAdd.setOnAction(event ->
            {
                if(!productList.getSelectionModel().isEmpty()){
                    App.ctx.getBean(ProductService.class).deletProduct(productList.getSelectionModel().getSelectedItem().getProductId());
                    info.setText("Товар Ўчирилди!");
//                  deleted(event);
                    updateProduct();
                }
                if(!typeList.getSelectionModel().isEmpty())
                {
                    //Ask password to give permission category
                 WPopup wPopup = new WPopup(FxmlUrl.Product.askDeletedView,"");
                 AskDeletedController askDeletedController = wPopup.getController();
                 askDeletedController.deleteType(typeList.getSelectionModel().getSelectedItem().getTypeId());
                 wPopup.show();
                }

            });
            btnCancel.setOnAction(event ->
            {
                deleted(event);
                Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
                stage.close();
            });
    }

    private void updateType() {
            if(!typeList.getItems().isEmpty())
                typeList.getItems().clear();
         typeList.getItems().addAll(App.ctx.getBean(ProductService.class).getAllType());
    }

    private void updateProduct() {
        if(!productList.getItems().isEmpty())
              productList.getItems().clear();
        productList.getItems().addAll(App.ctx.getBean(ProductService.class).getProducts());
    }

    private void deleted(ActionEvent event)
    {
        AddProductEvent addProductEvent = new AddProductEvent(AddProductEvent.ANY);
        App.eventBus.fireEvent(addProductEvent);
        Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        stage.close();
    }


}
