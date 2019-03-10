package uz.dukon.controllers.application.product.content;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Jack on 09.02.2019.
 */
public class ExitController implements Initializable
{
    @FXML
    private JFXButton yes;

    @FXML
    private JFXButton no;


    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        yes.setOnAction(event -> {
            System.exit(0);
        });
        no.setOnAction(event -> {
            Stage stage = (Stage) ((JFXButton)(event.getSource())).getScene().getWindow();
            stage.close();
        });


    }
}
