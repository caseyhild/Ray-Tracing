import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
public class RayTracing extends JFrame implements Runnable
{
    private final Thread thread;
    private boolean running;
    private final BufferedImage image;
    private int[] pixels;
    private final Screen screen;
    private final Camera camera;

    public RayTracing()
    {
        //set size of screen
        int width = 600;
        int height = 600;
        //setting up the screen
        pixels = new int[width * height];
        Sphere[] spheres = new Sphere[3];
        spheres[0] = new Sphere(0, 3, 6, 1, rgbNum(255, 0, 0));
        spheres[1] = new Sphere(4, 4, 8, 2, rgbNum(0, 255, 0));
        spheres[2] = new Sphere(-2, 6, 12, 3, rgbNum(0, 0, 255));
        Plane floor = new Plane(0, rgbNum(192, 192, 192));
        Light light = new Light(1000, 1000, 0);
        //fix this
        camera = new Camera(0, 3, 0);
        screen = new Screen(width, height, spheres, floor, light);
        //what will be displayed to the user and each pixel of that image
        thread = new Thread(this);
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
        //setting up the window
        setSize(width, height + 28);
        setResizable(false);
        setTitle("Ray Tracing");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.gray);
        setLocationRelativeTo(null);
        setVisible(true);
        start();
    }

    private synchronized void start()
    {
        //starts game
        running = true;
        thread.start();
    }

    private void render()
    {
        //draws the window
        BufferStrategy bs = getBufferStrategy();
        if(bs == null)
        {
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.drawImage(image, 0, 28, image.getWidth(), image.getHeight(), null);
        bs.show();
    }

    public void run()
    {
        //main game loop
        long lastTime = System.nanoTime();
        final double ns = 1000000000.0 / 60.0;//60 times per second
        double delta = 0;
        requestFocus();
        while(running)
        {
            //updates time
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1)//Make sure update is only happening 60 times a second
            {
                //updates game
                screen.update(camera, pixels);
                delta--;
            }
            render();//displays to the screen unrestricted time
        }
    }
    
    private int rgbNum(int r, int g, int b)
    {
        //gets rgb decimal value from rgb input
        return r * 65536 + g * 256 + b;
    }

    public static void main(String [] args)
    {
        new RayTracing();
    }
}