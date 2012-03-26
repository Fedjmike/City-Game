package city.iso;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

import city.Pair;
import city.math.ComplexSolutionException;
import city.math.FirstOrderBivariate;
import city.math.Vector;

public class Rasterizer implements IRasterizer {
    private final Graphics g;
    private final int halfwidth, halfheight;

    //Translated origin
    private Vector origin;

    /**
     * Map an in world point to an on screen coordinate based on
     * current context
     */
    private Vector mapPoint (Vector p) {
        p = p.subtract(origin);
        return new Vector(
                p.getX()+p.getY()+halfwidth,
                (p.getX()-p.getY())/2-p.getZ()+halfheight);
    }

    /**
     * Finds the point in a square corresponding to a point in an
     * arbitrary quadrilateral
     * @param quad An array of four points. In terms of the
     * 		  corresponding square, they must be in the order top
     * 		  left, top right, bottom right, bottom left (going
     * 		  clockwise).
     * @param p Point in the quad to unmap. No bounds checking is
     * 			performed!
     * @return Corresponding point in the 1x1 square
     */
    private static Vector unmapPointInQuad (Vector[] quad, Vector P) {
        //Translate everything so that the top left of the quad is the
        //origin
        Vector B = quad[3].subtract(quad[0]); //Bottom left
        Vector C = quad[1].subtract(quad[0]); //Top right
        Vector D = quad[2].subtract(quad[0]); //Bottom right
        P = P.subtract(quad[0]);

        FirstOrderBivariate[] Es = new FirstOrderBivariate[2];

        //The algorithm essentially says:
        //P is the 2D weighted average of four points, with the weights
        //called x and y. A is not included as it is the origin now.
        //P = (1-x)yB + x(1-y)C + xyD
        //xC + xy(D-B-C) + yB - P = 0
        //This yields an equation for each dimension
        Es[0] = new FirstOrderBivariate(
                C.getX(),
                D.subtract(B).subtract(C).getX(),
                B.getX(),
                P.negate().getX());
        Es[1] = new FirstOrderBivariate(
                C.getY(),
                D.subtract(B).subtract(C).getY(),
                B.getY(),
                P.negate().getY());

        try {
            Pair<Vector, Vector> solutions = Es[0].solve(Es[1]);

            if (solutions.getFirst().getX() >= 0 &&
                    solutions.getFirst().getY() >= 0 &&
                    solutions.getFirst().getX() <= 1 &&
                    solutions.getFirst().getY() <= 1)
                return solutions.getFirst();

            else
                return solutions.getSecond();
        } catch (ComplexSolutionException e) {
            //TODO: Confirm that this is impossible (mathematically) when P is inside
            assert(false);
            return new Vector(0, 0);
        }

    }

    /*::::PUBLIC METHODS::::*/

    public Rasterizer (Graphics g) {
        this.g = g;
        halfwidth = g.getClipBounds().width/2;
        halfheight = g.getClipBounds().height/2;

        origin = new Vector(0, 0, 0); 
    }

    @Override
    public Vector getOrigin () {
        return origin;
    }

    @Override
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

        //The on screen points

        Polygon poly = new Polygon();
        Vector[] points = new Vector[]{
                mapPoint(obj.get(0)),
                mapPoint(obj.get(1)),
                mapPoint(obj.get(2)),
                mapPoint(obj.get(3))};

        for (Vector point: points)
            poly.addPoint((int) point.getX(), (int) point.getY());

        //The texture buffer

        BufferedImage img = new BufferedImage(
                poly.getBounds().width+1,
                poly.getBounds().height+1,
                BufferedImage.TYPE_4BYTE_ABGR);
        
        for (int i = 0; i < img.getWidth(); i++) {
            for (int j = 0; j < img.getHeight(); j++) {
                if (poly.contains(i+poly.getBounds().x, j+poly.getBounds().y)) {
                    Vector posIn = new Vector(i+poly.getBounds().x, j+poly.getBounds().y);
                    Vector posOut = unmapPointInQuad(points, posIn); 
                    System.out.println(posIn + " to " + posOut +
                            " as 0x" + Integer.toHexString(obj.getPattern().getColor(posOut)));
                    img.setRGB(i, j, obj.getPattern().getColor(posOut));
                }
            }
        }
        
        //TODO: Use PathIterator to finish off texture
        
        g.drawImage(img, poly.getBounds().x, poly.getBounds().y, null);
        g.drawPolygon(poly);
    }

    @Override
    public void visit (Sprite obj) {
        // TODO Auto-generated method stub
        System.out.println("v Sp");
    }

}
