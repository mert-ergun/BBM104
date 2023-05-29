import java.io.File;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

/**
 * The Duck class represents a duck in the game. 
 * It sets up the duck's graphics, text, and sound effects, and handles user input.
 * It also handles the duck's movement and animation.
 * It also handles the duck's collision with the edges of the screen.
 */
public class Duck {
    private final int SCALE = DuckHunt.SCALE;
    private final double VOLUME = DuckHunt.VOLUME;
    private double x;  // The current x-coordinate of the duck
    private double y;  // The current y-coordinate of the duck 
    private double speed;  // The speed of the duck
    private Image[] crossFlyingImages;  // The images of the duck flying cross
    private Image[] stillFlyingImages;   // The images of the duck flying still
    private Image shotImage;  // The image of the duck when it is shot
    private Image fallingImage;  // The image of the duck when it is falling
    private ImageView currentImage;  // The current image of the duck
    public boolean isAlive;  // A boolean to check if the duck is alive
    private boolean isFlyingRight;  // A boolean to check if the duck is flying right
    private boolean isFlyingCross;  // A boolean to check if the duck is flying cross
    private boolean isFlyingUp;  // A boolean to check if the duck is flying up
    private Timeline timeline;  // The timeline of the duck's fly animation
    private MediaPlayer fallSound;  // The sound effect of the duck falling

    /**
     * Creates a duck with the given color, starting x-coordinate, starting y-coordinate, speed, and direction.
     * @param color the color of the duck
     * @param startX the starting x-coordinate of the duck
     * @param startY the starting y-coordinate of the duck
     * @param speed the speed of the duck
     * @param isFlyingRight the direction of the duck
     * @param isFlyingCross a boolean to check if the duck is flying cross
     * @param isFlyingUp a boolean to check if the duck is flying up
     */
    public Duck(String color, double startX, double startY, double speed, boolean isFlyingRight, boolean isFlyingCross, boolean isFlyingUp) {
        switch (color) {
            case "red":
                this.crossFlyingImages = new Image[] {
                    new Image("assets/duck_red/1.png"),
                    new Image("assets/duck_red/2.png"),
                    new Image("assets/duck_red/3.png")
                };
                this.stillFlyingImages = new Image[] {
                    new Image("assets/duck_red/4.png"),
                    new Image("assets/duck_red/5.png"),
                    new Image("assets/duck_red/6.png")
                };
                this.shotImage = new Image("assets/duck_red/7.png");
                this.fallingImage = new Image("assets/duck_red/8.png");
                break;
            case "blue":
                this.crossFlyingImages = new Image[] {
                    new Image("assets/duck_blue/1.png"),
                    new Image("assets/duck_blue/2.png"),
                    new Image("assets/duck_blue/3.png")
                };
                this.stillFlyingImages = new Image[] {
                    new Image("assets/duck_blue/4.png"),
                    new Image("assets/duck_blue/5.png"),
                    new Image("assets/duck_blue/6.png")
                };
                this.shotImage = new Image("assets/duck_blue/7.png");
                this.fallingImage = new Image("assets/duck_blue/8.png");
                break;
            case "black":
                this.crossFlyingImages = new Image[] {
                    new Image("assets/duck_black/1.png"),
                    new Image("assets/duck_black/2.png"),
                    new Image("assets/duck_black/3.png")
                };
                this.stillFlyingImages = new Image[] {
                    new Image("assets/duck_black/4.png"),
                    new Image("assets/duck_black/5.png"),
                    new Image("assets/duck_black/6.png")
                };
                this.shotImage = new Image("assets/duck_black/7.png");
                this.fallingImage = new Image("assets/duck_black/8.png");
                break;
        }
        this.fallSound = new MediaPlayer(new Media(new File("assets/effects/DuckFalls.mp3").toURI().toString()));

        this.x = startX;
        this.y = startY;
        this.isFlyingRight = isFlyingRight;
        this.speed = isFlyingRight ? speed * SCALE/3 : -speed * SCALE/3;
        this.isAlive = true;
        this.isFlyingCross = isFlyingCross;
        this.isFlyingUp = isFlyingUp;
        this.currentImage = new ImageView();
        this.currentImage.setImage(stillFlyingImages[0]);
        this.scale();  // Scale the duck
        this.fly();
    }

