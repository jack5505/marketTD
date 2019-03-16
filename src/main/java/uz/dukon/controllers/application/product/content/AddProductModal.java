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
import uz.dukon.App;
import uz.dukon.controllers.application.product.events.AddProductEvent;
import uz.dukon.controllers.application.product.events.AddTypeEvent;
import uz.dukon.controllers.application.widgets.FilterForTextField;
import uz.dukon.controllers.application.widgets.WPopup;
import uz.dukon.controllers.model.ProductDtoList;
import uz.dukon.controllers.model.ProductModal;
import uz.dukon.controllers.model.TypeDto;
import uz.dukon.service.ProductService;
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
    private JFXComboBox<TypeDto> types;

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
    private Label fill4;

    @FXML
    private JFXTextField dimensionQuantity;


    private ProductModal productModal = new ProductModal();


    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        events();
        prepare();



    }
    private void prepare() {
        infoAboutProduct.setVisible(false);
        fill1.setVisible(false);
        fill2.setVisible(false);
        fill4.setVisible(false);
        FilterForTextField.isIntegerValidation(sold);
        types.getItems().addAll(App.ctx.getBean(ProductService.class).getAllType());
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
            productModal.setPathImage(temp);
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
                App.ctx.getBean(ProductService.class).create(productModal);
                if(productModal.getProductId() == null)
                    infoAboutProduct.setText("Товар Кўшилди: " + productName.getText());
                else
                    infoAboutProduct.setText("Товар ўзгартирилди" + productName.getText());
                infoAboutProduct.setVisible(true);
                clear();
            }


        });

        //Cancel button
        btnCancel.setOnAction(event -> {
            close(event);
            AddProductEvent addProductEvent = new AddProductEvent(AddProductEvent.ANY);
            App.eventBus.fireEvent(addProductEvent);
        });

        //Call window of Type
        addType.setOnAction(event ->
        {
            new WPopup(FxmlUrl.Product.addType,"Тип кўшиш").show();
        });

        App.eventBus.addEventHandler(AddTypeEvent.ANY,event -> {
            types.getItems().addAll(App.ctx.getBean(ProductService.class).getAllType());
            types.getSelectionModel().selectLast();
        });
    }

    private void clear() {
        productName.setText("");
        productName.requestFocus();
        sold.setText("");
        dimensionQuantity.setText("");
        img.setImage(null);
    }

    private boolean allFilled()
    {
        Image image = img.getImage();
        if(image == null)
            return false;
        if(types.getSelectionModel().isEmpty())
            return false;
        if(fill1.isVisible() || fill2.isVisible() ||  fill4.isVisible())
            return false;
        productModal.setProductName(productName.getText());
        productModal.setSellPrice(Long.parseLong(sold.getText()));
        productModal.setTypeId(types.getSelectionModel().getSelectedItem().getTypeId());
        productModal.setDimension(dimensionQuantity.getText());
        return true;
    }

    private void check() {
        fill1.setVisible(productName.getText().isEmpty());
        fill2.setVisible(sold.getText().isEmpty());
        fill4.setVisible(dimensionQuantity.getText().isEmpty());
    }

    //Close window
    private void close(ActionEvent event) {
        Stage stage = (Stage)((Button)(event).getSource()).getScene().getWindow();
        stage.close();
    }

    public void setEditProduct(ProductDtoList productDtoList){
         productModal.setProductId(productDtoList.getProductId());
         productName.setText(productDtoList.getProductName());
         sold.setText(productDtoList.getSellPrice().toString());
         dimensionQuantity.setText(productDtoList.getDimension());
         Image image = new Image("file:///"+productDtoList.getPathImage());
         img.setImage(image);
         productModal.setPathImage(productDtoList.getPathImage());
         types.getItems().forEach(typeDto -> {
             if(typeDto.getTypeId().equals(productDtoList.getTypeId())){
                 types.getSelectionModel().select(typeDto);
             }
         });

    }






}
