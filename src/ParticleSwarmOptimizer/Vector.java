package ParticleSwarmOptimizer;

import java.util.ArrayList;

public class Vector {
	
	int dimensions;
	ArrayList<Double> coords;
	
	public Vector(int dimensions) {
		
		this.dimensions = dimensions;
		coords = new ArrayList<Double>();
		
		for(int i = 0; i < dimensions; i++) {
			
			coords.add(0.0);
		}
		
		//System.out.println(coords.get(0));
	}

	/*public void multiply(double d) {
		
		x *= d;
		y *= d;
		z *= d;
	}

	public void subtract(Vector v) {
		
		x -= v.getX();
		y -= v.getY();
		z -= v.getZ();
	}

	public void add(Vector v) {
		
		x = x + v.getX();
		y = y + v.getY();
		z = z + v.getZ();
	}*/
	
	public Vector(ArrayList<Double> coordsClone) {
		
		coordsClone = coords;
	}

	public Vector clone() {
		
		return new Vector(coords);
	}

	public void addLocation(ArrayList<Double> coords) {
		
		this.coords = coords;
	}
}
