package uz.dukon.controllers.application.product.content;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Jack on 17.02.2019.
 */
public class AddTypeController implements Initializable {
    @FXML
    private AnchorPane pane;

    @FXML
    private JFXButton btn;

    @FXML
    private JFXButton cancel;

    @FXML
    private JFXTextField field;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        events();


    }

    private void events() {
        cancel.setOnAction(event -> {
            Stage stage = (Stage)((Button)(event).getSource()).getScene().getWindow();
            stage.close();
        });
        btn.setOnAction(event ->
        {
            if(!field.getText().isEmpty()){
                saveIt();
                Stage stage = (Stage)((Button)(event).getSource()).getScene().getWindow();
                stage.close();
            }
        });
        pane.setOnKeyPressed(event -> {
            if(event.getCode().equals(KeyCode.ENTER))
            {
                if(!field.getText().isEmpty())
                {
                    saveIt();
                    Stage stage = (Stage)((AnchorPane)(event).getSource()).getScene().getWindow();
                    stage.close();
                }

            }
        });
    }

    private void saveIt()
    {

        //Save It
        System.out.println("Save it");


    }

}
