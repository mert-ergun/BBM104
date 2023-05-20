import java.io.File;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

/**
 * The BackgroundScene class represents the game's background selection screen.
 * It sets up the background selection screen's graphics, text, and sound effects, and handles user input.
 * It also handles the background music.
 *
 * User can change the background by pressing the left and right arrow keys.
 * User can change the crosshair by pressing the up and down arrow keys.
 * User can return to the title screen by pressing the escape key.
 * User can select the background by pressing the enter key.
 */
public class BackgroundScene {
    private Scene scene;
    private static final int SCALE = DuckHunt.SCALE;
    private static final double VOLUME = DuckHunt.VOLUME;
    private int backgroundIndex = 0;  // The index of the current background
    private int crosshairIndex = 0;  // The index of the current crosshair
    private javafx.scene.image.ImageView[] backgroundViews;  // An array of the background image views
    private javafx.scene.image.ImageView[] crosshairViews;  // An array of the crosshair image views
    private Pane crosshairPane;

    /**
     * The BackgroundScene constructor sets up the game's background selection screen.
     * It loads the background selection screen's graphics, text, and sound effects, and handles user input.
     * It also handles the background music.
     * @param primaryStage The primary stage of the game.
     */
    public BackgroundScene(Stage primaryStage) {
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
        backgroundViews = new javafx.scene.image.ImageView[] {
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
        crosshairViews = new javafx.scene.image.ImageView[] {
            crosshairView1,
            crosshairView2,
            crosshairView3,
            crosshairView4,
            crosshairView5,
            crosshairView6,
            crosshairView7
        };

        // Create a Pane to hold the crosshair image views
        crosshairPane = new Pane();
        crosshairPane.getChildren().addAll(crosshairViews);

        // Create a VBox to hold the text Labels and set its properties
        javafx.scene.layout.VBox textContainer = new javafx.scene.layout.VBox();
        textContainer.setAlignment(Pos.TOP_CENTER);
        textContainer.setSpacing(10);
        textContainer.setPadding(new javafx.geometry.Insets(10 * SCALE, 0, 0, 0));

        // Create the text Labels 
        Label navLabel = new Label("USE ARROW KEYS TO NAVIGATE");
        Label startLabel = new Label("PRESS ENTER TO START");
        Label exitLabel = new Label("PRESS ESC TO EXIT");

        // Set the text Label font as Verdana, bold and 8pt
        navLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 8 * SCALE));
        startLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 8 * SCALE));
        exitLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 8 * SCALE));

        // Set the text Label color as orange
        navLabel.setTextFill(Color.ORANGE);
        startLabel.setTextFill(Color.ORANGE);
        exitLabel.setTextFill(Color.ORANGE);
        
        // Add the text Labels to the VBox
        textContainer.getChildren().addAll(navLabel, startLabel, exitLabel);
        
        // Create a StackPane to hold the background and crosshair image views
        javafx.scene.layout.StackPane root = new javafx.scene.layout.StackPane();
        root.getChildren().addAll(backgroundViews);
        root.getChildren().add(crosshairPane);
        root.getChildren().add(textContainer);

        // Set invisible all the background and crosshair image views except the first one
        for (int i = 1; i < backgroundViews.length; i++) {
            backgroundViews[i].setVisible(false);
        }
        for (int i = 1; i < crosshairViews.length; i++) {
            crosshairViews[i].setVisible(false);
        }
        
        // Create the scene and set it on the stage 
        scene = new Scene(root, background1.getWidth() * SCALE, background1.getHeight() * SCALE);
        primaryStage.setScene(scene);

        // Set the title of the stage
        primaryStage.setTitle("HUBBM Duck Hunt");
        
        // Set the favicon
        primaryStage.getIcons().add(new Image("assets/favicon/1.png"));
        
        // Set the initial position of the crosshair Pane to the center of the StackPane
        Translate crosshairTranslate = new Translate();
        crosshairTranslate.setX(scene.getWidth() / 2 - crosshairView1.getFitWidth() / 2);
        crosshairTranslate.setY(scene.getHeight() / 2 - crosshairView1.getFitHeight() / 2);
        crosshairPane.getTransforms().add(crosshairTranslate);
        
        // Show the stage
        primaryStage.show();

        // Add event handler to check if the user presses the arrow keys or the enter key
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.LEFT) {
                // Move to the previous background image view
                backgroundIndex--;
                if (backgroundIndex < 0) {
                    backgroundIndex = backgroundViews.length - 1;
                }
                for (int i = 0; i < backgroundViews.length; i++) {
                    if (i == backgroundIndex) {
                        backgroundViews[i].setVisible(true);
                    } else {
                        backgroundViews[i].setVisible(false);
                    }
                }
            } else if (event.getCode() == KeyCode.RIGHT) {
                // Move to the next background image view
                backgroundIndex++;
                if (backgroundIndex >= backgroundViews.length) {
                    backgroundIndex = 0;
                }
                for (int i = 0; i < backgroundViews.length; i++) {
                    if (i == backgroundIndex) {
                        backgroundViews[i].setVisible(true);
                    } else {
                        backgroundViews[i].setVisible(false);
                    }
                }
            } else if (event.getCode() == KeyCode.UP) {
                // Move to the previous crosshair image view
                crosshairIndex--;
                if (crosshairIndex < 0) {
                    crosshairIndex = crosshairViews.length - 1;
                }
                crosshairPane.getChildren().clear();
                crosshairPane.getChildren().add(crosshairViews[crosshairIndex]);
                for (int i = 0; i < crosshairViews.length; i++) {
                    if (i == crosshairIndex) {
                        crosshairViews[i].setVisible(true);
                    } else {
                        crosshairViews[i].setVisible(false);
                    }
                }
            } else if (event.getCode() == KeyCode.DOWN) {
                // Move to the next crosshair image view
                crosshairIndex++;
                if (crosshairIndex >= crosshairViews.length) {
                    crosshairIndex = 0;
                }
                crosshairPane.getChildren().clear();
                crosshairPane.getChildren().add(crosshairViews[crosshairIndex]);
                for (int i = 0; i < crosshairViews.length; i++) {
                    if (i == crosshairIndex) {
                        crosshairViews[i].setVisible(true);
                    } else {
                        crosshairViews[i].setVisible(false);
                    }
                }
            } else if (event.getCode() == KeyCode.ESCAPE) {
                // Return to the Title screen
                TitleScene titleScreen = DuckHunt.getTitleScene();
                primaryStage.setScene(titleScreen.getScene());
            } else if (event.getCode() == KeyCode.ENTER) {
                // Start the game
                DuckHunt.getTitleScene().stopMusic();  // Stop the Title screen music
                Media introMusic = new Media(new File("src/assets/effects/Intro.mp3").toURI().toString());  // Load the intro music
                MediaPlayer introPlayer = new MediaPlayer(introMusic);
                introPlayer.setOnEndOfMedia(() -> {  
                    // Start the game after the intro music ends
                    GameScene gameScreen = new GameScene(primaryStage, backgroundIndex, crosshairIndex);
                    primaryStage.setScene(gameScreen.getScene());
                });
                introPlayer.setVolume(VOLUME);
                introPlayer.play();
            }
        });
    }

    /**
     * Returns the Scene object of the Game screen
     * @return the Scene object of the Game screen
     */
    public Scene getScene() {
        return scene;
    }
}