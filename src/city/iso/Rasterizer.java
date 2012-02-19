package city.iso;

import java.awt.*;

import city.Vector;

public class Rasterizer implements IRasterizer {
	private final Graphics g;
	private final int halfwidth, halfheight;
	
	//Translated origin
	private Vector origin;
	
	public Rasterizer (Graphics g) {
		this.g = g;
		halfwidth = g.getClipBounds().width/2;
		halfheight = g.getClipBounds().height/2;
		
		origin = new Vector(0, 0, 0); 
	}
	
	/**
	 * Map an in world point to an on screen x coordinate based on current context
	 */
	private int mapPointX (Vector p) {
		p = p.subtract(origin);
		return (int)(p.getX()+p.getY())+halfwidth;
	}
	
	/**
	 * Map an in world point to an on screen y coordinate based on current context
	 */
	private int mapPointY (Vector p) {
		p = p.subtract(origin);
		return (int)((p.getX()-p.getY())/2-p.getZ())+halfheight;
	}
	
	public Vector getOrigin () {
		return origin;
	}
	
	public void setOrigin (Vector o) {
		origin = o;
	}
	
	@Override
	public void visit (Shapes obj) {
		// TODO Auto-generated method stub
		System.out.println("v S");
	}

	@Override
	public void visit (Quad obj) {
		System.out.println("v Q");
		
		g.drawPolygon(new int[]{mapPointX(obj.get(0)),
								mapPointX(obj.get(1)),
								mapPointX(obj.get(2)),
								mapPointX(obj.get(3))},
					  new int[]{mapPointY(obj.get(0)),
								mapPointY(obj.get(1)),
								mapPointY(obj.get(2)),
								mapPointY(obj.get(3))},
					  4);
	}

	@Override
	public void visit (Sprite obj) {
		// TODO Auto-generated method stub
		System.out.println("v Sp");
	}

}
