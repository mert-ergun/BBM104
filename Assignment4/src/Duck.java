import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Duck {
    private ImageView imageView;
    private Timeline animation;
    private boolean isAlive;
    private boolean isFlyingRight;
    private boolean isFalling;
    private double speed;
    private double startX;
    private double startY;
    private double endX;
    private double endY;
    private double currentX;
    private double currentY;
    private double width;
    private double height;
    private int frameCount;
    private int currentFrame;
    private int columns;
    private int rows;
    private Image image;
    private Image[] crossFlyingImages;
    private Image[] stillFlyingImages;
    private Image shotImage;
    private Image fallingImage;

    public Duck(double startX, double startY, double endX, double endY, double speed, String color) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.speed = speed;
        this.isAlive = true;
        this.isFlyingRight = true;
        this.isFalling = false;
        this.currentX = startX;
        this.currentY = startY;
        this.crossFlyingImages = new Image[] {
            new Image("assets/duck_" + color + "/1.png"),
            new Image("assets/duck_" + color + "/2.png"),
            new Image("assets/duck_" + color + "/3.png")
        };
        this.stillFlyingImages = new Image[] {
            new Image("assets/duck_" + color + "/4.png"),
            new Image("assets/duck_" + color + "/5.png"),
            new Image("assets/duck_" + color + "/6.png")
        };
        this.shotImage = new Image("assets/duck_" + color + "/7.png");
        this.fallingImage = new Image("assets/duck_" + color + "/8.png");
        this.width = image.getWidth() / 3;
        this.height = image.getHeight() / 4;
        this.imageView = new ImageView(image);
        this.imageView.setViewport(new Rectangle2D(0, 0, width, height));
        this.columns = 3;
        this.rows = 4;
        this.frameCount = columns * rows;
        this.currentFrame = 0;
        this.animation = new Timeline(new KeyFrame(Duration.millis(100), event -> {
            if (isAlive) {
                if (isFlyingRight) {
                    currentX += speed;
                    if (currentX >= endX) {
                        isFlyingRight = false;
                    }
                } else {
                    currentX -= speed;
                    if (currentX <= startX) {
                        isFlyingRight = true;
                    }
                }
                if (isFalling) {
                    imageView.setImage(fallingImage);
                } else if (currentY == startY) {
                    int index = (int) (System.currentTimeMillis() / 300 % stillFlyingImages.length);
                    imageView.setImage(stillFlyingImages[index]);
                } else if (isFlyingRight) {
                    int index = (int) (System.currentTimeMillis() / 300 % crossFlyingImages.length);
                    imageView.setImage(crossFlyingImages[index]);
                } else {
                    int index = (int) (System.currentTimeMillis() / 300 % crossFlyingImages.length);
                    Image flippedImage = new Image("assets/duck_" + color + "/2_flipped.png");
                    imageView.setImage(flipImage(crossFlyingImages[index], flippedImage));
                }
                currentFrame = (currentFrame + 1) % frameCount;
                int x = (currentFrame % columns) * (int) width;
                int y = (currentFrame / columns) * (int) height;
                imageView.setViewport(new Rectangle2D(x, y, width, height));
                imageView.setX(currentX);
                imageView.setY(currentY);
            } else {
                imageView.setImage(shotImage);
                animation.stop();
            }
        }));
        animation.setCycleCount(Animation.INDEFINITE);
        animation.play();
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setFalling(boolean falling) {
        isFalling = falling;
    }

    public boolean isFalling() {
        return isFalling;
    }

    public double getCurrentX() {
        return currentX;
    }

    public double getCurrentY() {
        return currentY;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public void stopAnimation() {
        animation.stop();
    }

    private Image flipImage(Image image, Image flippedImage) {
        if (image == null) {
            return null;
        }
        ImageView imageView = new ImageView(image);
        imageView.setScaleX(-1);
        Image flipped = imageView.snapshot(null, null);
        return flippedImage == null ? flipped : flipped;
    }
}