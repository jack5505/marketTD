package uz.dukon.controllers.application.widgets;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class WImageView extends ImageView {

    public WImageView(String imgUrl){
        this.init(imgUrl, 0, 0);
    }

    public WImageView(String imgUrl,int fitWidth,int fitHeight){
        this.init(imgUrl,fitWidth,fitHeight);
    }

    private void init(String imgUrl, int fitWidth, int fitHeight) {
        try {
            Image image = new Image(getClass().getResourceAsStream(imgUrl));
            setImage(image);
            if(fitWidth!=0){
                setFitWidth(fitWidth);
            }
            if(fitHeight!=0){
                setFitHeight(fitHeight);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
