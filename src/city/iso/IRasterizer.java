package city.iso;

import city.math.Vector;


public interface IRasterizer {
	public Vector getOrigin ();
	public void setOrigin (Vector o);
	
	public void visit (Shapes g);
	public void visit (Quad g);
	public void visit (Sprite g);
}
