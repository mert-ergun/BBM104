import java.io.File;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

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
    private boolean levelCompleted;

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

        // Create the scene
        scene = new Scene(root, backgroundImage.getFitWidth(), backgroundImage.getFitHeight());
        
        // Create the crosshair as cursor
        scene.setCursor(new ImageCursor(crosshairImage.getImage()));

        // Initalize the first level
        ducks = initLevel(1);
        updateSceneForLevel(primaryStage);

        // Show the scene
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public Scene getScene() {
        return scene;
    }

    public Duck[] initLevel(int level) {
        switch (level) {
            case 1:
                numberOfDucks = 1;
                ducks = new Duck[numberOfDucks];
                Duck duck = new Duck("red", 30, 20, 30, true, false, true);
                ducks[0] = duck;
                this.level = 1;
                break;
            case 2:
                numberOfDucks = 2;
                ducks = new Duck[numberOfDucks];
                Duck duck1 = new Duck("blue", 10, 20, 40, true, false, true);
                Duck duck2 = new Duck("red", -150, -200, 40, false, false, true);
                ducks[0] = duck1;
                ducks[1] = duck2;
                this.level = 2;
                break;
            case 3:
                numberOfDucks = 3;
                ducks = new Duck[numberOfDucks];
                Duck duck3 = new Duck("blue", 10, 20, 40, true, false, true);
                Duck duck4 = new Duck("red", -150, -200, 40, false, false, true);
                Duck duck5 = new Duck("black", 50, 20, 40, true, true, true);
                ducks[0] = duck3;
                ducks[1] = duck4;
                ducks[2] = duck5;
                this.level = 3;
                break;
            case 4:
                numberOfDucks = 3;
                ducks = new Duck[numberOfDucks];
                Duck duck6 = new Duck("blue", 10, 20, 40, true, false, true);
                Duck duck7 = new Duck("red", -150, -200, 40, false, false, true);
                Duck duck8 = new Duck("black", 50, 20, 40, false, true, false);
                ducks[0] = duck6;
                ducks[1] = duck7;
                ducks[2] = duck8;
                this.level = 4;
                break;
            case 5:
                numberOfDucks = 4;
                ducks = new Duck[numberOfDucks];
                Duck duck9 = new Duck("blue", 10, 20, 40, true, true, true);
                Duck duck10 = new Duck("red", -150, -200, 40, false, false, true);
                Duck duck11 = new Duck("black", 50, 20, 40, false, true, false);
                Duck duck12 = new Duck("blue", 70, 20, 40, true, true, true);
                ducks[0] = duck9;
                ducks[1] = duck10;
                ducks[2] = duck11;
                ducks[3] = duck12;
                this.level = 5;
                break;
            case 6:
                numberOfDucks = 5;
                ducks = new Duck[numberOfDucks];
                Duck duck13 = new Duck("blue", 10, 20, 40, true, true, true);
                Duck duck14 = new Duck("red", -150, -200, 40, false, false, true);
                Duck duck15 = new Duck("black", 50, 20, 40, false, true, false);
                Duck duck16 = new Duck("blue", 70, -30, 40, true, true, true);
                Duck duck17 = new Duck("red", 90, 20, 40, false, true, true);
                ducks[0] = duck13;
                ducks[1] = duck14;
                ducks[2] = duck15;
                ducks[3] = duck16;
                ducks[4] = duck17;
                this.level = 6;
                break;
        }
        numberOfBullets = numberOfDucks * 3;
        return ducks;
    }
    
    public void updateSceneForLevel(Stage primaryStage) {
        levelCompleted = false;
        // Clear the existing scene
        StackPane root = (StackPane) scene.getRoot();
        root.getChildren().clear();
    
        // Add the new level's elements to the scene
        root.getChildren().add(backgroundImage);
        for (Duck duck : ducks) {
            root.getChildren().add(duck.getCurrentImage());
            duck.getCurrentImage().setTranslateX(duck.getX());
            duck.getCurrentImage().setTranslateY(duck.getY());
        }
        root.getChildren().add(foregroundImage);

        // Add the level text
        Text levelText = new Text("Level " + level + "/6");
        levelText.setFont(Font.font("Verdana", FontWeight.BOLD, 6 * SCALE));
        levelText.setFill(Color.ORANGE);
        levelText.setTranslateX(0);
        levelText.setTranslateY(-110 * SCALE);
        root.getChildren().add(levelText);

        // Add the bullets remaining text
        Text bulletsText = new Text("Ammo Left: " + numberOfBullets);
        bulletsText.setFont(Font.font("Verdana", FontWeight.BOLD, 6 * SCALE));
        bulletsText.setFill(Color.ORANGE);
        bulletsText.setTranslateX(100 * SCALE);
        bulletsText.setTranslateY(-110 * SCALE);
        root.getChildren().add(bulletsText);

        scene.setOnMouseClicked(event -> {
            if (!levelCompleted) {
                if (numberOfBullets > 0) {
                    numberOfBullets--;
                    bulletsText.setText("Ammo Left: " + numberOfBullets);
                    Media sound = new Media(new File("src/assets/effects/Gunshot.mp3").toURI().toString());
                    MediaPlayer mediaPlayer = new MediaPlayer(sound);
                    mediaPlayer.setVolume(VOLUME);
                    mediaPlayer.play();
                    int ducksKilled = 0;

                    for (Duck duck : ducks) {
                        if (duck.isAlive && duck.getCurrentImage().getBoundsInParent().contains(event.getX(), event.getY())) {
                            duck.die();
                        }
                        if (!duck.isAlive) {
                            ducksKilled++;
                        }
                        if (ducksKilled == numberOfDucks) {
                            if (level != 6) {
                                Media levelup = new Media(new File("src/assets/effects/LevelCompleted.mp3").toURI().toString());
                                MediaPlayer levelupplayer = new MediaPlayer(levelup);
                                levelupplayer.setVolume(VOLUME);
                                levelupplayer.play();
                                Text levelCompletedText = new Text("YOU WIN!");
                                levelCompletedText.setFont(Font.font("Verdana", FontWeight.BOLD, 15 * SCALE));
                                levelCompletedText.setFill(Color.ORANGE);
                                levelCompletedText.setTranslateX(0);
                                levelCompletedText.setTranslateY(0);
                                Text levelCompletedText2 = new Text("Press ENTER to play next level");
                                levelCompletedText2.setFont(Font.font("Verdana", FontWeight.BOLD, 10 * SCALE));
                                levelCompletedText2.setFill(Color.ORANGE);
                                levelCompletedText2.setTranslateX(0);
                                levelCompletedText2.setTranslateY(20 * SCALE);
                                // Make levelcompletedtext2 flashing
                                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.5), evt -> {
                                    if (levelCompletedText2.getFill() == Color.ORANGE) {
                                        levelCompletedText2.setFill(Color.TRANSPARENT);
                                    } else {
                                        levelCompletedText2.setFill(Color.ORANGE);
                                    }
                                }));
                                timeline.setCycleCount(Timeline.INDEFINITE);
                                timeline.play();
                                root.getChildren().add(levelCompletedText);
                                root.getChildren().add(levelCompletedText2);
                                levelCompleted = true;
                                scene.setOnKeyPressed(event2 -> {
                                    if (event2.getCode() == KeyCode.ENTER) {
                                        level += 1;
                                        levelupplayer.stop();
                                        root.getChildren().clear();
                                        initLevel(level);
                                        updateSceneForLevel(primaryStage);
                                    }
                                });
                            } else {
                                Media gamecompleted = new Media(new File("src/assets/effects/GameCompleted.mp3").toURI().toString());
                                MediaPlayer gamecompletedplayer = new MediaPlayer(gamecompleted);
                                gamecompletedplayer.setVolume(VOLUME);
                                gamecompletedplayer.play();
                                Text gameCompletedText = new Text("You have completed the game!");
                                gameCompletedText.setFont(Font.font("Verdana", FontWeight.BOLD, 13 * SCALE));
                                gameCompletedText.setFill(Color.ORANGE);
                                gameCompletedText.setTranslateX(0);
                                gameCompletedText.setTranslateY(0);
                                Text gameCompletedText2 = new Text("Press ENTER to play again");
                                gameCompletedText2.setFont(Font.font("Verdana", FontWeight.BOLD, 10 * SCALE));
                                gameCompletedText2.setFill(Color.ORANGE);
                                gameCompletedText2.setTranslateX(0);
                                gameCompletedText2.setTranslateY(20 * SCALE);
                                Text gameCompletedText3 = new Text("Press ESC to exit");
                                gameCompletedText3.setFont(Font.font("Verdana", FontWeight.BOLD, 10 * SCALE));
                                gameCompletedText3.setFill(Color.ORANGE);
                                gameCompletedText3.setTranslateX(0);
                                gameCompletedText3.setTranslateY(40 * SCALE);
                                // Make gamecompletedtext2 and gamecompletedtext3 flashing
                                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.5), evt -> {
                                    if (gameCompletedText2.getFill() == Color.ORANGE) {
                                        gameCompletedText2.setFill(Color.TRANSPARENT);
                                        gameCompletedText3.setFill(Color.TRANSPARENT);
                                    } else {
                                        gameCompletedText2.setFill(Color.ORANGE);
                                        gameCompletedText3.setFill(Color.ORANGE);
                                    }
                                }));
                                timeline.setCycleCount(Timeline.INDEFINITE);
                                timeline.play();
                                root.getChildren().add(gameCompletedText);
                                root.getChildren().add(gameCompletedText2);
                                root.getChildren().add(gameCompletedText3);
                                levelCompleted = true;
                                scene.setOnKeyPressed(event2 -> {
                                    if (event2.getCode() == KeyCode.ENTER) {
                                        gamecompletedplayer.stop();
                                        root.getChildren().clear();
                                        initLevel(1);
                                        updateSceneForLevel(primaryStage);
                                    } else if (event2.getCode() == KeyCode.ESCAPE) {
                                        // Return to title screen
                                        gamecompletedplayer.stop();
                                        root.getChildren().clear();
                                        TitleScene titleScene = new TitleScene(primaryStage);
                                        primaryStage.setScene(titleScene.getScene());
                                    }
                                });
                            }
                        }
                    }

                    if (numberOfBullets == 0) {
                        int ducksAlive = 0;
                        for (Duck duck : ducks) {
                            if (duck.isAlive) {
                                ducksAlive++;
                            }
                        }
                        if (ducksAlive > 0) {
                            Media gameover = new Media(new File("src/assets/effects/GameOver.mp3").toURI().toString());
                            MediaPlayer gameoverplayer = new MediaPlayer(gameover);
                            gameoverplayer.setVolume(VOLUME);
                            gameoverplayer.play();
                            Text gameOverText = new Text("GAME OVER!");
                            gameOverText.setFont(Font.font("Verdana", FontWeight.BOLD, 15 * SCALE));
                            gameOverText.setFill(Color.ORANGE);
                            gameOverText.setTranslateX(0);
                            gameOverText.setTranslateY(0);
                            Text gameOverText2 = new Text("Press ENTER to play again");
                            gameOverText2.setFont(Font.font("Verdana", FontWeight.BOLD, 15 * SCALE));
                            gameOverText2.setFill(Color.ORANGE);
                            gameOverText2.setTranslateX(0);
                            gameOverText2.setTranslateY(20 * SCALE);
                            Text gameOverText3 = new Text("Press ESC to exit");
                            gameOverText3.setFont(Font.font("Verdana", FontWeight.BOLD, 15 * SCALE));
                            gameOverText3.setFill(Color.ORANGE);
                            gameOverText3.setTranslateX(0);
                            gameOverText3.setTranslateY(40 * SCALE);
                            // Make gamovertext2 and 3 flashing
                            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.5), evt -> {
                                gameOverText2.setVisible(!gameOverText2.isVisible());
                                gameOverText3.setVisible(!gameOverText3.isVisible());
                            }));
                            timeline.setCycleCount(Timeline.INDEFINITE);
                            timeline.play();
                            root.getChildren().add(gameOverText);
                            root.getChildren().add(gameOverText2);
                            root.getChildren().add(gameOverText3);

                            // Add the event handler for the ENTER and ESC keys
                            scene.setOnKeyPressed(event2 -> {
                                if (event2.getCode() == KeyCode.ENTER) {
                                    root.getChildren().clear();
                                    initLevel(1);
                                    updateSceneForLevel(primaryStage);
                                } else if (event2.getCode() == KeyCode.ESCAPE) {
                                    // Go to title screen
                                    root.getChildren().clear();
                                    TitleScene titleScene = new TitleScene(primaryStage);
                                    primaryStage.setScene(titleScene.getScene());
                                }
                            });
                        }
                    }
                }
            
            }
        });
        
        
    }
    
}