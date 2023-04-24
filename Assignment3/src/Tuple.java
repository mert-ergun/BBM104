/**
 * Tuple class to store two values of different types
 * @param <X> X Type of first value
 * @param <Y> Y Type of second value
 */
public class Tuple<X, Y>{
    public X x;  // first value
    public Y y;  // second value

    /**
     * Constructor for the class.
     * @param x - first value
     * @param y - second value
     */
    public Tuple(X x, Y y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Get the first value
     * @return first value
     */
    public X getX() {
        return x;
    }

    /**
     * Get the second value
     * @return second value
     */
    public Y getY() {
        return y;
    }

    /**
     * Set the first value
     * @param x - first value
     */
    public void setX(X x) {
        this.x = x;
    }

    /**
     * Set the second value
     * @param y - second value
     */
    public void setY(Y y) {
        this.y = y;
    }
}
