package uz.dukon.controllers.application.product.content;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import uz.dukon.App;
import uz.dukon.controllers.application.product.events.DeleteProductEvent;
import uz.dukon.service.TransactionsService;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Jack on 01.03.2019.
 */
public class AskDeletedController implements Initializable {

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnCancel;

    @FXML
    private JFXTextField passwordField;

    @FXML
    private Label info;

    //hatolik haqidaki label
    @FXML
    private Label info1;

    @FXML
    private AnchorPane anchor;

    private String delet;

    private Long id;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        delet = "other";
        info1.setVisible(false);
        registerEvents();


    }
    private void registerEvents()
    {
        //mishka bilan xa bosilganda
        btnAdd.setOnAction(event ->
        {
            if(checkPassword(passwordField.getText()))
            {
                if(delet.equals("transactionss")){
                    App.ctx.getBean(TransactionsService.class).updateDeletedAll();
                }
                else
                {
                    DeleteProductEvent deleteProductEvent = new DeleteProductEvent(DeleteProductEvent.ANY,this.id);
                    App.eventBus.fireEvent(deleteProductEvent);
                }
                close(event);
            }
            else{
                info1.setVisible(true);
            }

        });
        btnCancel.setOnAction(event -> {
            close(event);
        });
        //Enter bosilganda password to`ldirilib
        anchor.setOnKeyPressed(event ->
        {
            if(event.getCode().equals(KeyCode.ENTER))
            {
                if(checkPassword(passwordField.getText()))
                {
                    if(delet.equals("transactionss")){
                        App.ctx.getBean(TransactionsService.class).updateDeletedAll();
                    }
                    else{
                        //qategoriya o`chirish
                    }
                    close(event);
                }
                else
                {
                info1.setVisible(true);
                 }
            }

        });
    }

    private void close(KeyEvent event) {
        Stage stage = (Stage) ((AnchorPane)event.getSource()).getScene().getWindow();
        stage.close();
    }

    private void close(ActionEvent event) {
        Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        stage.close();
    }


    private boolean checkPassword(String temp)
    {
        if(temp.equals("admin"))
            return true;
        return false;
    }

    public void deleteReport() {
            delet = "transactionss";
    }

    public void deleteType(Long id) {
        this.id = id;
    }
}
