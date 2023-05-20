import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The DuckHunt class is the main class of the game. 
 * It extends the JavaFX Application class and sets up the game's title scene.
 * It also contains constants for the game's scale and volume, as well as a reference to the game's title scene.
 * @author Mert ERGÜN
 * @version 1.0
 */
public class DuckHunt extends Application{
    public static final int SCALE = 4;  // The scale of the game, used to scale the game's images and text
    public static final double VOLUME = 0.2;  // The volume of the game's sound effects and music (0.0 - 1.0)
    public static TitleScene titleScene;  // A reference to the game's title scene

    @Override
    public void start(Stage primaryStage) throws Exception {
        titleScene = new TitleScene(primaryStage);  // Create the title scene and set it on the stage
        primaryStage.setScene(titleScene.getScene());
    }

    /**
     * The main method of the game. It launches the game.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    /**
     * Returns the game's title scene.
     * @return the game's title scene
     */
    public static TitleScene getTitleScene() {
        return titleScene;
    }
}
