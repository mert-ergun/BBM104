import javafx.application.Application;
import javafx.stage.Stage;

public class DuckHunt extends Application{
    public static final int SCALE = 2;
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
    
    public static TitleScene getTitleScene() {
        return titleScene;
    }
}
