package uz.dukon;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import uz.dukon.controllers.events.EventBus;
import uz.dukon.controllers.events.FXEventBus;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App extends Application {

    public static Stage window;
    public static EventBus eventBus = new FXEventBus();;
    public static AnnotationConfigApplicationContext ctx;

    public static void main(String... args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {

            App.window = primaryStage;
            Parent root = FXMLLoader.load(getClass().getResource("Loading.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("Учет склада");
            primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.setScene(scene);
            primaryStage.centerOnScreen();
//            primaryStage.getIcons().add(new Image("/images/iconss.png"));
            primaryStage.show();

        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
