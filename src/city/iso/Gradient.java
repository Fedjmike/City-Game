package city.iso;

import city.math.Vector;

public final class Gradient implements IPattern {
    int colTL, colTR, colBL, colBR;
    
    public Gradient (int colTL, int colTR, int colBL, int colBR) {
        this.colTL = colTL;
        this.colTR = colTR;
        this.colBL = colBL;
        this.colBR = colBR;
    }
    
    /**
     * Get the nth component of the colour at pos
     * 
     * @param n Zero based index of components, 0B 1G 2R 3A
     */
    private int getComponent (int n, Vector pos) {
        int shift = n*8;
        
        //Two dimensional weighted mean of the components
        return (int)((colTL >>> shift & 0xFF) * (1-pos.getX()) * (1-pos.getY()) +
                (colTR >>> shift & 0xFF) * pos.getX() * (1-pos.getY()) +
                (colBL >>> shift & 0xFF) * (1-pos.getX()) * pos.getY() +
                (colBR >>> shift & 0xFF) * pos.getX() * pos.getY()) << shift;
    }
    
    @Override
    public int getColor(Vector pos) {
        return getComponent(0, pos) | getComponent(1, pos) |
                getComponent(2, pos) | getComponent(3, pos);
    }
}
