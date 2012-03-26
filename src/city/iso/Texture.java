package city.iso;

import java.awt.image.BufferedImage;

import city.math.Vector;

public class Texture implements IPattern {
    private BufferedImage tex;
    
    public Texture (BufferedImage tex) {
        this.tex = tex;
    }
    
    @Override
    public int getColor (Vector pos) {
        try {
            return tex.getRGB(
                    (int)(pos.getX()*tex.getWidth()),
                    (int)(pos.getY()*tex.getHeight()));
        } catch (ArrayIndexOutOfBoundsException e) {
            return 0;
        }
    }
}
