package city.math;

/**
 * An immutable 2D (effective) or 3D vector of doubles
 */
public final class Vector {
	private final double x, y, z;
	private final double length, dir, pitch;
	
	public Vector (double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
		
		length = Math.sqrt(getX()*getX() +
				 		   getY()*getY() +
				 		   getZ()*getZ());
		
		dir = Math.atan2(getY(), getX());
		
		pitch = Math.atan2(getZ(),
						   Math.sqrt(getX()*getX() +
								   	 getY()*getY()));
		
	}
	
	/**
	 * Construct an (effectively) 2D vector
	 * 
	 * Construct a 3D vector in the z=0 plane. This functions as a 2D vector
	 * as Vectors are immutable and will always be in this plane.
	 */
	public Vector (double x, double y) {
		this(x, y, 0.0);
	}
	
	/**
	 * Return a new vector from its spherical coordinates
	 */
	public static Vector create (double length, double dir, double pitch) {
		return new Vector(length*Math.cos(pitch)*Math.cos(dir),
						  length*Math.cos(pitch)*Math.sin(dir),
						  length*Math.sin(pitch));
	}
	
	/**
	 * Return a new 2D vector from its polar coordinates
	 */
	public static Vector create (double length, double dir) {
		return new Vector(length*Math.cos(dir),
						  length*Math.sin(dir));
	}
	
	public double getX () {
		return x;
	}
	
	public double getY () {
		return y;
	}

	public double getZ () {
		return z;
	}
	
	public double getLength () {
		return length;
	}
	
	/**
	 * Get the direction of the vector in the XY plane
	 * 
	 * Get the angle between the X axis and the projection of the vector onto
	 * the XY plane.
	 */
	public double getDir () {
		return dir;
	}
	
	/**
	 * Get the pitch of the vector
	 * 
	 * Get the angle between the vector and the projection of the vector onto
	 * the XY plane.
	 */
	public double getPitch () {
		return pitch;
	}
	
	public Vector add (Vector r) {
		return new Vector(getX()+r.getX(),
						  getY()+r.getY(),
						  getZ()+r.getZ());
	}
	
	public Vector subtract (Vector r) {
		return new Vector(getX()-r.getX(),
						  getY()-r.getY(),
						  getZ()-r.getZ());
	}
	
	/**
	 * Return a negated copy of this vector
	 */
	public Vector negate () {
		return new Vector(-getX(), -getY(), -getZ());
	}
	
	/**
	 * Return a new normalized copy of the vector
	 */
	public Vector norm () {
		return scale(1/getLength());
	}
	
	/**
	 * Return a copy of this vector scaled by a factor
	 */
	public Vector scale (double factor) {
		return new Vector(factor*getX(), factor*getY(), factor*getZ());
	}
	
	public boolean equals (Vector r) {
		return getX() == r.getX() && getY() == r.getY() &&
			   getZ() == r.getZ();
	}
	
	@Override
	public String toString () {
		if (getZ() == 0)
			return "[" + getX() + ", " + getY() + "]";
		
		else
			return "[" + getX() + ", " + getY() + ", " + getZ() + "]";
	}
}
