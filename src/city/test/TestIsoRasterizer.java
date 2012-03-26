package city.test;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import city.math.Vector;
import city.iso.*;

public class TestIsoRasterizer extends JPanel {
    @Override
    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.black);

        IRasterizer r = new Rasterizer(g);
        Shapes container = new Shapes();
        
        try {
            Texture tex = new Texture(ImageIO.read(new File("data/tex/test0.png")));
            
            container.add(new Quad(
                    new Vector(0, 0),
                    new Vector(100, 0),
                    new Vector(100, 100),
                    new Vector(0, 100),
                    tex));
            
            container.add(new Quad(
                    new Vector(0, 0, 0),
                    new Vector(0, 100, 0),
                    new Vector(0, 100, 100),
                    new Vector(0, 0, 100),
                    new Gradient(0xFF000000, 0xFFFF0000, 0xFF0000FF, 0xFFFF00FF)));
            
            //Render!
            container.accept(r);
            
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main (String[] args) {
        Container content = new TestIsoRasterizer();
        JFrame frame = new JFrame("");
        frame.setBackground(Color.white);
        content.setBackground(Color.white);
        frame.setSize(640, 480);
        frame.setContentPane(content);
        frame.addWindowListener(new ExitListener());
        frame.setVisible(true);
    }
}
