package city.iso;

import city.math.Vector;

public class Quad implements IGraphic {
    /**
     * Upper/lower left/right (in terms of texture coordinates) corners
     */
    private Vector[] points;
    private IPattern p;

    //TODO: use one point plus two adjacent vectors, then calculate
    //      the other three, to ensure coplanarity   
    public Quad (Vector ul, Vector ur, Vector ll, Vector lr,
            IPattern p) {
        points = new Vector[]{ul, ur, ll, lr};
        this.p = p;
    }

    public Quad (Vector ul, Vector ur, Vector ll, Vector lr) {
        this(ul, ur, ll, lr, null);
    }

    public Vector get (int n) {
        return points[n];
    }

    public void set (int n, Vector p) {
        //TODO: Catch ArrayIndexOutOfBounds, throw ...?
        points[n] = p;
    }

    public IPattern getPattern () {
        return p;
    }
    
    @Override
    public void accept(IRasterizer r) {
        r.visit(this);
    }

}
