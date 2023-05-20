import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * The TitleScene class represents the game's title screen. 
 * It sets up the title screen's graphics, text, and sound effects, and handles user input.
 */
public class TitleScene {
    private Scene scene;  // The title scene of the game
    private static final int SCALE = DuckHunt.SCALE;  // The scale of the game, got from DuckHunt class
    private static final double VOLUME = DuckHunt.VOLUME;  // The volume of the game's sound effects and music (0.0 - 1.0)
    public static boolean cheatObtained = false;  // A boolean to check if the cheat is obtained

    /**
     * The TitleScene constructor sets up the game's title screen. 
     * It loads the title screen's graphics, text, and sound effects, and handles user input.
     * @param primaryStage The primary stage of the game.
     */
    public TitleScene(Stage primaryStage) {
        // Load the welcome image and create an ImageView to hold it
        Image welcomeImage = new Image("assets/welcome/1.png");
        ImageView imageView = new ImageView(welcomeImage);
        imageView.setFitWidth(welcomeImage.getWidth() * SCALE);  // Scale the image
        imageView.setFitHeight(welcomeImage.getHeight() * SCALE);

        // Create the text to be displayed on the screen
        Text line1 = new Text("PRESS ENTER TO PLAY");
        Text line2 = new Text("PRESS ESC TO EXIT");
        line1.setFont(Font.font("Verdana",FontWeight.BOLD, 18 * SCALE));  // Scale the text
        line1.setFill(javafx.scene.paint.Color.ORANGE);
        line2.setFont(Font.font("Verdana",FontWeight.BOLD, 18 * SCALE));
        line2.setFill(javafx.scene.paint.Color.ORANGE);

        // Create VBox to hold the text
        javafx.scene.layout.VBox vBox = new javafx.scene.layout.VBox();
        vBox.getChildren().addAll(line1, line2);
        vBox.setAlignment(javafx.geometry.Pos.CENTER);
        vBox.setSpacing(5 * SCALE);  // Scale the spacing
        vBox.setPadding(new javafx.geometry.Insets(90 * SCALE, 0, 0, 0));  // Scale the padding

        // Create a ScaleTransition to scale the VBox
        javafx.animation.ScaleTransition scaleTransition = new javafx.animation.ScaleTransition(Duration.ZERO, vBox);
        scaleTransition.setToX(SCALE);
        scaleTransition.setToY(SCALE);

        // Create a Timeline to flash the text 
        Timeline timeline = new Timeline(
            new javafx.animation.KeyFrame(Duration.seconds(0.5), e -> {
                line1.setVisible(!line1.isVisible());
                line2.setVisible(!line2.isVisible());
            })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);  // Make the Timeline repeat infinitely
        
        // Create a StackPane to hold the image
        StackPane root = new StackPane();
        root.getChildren().addAll(imageView, vBox);  // Add the image and VBox to the StackPane

        // Create the scene and set it on the stage
        scene = new Scene(root, welcomeImage.getWidth() * SCALE, welcomeImage.getHeight() * SCALE);
        primaryStage.setScene(scene);

        // Set the title of the stage
        primaryStage.setTitle("HUBBM Duck Hunt");

        // Set the favicon
        primaryStage.getIcons().add(new Image("assets/favicon/1.png"));

        // Show the stage
        primaryStage.show();

        // Play the blink transition and scale transition
        timeline.play();
        scaleTransition.play();

        // Load and play the title sound effect in loop
        AudioClip titleSound = new AudioClip(getClass().getResource("assets/effects/Title.mp3").toString());
        titleSound.setVolume(VOLUME);
        titleSound.setCycleCount(AudioClip.INDEFINITE);
        titleSound.play();
        
        // Create a string to hold the keys pressed to check for the cheat
        String[] keysPressed = {""};

        // Set the key listeners
        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case ENTER:  // If the enter key is pressed, start the game
                    // Set scene to BackgroundScene
                    BackgroundScene backgroundScene = new BackgroundScene(primaryStage);
                    primaryStage.setScene(backgroundScene.getScene());
                    break;
                case ESCAPE:  // If the escape key is pressed, exit the game
                    // Stop the title sound
                    titleSound.stop();

                    // Exit the game
                    primaryStage.close();
                    break;
                default:  // If any other key is pressed, add it to the string and check for the cheat
                    keysPressed[0] += e.getCode().toString();
                    if (keysPressed[0].contains("UP") && keysPressed[0].contains("UP") && keysPressed[0].contains("DOWN") && keysPressed[0].contains("DOWN") && keysPressed[0].contains("LEFT") && keysPressed[0].contains("RIGHT") && keysPressed[0].contains("LEFT") && keysPressed[0].contains("RIGHT") && keysPressed[0].contains("B") && keysPressed[0].contains("A")) {
                        cheatObtained = true;
                        // Add a flashing text to the scene to indicate that the cheat has been obtained
                        Text cheatObtainedText = new Text("CHEAT ACTIVATED!!!");
                        cheatObtainedText.setFont(Font.font("Verdana",FontWeight.BOLD, 18 * SCALE));
                        cheatObtainedText.setFill(javafx.scene.paint.Color.RED);
                        cheatObtainedText.setX(100 * SCALE);
                        cheatObtainedText.setY(100 * SCALE);
                        Timeline cheatObtainedTimeline = new Timeline(
                            new javafx.animation.KeyFrame(Duration.seconds(0.5), e2 -> {
                                cheatObtainedText.setVisible(!cheatObtainedText.isVisible());
                            })
                        );
                        cheatObtainedTimeline.setCycleCount(Timeline.INDEFINITE);
                        cheatObtainedTimeline.play();
                        root.getChildren().add(cheatObtainedText);
                        Media media = new Media(getClass().getResource("assets/effects/GameCompleted.mp3").toString());
                        MediaPlayer mediaPlayer = new MediaPlayer(media);
                        mediaPlayer.setVolume(VOLUME);
                        mediaPlayer.play();
                    }
                    
                    break;
            }
        });

        
    }

    /**
     * The getScene method returns the scene of the TitleScene.
     * @return The scene of the TitleScene.
     */
    public Scene getScene() {
        return scene;
    }

    /**
     * The stopMusic method stops the title sound effect.
     */
    public void stopMusic() {
        // Stop the title sound
        AudioClip titleSound = new AudioClip(getClass().getResource("assets/effects/Title.mp3").toString());
        titleSound.stop();
    }
}