package ParticleSwarmOptimizer;

public class Velocity {

	private double x, y, z;
	
	public Velocity(double x, double y, double z) {
		
		this.x = x;
		this.y = y;
		this. z = z;
	}
	
	public double getX() {
		
		return x;
	}
	
	public double getY() {
		
		return y;
	}
	
	public double getZ() {
		
		return z;
	}
	
	public void setX(double x) {
		
		this.x = x;
	}
	
	public void setY(double y) {
		
		this.y = y;
	}

	public void setZ(double z) {
	
		this.z = z;
	}
	
	public String toString() {
	
		return "X: " + x + " Y: " + y + " Z: " + z;
	}
}
