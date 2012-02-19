package iso;

import java.util.List;

/**
 * A collection of graphic objects
 */
public class Shapes implements IGraphic {
	private List<IGraphic> children;
	
	@Override
	public void accept(IRasterizer r) {
		for (IGraphic child: children)
			child.accept(r);
		
		r.visit(this);
	}

}
