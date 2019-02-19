package ParticleSwarmOptimizer;

public class Vector {
	
	double x, y, z;

	public Vector(double x, double y, double z) {
		
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public void setLocation(double x, double y, double z) {
		
		setX(x);
		setY(y);
		setZ(z);
	}

	private void setX(double x) {
		
		this.x = x;
	}
	
	private void setY(double y) {
		
		this.y = y;
	}

	private void setZ(double z) {
	
		this.z = z;
	}
	
	public String toString() {
		
		return "x: " + x + " Y: " + y + " Z: " + z;
	}

	public double getX() {
		
		return x;
	}
	
	public double getY() {
		
		return y;
	}


	public void multiply(double d) {
		
		x *= d;
		y *= d;
		z *= d;
	}

	public void subtract(Vector v) {
		
		x = x - v.x;
		y = y - v.y;
		z = z - v.z;
	}

	public void add(Vector v) {
		
		x += v.x;
		y += v.y;
		z += v.z;
	}
}
