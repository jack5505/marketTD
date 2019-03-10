package uz.dukon.controllers.application.product.content;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import uz.dukon.App;
import uz.dukon.controllers.application.product.events.DeleteProductEvent;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Jack on 17.02.2019.
 */
public class NotificationController implements Initializable
{

    @FXML
    private JFXButton ok;

    @FXML
    private JFXButton cancel;

    private Long productId;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

        ok.setOnAction(event ->
        {
            System.out.println(productId);
            App.eventBus.fireEvent(new DeleteProductEvent(DeleteProductEvent.ANY,productId));
            Stage stage = (Stage) ((Button)(event).getSource()).getScene().getWindow();
            stage.close();

        });
        cancel.setOnAction(event ->
        {

            Stage stage = (Stage) ((Button)(event).getSource()).getScene().getWindow();
            stage.close();
        });


    }
    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
