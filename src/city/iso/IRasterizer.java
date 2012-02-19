package city.iso;

public interface IRasterizer {
	public void visit (IGraphic g);
	public void visit (Shapes g);
	public void visit (Quad g);
	public void visit (Sprite g);
}
