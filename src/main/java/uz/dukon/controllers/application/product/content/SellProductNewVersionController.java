package uz.dukon.controllers.application.product.content;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import uz.dukon.App;
import uz.dukon.controllers.application.product.events.AddProductEvent;
import uz.dukon.controllers.application.widgets.WPopup;
import uz.dukon.utils.FxmlUrl;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Jack on 18.02.2019.
 */
public class SellProductNewVersionController implements Initializable {


    @FXML
    private JFXButton addProduct;

    @FXML
    private JFXButton report;

    @FXML
    private JFXButton deletProduct;

    @FXML
    private AnchorPane main1;
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/uz/dukon/application/product/content/NewSalePart.fxml"));
            if(main1.getChildren() != null && main1.getChildren().size() > 0)
                main1.getChildren().clear();
            main1.getChildren().add(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        eventsButton();

    }

    private void eventsButton() {
        //Add button will open to us ProductModal
        addProduct.setOnAction(event ->
        {
               WPopup wPopup =  new WPopup(FxmlUrl.Product.productsView,"Товар");
               wPopup.getStage().setOnCloseRequest(event1 -> {

                    AddProductEvent addProductEvent = new AddProductEvent(AddProductEvent.ANY);
                    App.eventBus.fireEvent(addProductEvent);

               });
               wPopup.setMaximized();
               wPopup.show();
        });

        report.setOnAction(event ->
        {
            new WPopup(FxmlUrl.Product.reports,"").show();
        });
        //delete product modal
        deletProduct.setOnAction(event -> {
            WPopup wPopup = new WPopup(FxmlUrl.Product.delete,"");
            Stage stage = wPopup.getStage();
            stage.setOnCloseRequest(event1 -> {
                AddProductEvent addProductEvent = new AddProductEvent(AddProductEvent.ANY);
                App.eventBus.fireEvent(addProductEvent);
            });
            wPopup.show();
        });
    }
}
