import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.*;
public class RayMarching3D extends JPanel implements ActionListener
{
    javax.swing.Timer tm = new javax.swing.Timer(1, this);
    private static final int width = 600;
    private static final int height = 600;
    private final BufferedImage bufferedImage;
    private final Screen screen;
    private final ArrayList<Shape> shapes;
    private final Light light;

    public RayMarching3D()
    {
        bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        shapes = new ArrayList<>();

        shapes.add(new Cube(-1, 5, 8, 0.5, 0.5, 0.5, new Vector3D(0, 255, 0)));
                ((Cube) shapes.getLast()).pos.rotateY3D(45, new Vector3D(3, 1, 8));
        shapes.add(new Sphere(2, 6, 10, 2, new Vector3D(255, 0, 0)));
                ((Sphere) shapes.getLast()).pos.rotateY3D(45, new Vector3D(3, 1, 8));
        shapes.add(new Torus(0, 4, 6, 1, 0.25, new Vector3D(0, 0, 255)));
                ((Torus) shapes.getLast()).pos.rotateY3D(45, new Vector3D(3, 1, 8));
        shapes.add(new Plane(0, "xz", new Vector3D(255, 255, 255)));

        for(int r = -5; r <= 5; r++)
        {
            for(int c = -5; c <= 5; c++)
            {
                shapes.add(new Sphere(3 * r - 6, 1, 14 + 3 * c, 1, new Vector3D(64, 64, 64)));
            }
        }
        light = new Light(3, 10, 4);
        Camera camera = new Camera(0, 10, -4);
        camera.ray.pos.rotateY3D(45, new Vector3D(3, 1, 8));
        screen = new Screen(camera, width, height);
        screen.update(shapes, light, bufferedImage);
        setBackground(new Color(0, 0, 0));
    }

    public void update()
    {
        screen.update(shapes, light, bufferedImage);
    }

    public void draw(Graphics g)
    {
        g.drawImage(bufferedImage, 0, 0, this);
    }

    public void addNotify()
    {
        super.addNotify();
        requestFocus();
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        draw(g);
        tm.start();
    }

    public void actionPerformed(ActionEvent e)
    {
        update();
        repaint();
    }

    public static void main(String[] args)
    {
        RayMarching3D r  = new RayMarching3D();
        JFrame jf = new JFrame();
        jf.setTitle("Ray Marching 3D");
        jf.setSize(width, height + 28);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setResizable(false);
        jf.setLocationRelativeTo(null);
        jf.add(r);
        jf.setVisible(true);
    }
}