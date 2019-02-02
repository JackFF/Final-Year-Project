package ParticleSwarmOptimizer;

public class Vector {

	Location location;
	Velocity velocity;
	
	double locationX;
	double locationY;
	double locationZ;
	
	double velocityX;
	double velocityY;
	double velocityZ;
	
	public Vector(Location location, Velocity velocity) {
		
		this.location = location;
		this.velocity = velocity;
	}
	
	public Double getX() {
		
		return locationX;
	}
	
	public Double getY() {
		
		return locationY;
	}

	public Double getZ() {
	
		return locationZ;
	}
	
	public void add() {
		
		locationX = location.getX();
		velocityX = velocity.getX();
		locationX += velocityX;
		
		locationY = location.getY();
		velocityY = velocity.getY();
		locationY += velocityY;
		
		locationZ = location.getZ();
		velocityZ = velocity.getZ();
		locationZ += velocityZ;
		
		location.setX(locationX);
		location.setY(locationY);
		location.setZ(locationZ);
	}
}
