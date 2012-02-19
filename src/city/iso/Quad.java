package city.iso;

import city.Vector;

public class Quad implements IGraphic {
	/**
	 * Upper/lower left/right (in terms of texture coordinates) corners
	 */
	private Vector ul, ur, ll, lr;
	private Texture tex;
	
	public Quad (Vector ul, Vector ur, Vector ll, Vector lr,
				 Texture tex) {
		this.ul = ul;
		this.ur = ur;
		this.ll = ll;
		this.lr = lr;
		this.tex = tex;
	}
	
	public Quad (Vector ul, Vector ur, Vector ll, Vector lr) {
		this(ul, ur, ll, lr, null);
	}
	
	@Override
	public void accept(IRasterizer r) {
		
	}

}
