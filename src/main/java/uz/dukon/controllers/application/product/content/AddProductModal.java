package uz.dukon.controllers.application.product.content;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.springframework.util.FileSystemUtils;
import uz.dukon.controllers.application.widgets.FilterForTextField;
import uz.dukon.controllers.application.widgets.WPopup;
import uz.dukon.utils.FxmlUrl;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Jack on 17.02.2019.
 */
public class AddProductModal implements Initializable
{
    @FXML
    private AnchorPane anchor;

    @FXML
    private JFXTextField productName;

    @FXML
    private JFXTextField sold;

    @FXML
    private JFXTextField  dimensionCount;

    @FXML
    private JFXComboBox<String> types;

    @FXML
    private Button addType;

    @FXML
    private JFXButton uploadImage;

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnCancel;


    @FXML
    private Label infoAboutProduct;

    @FXML
    private ImageView img;

    @FXML
    private Label fill1;

    @FXML
    private Label fill2;

    @FXML
    private Label fill3;

    @FXML
    private Label fill4;

    @FXML
    private JFXTextField dimensionQuantity;





    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        events();
        prepare();
        types.getItems().add("bir");


    }
    private void prepare() {
        infoAboutProduct.setVisible(false);
        fill1.setVisible(false);
        fill2.setVisible(false);
        fill3.setVisible(false);
        fill4.setVisible(false);
        FilterForTextField.isIntegerValidation(sold);
        FilterForTextField.isDoubleValidation(dimensionQuantity);
    }
    private void events()
    {
        //Rasim yuklash
        uploadImage.setOnAction(event ->
        {
            //Here is give setting of FileChooser and copying it into directory
            //path of choosing File is in temp String path is selected image
            Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            FileChooser.ExtensionFilter ex1 = new FileChooser.ExtensionFilter("JPG files(*.jpg)","*.jpg");
            FileChooser.ExtensionFilter ex2 = new FileChooser.ExtensionFilter("PNG files(*.png)","*.png");
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(ex1,ex2);
            File approve = fileChooser.showOpenDialog(stage);
            String g = approve.getAbsolutePath();
            File path = new File(approve.getAbsolutePath());
            File savePlace = new File("C:/images");
            if(!savePlace.exists()){
                savePlace.mkdir();
            }
            String temp = savePlace.getAbsolutePath();
            temp +="\\" + path.getName();

            System.out.println(temp);
            savePlace = new File(temp);
            try
            {
                FileSystemUtils.copyRecursively(path,savePlace);
            } catch (IOException e) {
                e.printStackTrace();
            }

            BufferedImage bf;
            try {
                bf = ImageIO.read(approve);
                Image image = SwingFXUtils.toFXImage(bf,null);
                img.setImage(image);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        //Save button
        btnAdd.setOnAction(event ->
        {
            check();
            if(allFilled())
            {
                System.out.println("yes");
                infoAboutProduct.setText("Товар Кўшилди: " + productName.getText());
                infoAboutProduct.setVisible(true);
                clear();
            }


        });

        //Cancel button
        btnCancel.setOnAction(event -> {
            close(event);
        });

        //Call window of Type
        addType.setOnAction(event ->
        {
            new WPopup(FxmlUrl.Product.addType,"Тип кўшиш").show();
        });

    }

    private void clear() {
        productName.setText("");
        productName.requestFocus();
        sold.setText("");
        dimensionCount.setText("");
        dimensionQuantity.setText("");
        img.setImage(null);
    }

    private boolean allFilled()
    {
        Image image = img.getImage();
        if(image == null)
            return false;
//        if(types.getSelectionModel().isEmpty())
//            return false;

        if(fill1.isVisible() || fill2.isVisible() || fill3.isVisible() || fill4.isVisible())
            return false;
        return true;
    }

    private void check() {
        fill1.setVisible(productName.getText().isEmpty());
        fill2.setVisible(sold.getText().isEmpty());
        fill3.setVisible(dimensionCount.getText().isEmpty());
        fill4.setVisible(dimensionQuantity.getText().isEmpty());
    }

    //Close window
    private void close(ActionEvent event) {
        Stage stage = (Stage)((Button)(event).getSource()).getScene().getWindow();
        stage.close();
    }





}