    /**
     * Fly the duck.
     * The duck will fly in a cross pattern if isFlyingCross is true.
     * The duck will fly in a straight line if isFlyingCross is false.
     * The duck will fly up if isFlyingUp is true.
     * The duck will fly down if isFlyingUp is false.
     * The duck will fly right if isFlyingRight is true.
     * The duck will fly left if isFlyingRight is false.
     * The duck will stop flying if it is shot.
     */
    public void fly() {
        if (!isAlive) {  // If the duck is not alive, return
            return;
        }
        timeline = new Timeline();  
        timeline.setCycleCount(Timeline.INDEFINITE);  

        // If the duck is flying right, cross, and up
        if (isFlyingRight && isFlyingCross && isFlyingUp) {
            for (int i = 0; i < crossFlyingImages.length; i++) {  // Loop through the cross flying images
                Image image = crossFlyingImages[i];
                KeyFrame keyFrame = new KeyFrame(Duration.seconds(i * 0.2 + 0.2), e -> {  // Change the image every 0.2 seconds
                    currentImage.setImage(image);
                    currentImage.setScaleY(1); // Reset scaleY to normal
                    currentImage.setScaleX(1); // Reset scaleX to normal
                    this.scale();
                    x += speed;
                    y -= speed;
                    currentImage.setTranslateX(x);
                    currentImage.setTranslateY(y);
                    if (isColliding()) {
                        timeline.stop();  // Stop the timeline
                        fly();  // Fly again
                    }
                    // Move the duck
                    currentImage.setTranslateX(x);
                    currentImage.setTranslateY(y);
                });
                timeline.getKeyFrames().add(keyFrame);
            }
        }
        // If the duck is flying right, cross and down 
        else if (isFlyingRight && isFlyingCross && !isFlyingUp) {
            for (int i = 0; i < crossFlyingImages.length; i++) {  // Loop through the cross flying images
                Image image = crossFlyingImages[i];
                KeyFrame keyFrame = new KeyFrame(Duration.seconds(i * 0.2 + 0.2), e -> {
                    currentImage.setImage(image);
                    currentImage.setScaleY(-1);  // Flip the image vertically to make the duck fly down
                    currentImage.setScaleX(1);
                    this.scale();
                    x += speed;
                    y += speed;
                    currentImage.setTranslateX(x);
                    currentImage.setTranslateY(y);
                    if (isColliding()) {
                        timeline.stop();
                        fly();
                    }
                    currentImage.setTranslateX(x);
                    currentImage.setTranslateY(y);
                });
                timeline.getKeyFrames().add(keyFrame);
            }
        }
        // If the duck is flying right and straight 
        else if (isFlyingRight && !isFlyingCross) {
            for (int i = 0; i < stillFlyingImages.length; i++) {
                Image image = stillFlyingImages[i];
                KeyFrame keyFrame = new KeyFrame(Duration.seconds(i * 0.2 + 0.2), e -> {
                    currentImage.setImage(image);
                    currentImage.setScaleY(1); // Reset scaleY to normal
                    currentImage.setScaleX(1); // Reset scaleX to normal
                    this.scale();
                    x += speed;
                    currentImage.setTranslateX(x);
                    if (isColliding()) {
                        timeline.stop();
                        fly();
                    }
                    currentImage.setTranslateX(x);
                });
                timeline.getKeyFrames().add(keyFrame);
            }
        }
        // If the duck is flying left, cross and up
        else if (!isFlyingRight && isFlyingCross && isFlyingUp) {
            for (int i = crossFlyingImages.length - 1; i >= 0; i--) {
                Image image = crossFlyingImages[i];
                KeyFrame keyFrame = new KeyFrame(Duration.seconds((crossFlyingImages.length - i - 1) * 0.2 + 0.2), e -> {
                    currentImage.setImage(image);
                    currentImage.setScaleY(1); // Reset scaleY to normal
                    currentImage.setScaleX(-1); // Mirror the image horizontally to make the duck fly left
                    this.scale();
                    x += speed;
                    y += speed;
                    currentImage.setTranslateX(x);
                    currentImage.setTranslateY(y);
                    if (isColliding()) {
                        timeline.stop();
                        fly();
                    }
                    currentImage.setTranslateX(x);
                    currentImage.setTranslateY(y);
                });
                timeline.getKeyFrames().add(keyFrame);
            }
        }
        // If the duck is flying left, cross and down 
        else if (!isFlyingRight && isFlyingCross && !isFlyingUp) {
            for (int i = crossFlyingImages.length - 1; i >= 0; i--) {
                Image image = crossFlyingImages[i];
                KeyFrame keyFrame = new KeyFrame(Duration.seconds((crossFlyingImages.length - i - 1) * 0.2 + 0.2), e -> {
                    currentImage.setImage(image);
                    currentImage.setScaleX(-1); // Mirror the image horizontally to make the duck fly left
                    currentImage.setScaleY(-1); // Flip the image vertically to make the duck fly down
                    this.scale();
                    x += speed;
                    y -= speed;
                    currentImage.setTranslateX(x);
                    currentImage.setTranslateY(y);
                    if (isColliding()) {
                        timeline.stop();
                        fly();
                    }
                    currentImage.setTranslateX(x);
                    currentImage.setTranslateY(y);
                });
                timeline.getKeyFrames().add(keyFrame);
            }
        }
        // If the duck is flying left and straight 
        else if (!isFlyingRight && !isFlyingCross) {
            for (int i = stillFlyingImages.length - 1; i >= 0; i--) {
                Image image = stillFlyingImages[i];
                KeyFrame keyFrame = new KeyFrame(Duration.seconds((stillFlyingImages.length - i - 1) * 0.2 + 0.2), e -> {
                    currentImage.setImage(image);
                    currentImage.setScaleY(1); // Reset scaleY to normal
                    currentImage.setScaleX(-1); // Mirror the image horizontally to make the duck fly left
                    this.scale();
                    x += speed;
                    currentImage.setTranslateX(x);
                    if (isColliding()) {
                        timeline.stop();
                        fly();
                    }
                    currentImage.setTranslateX(x);
                });
                timeline.getKeyFrames().add(keyFrame);
            }
        }

        // Play the timeline, which will play the animation
        timeline.play();
    }

