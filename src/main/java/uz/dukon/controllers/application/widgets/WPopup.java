package uz.dukon.controllers.application.widgets;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class WPopup {
    private String fxmlUrl;
    private String title;
    private Stage stage;
    private FXMLLoader fxmlLoader;

    public WPopup(String fxmlUrl,String title){
        this.fxmlUrl = fxmlUrl;
        System.out.println(this.fxmlUrl);
        this.title = title;
        this.init();
    }

    private void init() {
        try {
            fxmlLoader = new FXMLLoader();
            Parent root = fxmlLoader.load(this.getClass().getResource(fxmlUrl).openStream());
            stage  = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UTILITY);
            stage.getIcons().addAll(new Image("/images/iconss.png"));
            stage.setResizable(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setModality(Modality modality) {
        this.stage.initModality(modality);
    }

    public void setStageStyle(StageStyle stageStyle) {
        this.stage.initStyle(stageStyle);
    }

    public void show(){
        stage.show();
    }

    public void showAndWait(){
        stage.showAndWait();
    }

    public <T> T getController(){
        return fxmlLoader.getController();
    }
    public Stage getStage(){return this.stage;}
}
