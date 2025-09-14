import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class RayMarching2D extends JPanel implements ActionListener, KeyListener
{
    Timer tm = new Timer(1, this);
    private static final int width = 600;
    private static final int height = 600;
    private final int mapSize;
    private final int[][] pixels;
    private final Screen screen;
    private final Camera camera;
    private final Shape[] shapes;
    private boolean keyPressed;
    private KeyEvent key;

    public RayMarching2D()
    {
        addKeyListener(this);
        pixels = new int[height][width];
        mapSize = 6;
        screen = new Screen(width, height, mapSize);
        camera = new Camera(3, 4, 0, 0, -1, 0, 360);
        shapes = new Shape[2];
        shapes[0] = new Sphere(2, 2, 0, 0.5);
        shapes[1] = new Cube(4, 3, 0, 0.5, 0.5 , 0.5);
        screen.update(camera, shapes, pixels);
        setBackground(new Color(0, 0, 0));
    }

    public void update()
    {
        if(keyPressed && key.getKeyCode() == KeyEvent. VK_UP)
            camera.pos.y -= 0.1;
        if(keyPressed && key.getKeyCode() == KeyEvent. VK_DOWN)
            camera.pos.y += 0.1;
        if(keyPressed && key.getKeyCode() == KeyEvent. VK_LEFT)
            camera.pos.x -= 0.1;
        if(keyPressed && key.getKeyCode() == KeyEvent. VK_RIGHT)
            camera.pos.x += 0.1;
        if(camera.pos.x <= 0.505)
            camera.pos.x = 0.505;
        if(camera.pos.x >= mapSize - 0.505)
            camera.pos.x = mapSize - 0.505;
        if(camera.pos.y <= 0.505)
            camera.pos.y = 0.505;
        if(camera.pos.y >= mapSize - 0.505)
            camera.pos.y = mapSize - 0.505;
        screen.update(camera, shapes, pixels);
    }

    public void draw(Graphics g)
    {
        for(int y = 0; y < height; y++)
        {
            for(int x = 0; x < width; x++)
            {
                g.setColor(new Color(pixels[y][x]));
                g.drawLine(x, y, x, y);
            }
        }
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

    public void keyPressed(KeyEvent key)
    {
        keyPressed = true;
        this.key = key;
    }

    public void keyReleased(KeyEvent key)
    {
        keyPressed = false;
        this.key = null;
    }

    public void keyTyped(KeyEvent key)
    {

    }

    public static void main(String[] args)
    {
        RayMarching2D r  = new RayMarching2D();
        JFrame jf = new JFrame();
        jf.setTitle("Ray Marching 2D");
        jf.setSize(width, height + 28);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setResizable(false);
        jf.setLocationRelativeTo(null);
        jf.add(r);
        jf.setVisible(true);
    }
}