    /**
     * Checks if the duck is colliding with the edges of the screen.
     * If it is, it will change the direction of the duck.
     * @return true if the duck is colliding with the edges of the screen, false otherwise
     */
    public boolean isColliding() {
        Bounds duckBounds = currentImage.getBoundsInLocal();  // Get the bounds of the duck
        duckBounds = currentImage.localToScene(duckBounds);  // Convert the bounds to the scene
        Bounds sceneBounds = DuckHunt.getTitleScene().getScene().getRoot().getBoundsInParent();  // Get the bounds of the scene

        boolean isColliding = false;  // Whether the duck is colliding with the edges of the screen

        // If the duck is flying down, it can collide with the bottom of the screen
        if (duckBounds.getMaxY() > sceneBounds.getMaxY()) {
            if (isFlyingUp) {  // If the duck is flying up, it can't collide with the bottom of the screen
                // Quit from method
                return false;
            }
            isColliding = true; 
            isFlyingUp = true;  // Change the direction of the duck
            y -= Math.abs(speed);  // Move the duck up a bit so it doesn't get stuck in the bottom of the screen
            currentImage.setScaleY(1);
        } 
        // If the duck is flying up, it can collide with the top of the screen
        else if (duckBounds.getMinY() < sceneBounds.getMinY()) {
            if (!isFlyingUp) {  // If the duck is flying down, it can't collide with the top of the screen
                // Quit from method
                return false;
            }
            isColliding = true;
            isFlyingUp = false;
            y += Math.abs(speed);
            currentImage.setScaleY(-1);
        }

        // If the duck is flying right, it can collide with the right side of the screen
        if (duckBounds.getMaxX() > sceneBounds.getMaxX()) {
            if (!isFlyingRight) {
                // Quit from method
                return false;
            }
            isColliding = true;
            isFlyingRight = false;
            speed = -speed;
            x -= Math.abs(speed);
            currentImage.setScaleX(-1);
        }
        // If the duck is flying left, it can collide with the left side of the screen 
        else if (duckBounds.getMinX() < sceneBounds.getMinX()) {
            if (isFlyingRight) {
                // Quit from method
                return false;
            }
            isColliding = true;
            isFlyingRight = true;
            speed = -speed;
            x += Math.abs(speed);
            currentImage.setScaleX(1);
        }

        return isColliding;
    }

    /**
     * Makes the duck fall down.
     * The duck will fall down until it reaches the bottom of the screen.
     */
    public void fall() {
        this.isAlive = false;  // The duck is dead
        if (isFlyingRight) {  // If the duck is flying right, it will fall while facing right
            currentImage.setImage(fallingImage);
            this.scale();
            
        } else {  // If the duck is flying left, it will fall while facing left
            currentImage.setImage(fallingImage);
            this.scale();
            currentImage.setScaleX(-1);
        }
        if (!isFlyingUp) {  // Don't flip the image if the duck is flying up or down
            currentImage.setScaleY(1); // Reset scaleY to normal 
        }
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.2), e -> {
            y += 20 * SCALE;  // Move the duck down
            currentImage.setTranslateY(y);
        });
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
        fallSound.setVolume(VOLUME);
        fallSound.play();
    }

    /**
     * Makes the duck die.
     * It will stop moving and fall down.
     * This method will call the fall() method.
     */
    public void die() {
        this.isAlive = false;  // The duck is dead
        this.speed = 0;  // The duck will stop moving
        timeline.stop();
        if (isFlyingRight) {  // If the duck is flying right, it will be shot while facing right
            currentImage.setImage(shotImage);
            this.scale();
        } else {  // If the duck is flying left, it will be shot while facing left
            currentImage.setImage(shotImage);
            this.scale();
            currentImage.setScaleX(-1);
        }
        Timeline timeline = new Timeline();
        timeline.setCycleCount(1);  
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.5), e -> {
            this.fall();
        });
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();    
    }

    /**
     * Returns the current imageview of duck.
     * @return current imageview
     */
    public ImageView getCurrentImage() {
        return currentImage;
    }

    /**
     * Scale the duck according to SCALE variable that comes from DuckHunt class.
     */
    public void scale() {
        currentImage.setFitWidth(currentImage.getImage().getWidth() * SCALE);
        currentImage.setFitHeight(currentImage.getImage().getHeight() * SCALE);
    }
    
    /**
     * Stops the fallsound.
     * Called when player advances to next level.
     */
    public void stopFallSound() {
        fallSound.stop();
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

}
