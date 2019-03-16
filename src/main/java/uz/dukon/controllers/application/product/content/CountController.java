package uz.dukon.controllers.application.product.content;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import uz.dukon.App;
import uz.dukon.controllers.application.product.events.AddCartEvent;
import uz.dukon.controllers.newmodel.CartTable;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Jack on 10.03.2019.
 */
public class CountController implements Initializable {
    @FXML
     private   Button accept;
    @FXML
    private TextField textField;
    private  boolean dot=false;
    private CartTable cartTable;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

            accept.setOnAction(event -> {
                        // ok bosiladi shorda
        if(textField.getText().isEmpty() || textField.getText().equals("0")){
            Stage stage = (Stage)((Button)(event).getSource()).getScene().getWindow();
            stage.close();
            cartTable.setDontAdd(true);
        }
        else if(this.cartTable.getChange() == true)
        {
            cartTable.setDontAdd(false);
            editIt();
        }
        else
        {
            cartTable.setDontAdd(false);
            saveIt();
        }
        AddCartEvent addCartEvent = new AddCartEvent(AddCartEvent.ANY,this.cartTable);
        App.eventBus.fireEvent(addCartEvent);
        Stage stage = (Stage) accept.getScene().getWindow();
        stage.close();
            });
    }

    private void saveIt() {
        this.cartTable.setCountP(new BigDecimal(textField.getText()));
        cartTable.setTotalSumm(new BigDecimal(cartTable.getSellPrice()).multiply(cartTable.getCountP()));
    }

    private void editIt() {
        this.cartTable.setCountP(new BigDecimal(textField.getText()));
        cartTable.setTotalSumm(new BigDecimal(cartTable.getSellPrice()).multiply(cartTable.getCountP()));
    }

    public  void numAction(ActionEvent actionEvent){
        int x=Integer.parseInt(((Button)actionEvent.getSource()).getId());
        if(x==1){
            textField.setText(textField.getText()+"1");
        }
        else if(x==2){
            textField.setText(textField.getText()+"2");
        }
        else if(x==3){
            textField.setText(textField.getText()+"3");
        }
        else if(x==4){
            textField.setText(textField.getText()+"4");

        }
        else if(x==5){
            textField.setText(textField.getText()+"5");
        }
        else if(x==6){
            textField.setText(textField.getText()+"6");
        }
        else if(x==7){
            textField.setText(textField.getText()+"7");
        }
        else if(x==8){
            textField.setText(textField.getText()+"8");
        }
        else if(x==9){
            textField.setText(textField.getText()+"9");
        }
        else if(x == 0){
            if(!textField.getText().isEmpty()){
                textField.setText(textField.getText()+"0");
            }
        }
        else if(x == -1){
            textField.setText("");
            dot=false;
        }
        else if(x == -2) {
            if(!textField.getText().isEmpty() && dot==false){
                textField.setText(textField.getText()+".");
            }
            else if(textField.getText().isEmpty() && dot==false){
                textField.setText(textField.getText()+"0.");
            }
            dot=true;
        }
        else {
            textField.setText("Hato");
        }
    }
    public void acceptAction(ActionEvent actionEvent) {
//        // ok bosiladi shorda
//        System.out.println(textField.getText());
//            //TODO here is big issue which I cann't solve
//             this.cartTable.setCountP(new BigDecimal(textField.getText()));
////        System.out.println(this.cartTable.getChange() ? "Changed" : "Not Changed");
//        System.out.println(this.cartTable.getSellPrice()+ " " + cartTable.getCountP());
//        cartTable.setTotalSumm(new BigDecimal(cartTable.getSellPrice()).multiply(cartTable.getCountP()));
//        AddCartEvent addCartEvent = new AddCartEvent(AddCartEvent.ANY,this.cartTable);
//        App.eventBus.fireEvent(addCartEvent);
//        Stage stage = (Stage) accept.getScene().getWindow();
//        stage.close();
    }

    public void prepareToAdd(CartTable cartTable) {
        this.cartTable = cartTable;
        this.cartTable.setChange(false);
    }
    public void prepareToEdit(CartTable cartTable){
        this.cartTable = cartTable;
        this.cartTable.setChange(true);
        textField.setText(cartTable.getCountP().toString());

    }
}
