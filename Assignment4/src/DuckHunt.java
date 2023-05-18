import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DuckHunt extends Application{
    public static final int SCALE = 3;
    public static final double VOLUME = 0.2;
    public static TitleScene titleScene;

    @Override
    public void start(Stage primaryStage) throws Exception {
        titleScene = new TitleScene(primaryStage);
        primaryStage.setScene(titleScene.getScene());
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    public static TitleScene getTitleScene(Stage primaryStage) {
        return titleScene;
    }
}
