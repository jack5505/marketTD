package uz.dukon.controllers;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import uz.dukon.App;
import uz.dukon.config.AppConfig;
import uz.dukon.config.PersistenceConfig;
import uz.dukon.controllers.application.widgets.WPopup;
import uz.dukon.utils.FxmlUrl;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoadingController implements Initializable {

    private Timeline timeline;

    @FXML
    ProgressBar progressBar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run()
            {
                App.ctx = new AnnotationConfigApplicationContext();
                App.ctx.register(AppConfig.class);
                App.ctx.register(PersistenceConfig.class);
                App.ctx.refresh();
            }
        });
        thread.setDaemon(true);
        thread.start();

        IntegerProperty seconds = new SimpleIntegerProperty();
        progressBar.progressProperty().bind(seconds.divide(90.0));
        timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(seconds, 0)),
                new KeyFrame(Duration.seconds(10), e-> {
                    try {
                        FXMLLoader login = new FXMLLoader(getClass().getResource("/uz/dukon/application/product/content/SellProductNewVersion.fxml"));
                        Scene scene = new Scene(login.load());
                        App.window.hide();
                        Stage stage = new Stage();
                        stage.setTitle("Учет склада 2");
                        stage.setScene(scene);
                        stage.resizableProperty().setValue(false);
                        stage.setMaximized(true);
                        stage.setOnCloseRequest(event -> {
                            new WPopup(FxmlUrl.Product.exitWindow,"Чикиш").show();
                            event.consume();
                        });
//                        stage.getIcons().add(new Image("/images/iconss.png"));
                        stage.centerOnScreen();
                        stage.show();
                        App.window = stage;
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    timeline.stop();
                }, new KeyValue(seconds, 100))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

}
