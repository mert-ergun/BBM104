import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Duck {
    private final int SCALE = DuckHunt.SCALE;
    private String color;
    private double startX;
    private double startY;
    private double x;
    private double y;
    private double speed;
    private Image[] crossFlyingImages;
    private Image[] stillFlyingImages;
    private Image shotImage;
    private Image fallingImage;
    private ImageView currentImage;
    private boolean isAlive;
    private boolean isFlyingRight;
    private boolean isFlyingCross;
    private boolean isFlyingUp;
    private boolean isFalling;

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

        this.color = color;
        this.startX = startX;
        this.startY = startY;
        this.x = startX;
        this.y = startY;
        this.isFlyingRight = isFlyingRight;
        this.speed = isFlyingRight ? speed : -speed;
        this.isAlive = true;
        this.isFalling = false;
        this.isFlyingCross = isFlyingCross;
        this.isFlyingUp = isFlyingUp;
        this.currentImage = new ImageView();
        this.currentImage.setImage(stillFlyingImages[0]);
        this.scale();
        this.fly();
    }

    public void fly() {
        if (!isAlive) {
            return;
        }
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);

        if (isFlyingRight && isFlyingCross && isFlyingUp) {
            for (int i = 0; i < crossFlyingImages.length; i++) {
                Image image = crossFlyingImages[i];
                KeyFrame keyFrame = new KeyFrame(Duration.seconds(i * 0.2 + 0.2), e -> {
                    currentImage.setImage(image);
                    currentImage.setScaleY(1); // Reset scaleY to normal
                    currentImage.setScaleX(1); // Reset scaleX to normal
                    this.scale();
                    x += speed;
                    y -= speed;
                    if (isColliding()) {
                        // Reverse direction and move back a little bit to simulate a bounce effect
                        timeline.stop();
                        fly();
                    }
                    currentImage.setTranslateX(x);
                    currentImage.setTranslateY(y);
                });
                timeline.getKeyFrames().add(keyFrame);
            }
        } else if (isFlyingRight && isFlyingCross && !isFlyingUp) {
            for (int i = 0; i < crossFlyingImages.length; i++) {
                Image image = crossFlyingImages[i];
                KeyFrame keyFrame = new KeyFrame(Duration.seconds(i * 0.2 + 0.2), e -> {
                    currentImage.setImage(image);
                    currentImage.setScaleY(-1); 
                    currentImage.setScaleX(1);
                    this.scale();
                    x += speed;
                    y += speed;
                    if (isColliding()) {
                        // Reverse direction and move back a little bit to simulate a bounce effect
                        timeline.stop();
                        fly();
                    }
                    currentImage.setTranslateX(x);
                    currentImage.setTranslateY(y);
                });
                timeline.getKeyFrames().add(keyFrame);
            }
        } else if (isFlyingRight && !isFlyingCross) {
            for (int i = 0; i < stillFlyingImages.length; i++) {
                Image image = stillFlyingImages[i];
                KeyFrame keyFrame = new KeyFrame(Duration.seconds(i * 0.2 + 0.2), e -> {
                    currentImage.setImage(image);
                    currentImage.setScaleY(1); // Reset scaleY to normal
                    currentImage.setScaleX(1); // Reset scaleX to normal
                    this.scale();
                    x += speed;
                    if (isColliding()) {
                        // Reverse direction and move back a little bit to simulate a bounce effect
                        timeline.stop();
                        fly();
                    }
                    currentImage.setTranslateX(x);
                });
                timeline.getKeyFrames().add(keyFrame);
            }
        } else if (!isFlyingRight && isFlyingCross && isFlyingUp) {
            for (int i = crossFlyingImages.length - 1; i >= 0; i--) {
                Image image = crossFlyingImages[i];
                KeyFrame keyFrame = new KeyFrame(Duration.seconds((crossFlyingImages.length - i - 1) * 0.2 + 0.2), e -> {
                    currentImage.setImage(image);
                    currentImage.setScaleY(1); // Reset scaleY to normal
                    currentImage.setScaleX(-1); // Mirror the image horizontally
                    this.scale();
                    x += speed;
                    y += speed;
                    if (isColliding()) {
                        // Reverse direction and move back a little bit to simulate a bounce effect
                        timeline.stop();
                        fly();
                    }
                    currentImage.setTranslateX(x);
                    currentImage.setTranslateY(y);
                });
                timeline.getKeyFrames().add(keyFrame);
            }
        } else if (!isFlyingRight && isFlyingCross && !isFlyingUp) {
            for (int i = crossFlyingImages.length - 1; i >= 0; i--) {
                Image image = crossFlyingImages[i];
                KeyFrame keyFrame = new KeyFrame(Duration.seconds((crossFlyingImages.length - i - 1) * 0.2 + 0.2), e -> {
                    currentImage.setImage(image);
                    currentImage.setScaleX(-1); // Mirror the image horizontally
                    currentImage.setScaleY(-1); // Reset scaleY to normal
                    this.scale();
                    x += speed;
                    y -= speed;
                    if (isColliding()) {
                        // Reverse direction and move back a little bit to simulate a bounce effect
                        timeline.stop();
                        fly();
                    }
                    currentImage.setTranslateX(x);
                    currentImage.setTranslateY(y);
                });
                timeline.getKeyFrames().add(keyFrame);
            }
        } else if (!isFlyingRight && !isFlyingCross) {
            for (int i = stillFlyingImages.length - 1; i >= 0; i--) {
                Image image = stillFlyingImages[i];
                KeyFrame keyFrame = new KeyFrame(Duration.seconds((stillFlyingImages.length - i - 1) * 0.2 + 0.2), e -> {
                    currentImage.setImage(image);
                    currentImage.setScaleY(1); // Reset scaleY to normal
                    currentImage.setScaleX(-1); // Mirror the image horizontally
                    this.scale();
                    x += speed;
                    if (isColliding()) {
                        // Reverse direction and move back a little bit to simulate a bounce effect
                        timeline.stop();
                        fly();
                    }
                    currentImage.setTranslateX(x);
                });
                timeline.getKeyFrames().add(keyFrame);
            }
        }

        timeline.play();
    }

    public boolean isColliding() {
        Bounds duckBounds = currentImage.getBoundsInLocal();
        duckBounds = currentImage.localToScene(duckBounds);
        Bounds sceneBounds = DuckHunt.getTitleScene().getScene().getRoot().getBoundsInParent();

        boolean isColliding = false;

        if (duckBounds.getMaxY() > sceneBounds.getMaxY()) {
            if (isFlyingUp) {
                // Quit from method
                return false;
            }
            isColliding = true;
            isFlyingUp = true;
            y -= currentImage.getFitHeight() / 2;
        } else if (duckBounds.getMinY() < sceneBounds.getMinY()) {
            if (!isFlyingUp) {
                // Quit from method
                return false;
            }
            isColliding = true;
            isFlyingUp = false;
            y += currentImage.getFitHeight() / 2;
        }

        if (duckBounds.getMaxX() > sceneBounds.getMaxX()) {
            if (!isFlyingRight) {
                // Quit from method
                return false;
            }
            isColliding = true;
            isFlyingRight = false;
            speed = -speed;
            x -= currentImage.getFitWidth() / 2;
        } else if (duckBounds.getMinX() < sceneBounds.getMinX()) {
            if (isFlyingRight) {
                // Quit from method
                return false;
            }
            isColliding = true;
            isFlyingRight = true;
            speed = -speed;
            x += currentImage.getFitWidth() / 2;
        }

        return isColliding;
    }

    public void fall() {
        this.isFalling = true;
        this.isAlive = false;
        if (isFlyingRight) {
            currentImage.setImage(fallingImage);
            this.scale();
            
        } else {
            currentImage.setImage(fallingImage);
            this.scale();
            currentImage.setScaleX(-1);
        }
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.2), e -> {
            y += 10;
            if (y > DuckHunt.getTitleScene().getScene().getRoot().getBoundsInParent().getMaxY()) {
                timeline.stop();
                DuckHunt.getTitleScene().getScene().getRoot().getChildrenUnmodifiable().remove(currentImage);
            }
        });
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }

    public void die() {
        this.isAlive = false;
        this.speed = 0;
        if (isFlyingRight) {
            currentImage.setImage(shotImage);
            this.scale();
        } else {
            currentImage.setImage(shotImage);
            this.scale();
            currentImage.setScaleX(-1);
        }
        this.fall();
    }

    public ImageView getCurrentImage() {
        return currentImage;
    }

    public void scale() {
        currentImage.setFitWidth(currentImage.getImage().getWidth() * SCALE);
        currentImage.setFitHeight(currentImage.getImage().getHeight() * SCALE);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
