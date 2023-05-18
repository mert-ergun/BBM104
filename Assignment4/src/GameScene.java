import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GameScene {
    private Scene scene;
    private static final int SCALE = DuckHunt.SCALE;
    private static final double VOLUME = DuckHunt.VOLUME;
    private ImageView backgroundImage;
    private ImageView foregroundImage;
    private ImageView crosshairImage;
    private ImageView[] backgroundImageViews;
    private ImageView[] foregroundImageViews;
    private ImageView[] crosshairImageViews;

    private int numberOfDucks;
    private Duck[] ducks;
    private int numberOfBullets;
    private int level;

    public GameScene(Stage primaryStage, int backgroundIndex, int crosshairIndex) {
        // Load the background images
        Image background1 = new Image("assets/background/1.png");
        Image background2 = new Image("assets/background/2.png");
        Image background3 = new Image("assets/background/3.png");
        Image background4 = new Image("assets/background/4.png");
        Image background5 = new Image("assets/background/5.png");
        Image background6 = new Image("assets/background/6.png");

        // Create the background image views
        javafx.scene.image.ImageView backgroundView1 = new javafx.scene.image.ImageView(background1);
        javafx.scene.image.ImageView backgroundView2 = new javafx.scene.image.ImageView(background2);
        javafx.scene.image.ImageView backgroundView3 = new javafx.scene.image.ImageView(background3);
        javafx.scene.image.ImageView backgroundView4 = new javafx.scene.image.ImageView(background4);
        javafx.scene.image.ImageView backgroundView5 = new javafx.scene.image.ImageView(background5);
        javafx.scene.image.ImageView backgroundView6 = new javafx.scene.image.ImageView(background6);

        // Scale the background image views
        backgroundView1.setFitWidth(background1.getWidth() * SCALE);
        backgroundView1.setFitHeight(background1.getHeight() * SCALE);
        backgroundView2.setFitWidth(background2.getWidth() * SCALE);
        backgroundView2.setFitHeight(background2.getHeight() * SCALE);
        backgroundView3.setFitWidth(background3.getWidth() * SCALE);
        backgroundView3.setFitHeight(background3.getHeight() * SCALE);
        backgroundView4.setFitWidth(background4.getWidth() * SCALE);
        backgroundView4.setFitHeight(background4.getHeight() * SCALE);
        backgroundView5.setFitWidth(background5.getWidth() * SCALE);
        backgroundView5.setFitHeight(background5.getHeight() * SCALE);
        backgroundView6.setFitWidth(background6.getWidth() * SCALE);
        backgroundView6.setFitHeight(background6.getHeight() * SCALE);

        // Create an array of the background image views
        backgroundImageViews = new javafx.scene.image.ImageView[] {
            backgroundView1,
            backgroundView2,
            backgroundView3,
            backgroundView4,
            backgroundView5,
            backgroundView6
        };

        // Load the crosshair images
        Image crosshair1 = new Image("assets/crosshair/1.png");
        Image crosshair2 = new Image("assets/crosshair/2.png");
        Image crosshair3 = new Image("assets/crosshair/3.png");
        Image crosshair4 = new Image("assets/crosshair/4.png");
        Image crosshair5 = new Image("assets/crosshair/5.png");
        Image crosshair6 = new Image("assets/crosshair/6.png");
        Image crosshair7 = new Image("assets/crosshair/7.png");

        // Create the crosshair image views
        javafx.scene.image.ImageView crosshairView1 = new javafx.scene.image.ImageView(crosshair1);
        javafx.scene.image.ImageView crosshairView2 = new javafx.scene.image.ImageView(crosshair2);
        javafx.scene.image.ImageView crosshairView3 = new javafx.scene.image.ImageView(crosshair3);
        javafx.scene.image.ImageView crosshairView4 = new javafx.scene.image.ImageView(crosshair4);
        javafx.scene.image.ImageView crosshairView5 = new javafx.scene.image.ImageView(crosshair5);
        javafx.scene.image.ImageView crosshairView6 = new javafx.scene.image.ImageView(crosshair6);
        javafx.scene.image.ImageView crosshairView7 = new javafx.scene.image.ImageView(crosshair7);

        // Scale the crosshair image views
        crosshairView1.setFitWidth(crosshair1.getWidth() * SCALE);
        crosshairView1.setFitHeight(crosshair1.getHeight() * SCALE);
        crosshairView2.setFitWidth(crosshair2.getWidth() * SCALE);
        crosshairView2.setFitHeight(crosshair2.getHeight() * SCALE);
        crosshairView3.setFitWidth(crosshair3.getWidth() * SCALE);
        crosshairView3.setFitHeight(crosshair3.getHeight() * SCALE);
        crosshairView4.setFitWidth(crosshair4.getWidth() * SCALE);
        crosshairView4.setFitHeight(crosshair4.getHeight() * SCALE);
        crosshairView5.setFitWidth(crosshair5.getWidth() * SCALE);
        crosshairView5.setFitHeight(crosshair5.getHeight() * SCALE);
        crosshairView6.setFitWidth(crosshair6.getWidth() * SCALE);
        crosshairView6.setFitHeight(crosshair6.getHeight() * SCALE);
        crosshairView7.setFitWidth(crosshair7.getWidth() * SCALE);
        crosshairView7.setFitHeight(crosshair7.getHeight() * SCALE);

        // Create an array of the crosshair image views
        crosshairImageViews = new javafx.scene.image.ImageView[] {
            crosshairView1,
            crosshairView2,
            crosshairView3,
            crosshairView4,
            crosshairView5,
            crosshairView6,
            crosshairView7
        };

        // Load the foreground images
        Image foreground1 = new Image("assets/foreground/1.png");
        Image foreground2 = new Image("assets/foreground/2.png");
        Image foreground3 = new Image("assets/foreground/3.png");
        Image foreground4 = new Image("assets/foreground/4.png");
        Image foreground5 = new Image("assets/foreground/5.png");
        Image foreground6 = new Image("assets/foreground/6.png");

        // Create the foreground image views
        javafx.scene.image.ImageView foregroundView1 = new javafx.scene.image.ImageView(foreground1);
        javafx.scene.image.ImageView foregroundView2 = new javafx.scene.image.ImageView(foreground2);
        javafx.scene.image.ImageView foregroundView3 = new javafx.scene.image.ImageView(foreground3);
        javafx.scene.image.ImageView foregroundView4 = new javafx.scene.image.ImageView(foreground4);
        javafx.scene.image.ImageView foregroundView5 = new javafx.scene.image.ImageView(foreground5);
        javafx.scene.image.ImageView foregroundView6 = new javafx.scene.image.ImageView(foreground6);

        // Scale the foreground image views
        foregroundView1.setFitWidth(foreground1.getWidth() * SCALE);
        foregroundView1.setFitHeight(foreground1.getHeight() * SCALE);
        foregroundView2.setFitWidth(foreground2.getWidth() * SCALE);
        foregroundView2.setFitHeight(foreground2.getHeight() * SCALE);
        foregroundView3.setFitWidth(foreground3.getWidth() * SCALE);
        foregroundView3.setFitHeight(foreground3.getHeight() * SCALE);
        foregroundView4.setFitWidth(foreground4.getWidth() * SCALE);
        foregroundView4.setFitHeight(foreground4.getHeight() * SCALE);
        foregroundView5.setFitWidth(foreground5.getWidth() * SCALE);
        foregroundView5.setFitHeight(foreground5.getHeight() * SCALE);
        foregroundView6.setFitWidth(foreground6.getWidth() * SCALE);
        foregroundView6.setFitHeight(foreground6.getHeight() * SCALE);

        // Create an array of the foreground image views
        foregroundImageViews = new javafx.scene.image.ImageView[] {
            foregroundView1,
            foregroundView2,
            foregroundView3,
            foregroundView4,
            foregroundView5,
            foregroundView6
        };

        // Set the background, crosshair, and foreground image views
        backgroundImage = backgroundImageViews[backgroundIndex];
        foregroundImage = foregroundImageViews[backgroundIndex];
        crosshairImage = crosshairImageViews[crosshairIndex];

        // Add the background and foreground image views to the scene
        StackPane root = new StackPane();
        root.getChildren().add(backgroundImage);
        root.getChildren().add(foregroundImage);

        // Create the scene
        scene = new Scene(root, backgroundImage.getFitWidth(), backgroundImage.getFitHeight());
        
        // Create the crosshair as cursor
        scene.setCursor(new ImageCursor(crosshairImage.getImage()));


        // Show the scene
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public Scene getScene() {
        return scene;
    }

    public void initFirstLevel() {
        numberOfDucks = 1;
        ducks = new Duck[numberOfDucks];

        
    }
}
