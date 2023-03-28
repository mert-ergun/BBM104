
public class Point {
	private double x;
	private double y;
	public Point()// creates a point at (0,0)
    {
      this.x=0;
      this.y=0;
    }

	public Point(double x, double y) {// creates a point at (x,y)
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	
	
	public int quadrant()
	{
		if(x > 0 && y > 0) return 1;
		else if(x < 0 && y > 0) return 2;
		else if(x < 0 && y < 0) return 3;
		else if(x > 0 && y < 0) return 4;
		else return 0;
	}
  	public static double distance(Point p1, Point p2) 
  	{ 
    // Calculating distance 
    	return Math.sqrt(Math.pow(p2.x -p1.x, 2) +  
                Math.pow(p2.y - p1.y, 2) * 1.0); 
  	} 
	boolean equals(Point obj)  // checks if two point objects hold equivalent data
	{
		if(this.x==obj.x && this.y==obj.y)
			return true;
		else
			return false;
	}
 
   
	public String toString(){ //prints points such as (x,y)
		return "("+ this.x+","+this.y+")";
	}
}
