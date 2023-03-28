public class Point {
	private double x,y;

	public Point()// creates a point at (0,0)
    {
      this.x = 0;
	  this.y = 0;
    }

	public Point(double x, double y) // creates a point at (x,y)
	{	
		this.x = x;
		this.y = y;
	}
	public double getX() {
		return this.x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return this.y;
	}

	public void setY(double y) {
		
		this.y = y;
	}
	
	// returns quadrant number (1,2,3,4) where a point is on the coordinate plane
	public int quadrant() {
		if (this.x > 0 && this.y > 0) {
			return 1;
		} else if (this.x > 0 && this.y < 0) {
			return 4;
		} else if (this.x < 0 && this.y > 0) {
			return 2;
		} else {
			return 3;
		}
	}

	//returns double value which is distance of between two point objects
  	public static double distance(Point p1, Point p2) { 
		return Math.sqrt(Math.pow(p1.x - p2.x) + Math.pow(p1.y - p2.y));
  	}

	// checks if two point objects hold equivalent data and if equals returns true, else returns false
	boolean equals(Point p1, Point p2) {
      return ((p1.x == p2.x) && (p1.y == p2.y));
	}
	
	//prints points such as (x,y)
	public String toString() { 
		return ("(" + this.x + "," + this.y + ")");
	}
}
