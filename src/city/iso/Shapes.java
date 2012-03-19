package city.iso;

import java.util.LinkedList;
import java.util.List;

/**
 * A collection of graphic objects
 */
public class Shapes implements IGraphic {
    private List<IGraphic> children;

    public Shapes () {
        children = new LinkedList<IGraphic>();
    }

    @Override
    public void accept(IRasterizer r) {
        for (IGraphic child: children) {
            System.out.println("c a");
            child.accept(r);
        }	

        r.visit(this);
    }

    public void add(IGraphic g) {
        children.add(g);
    }

}
