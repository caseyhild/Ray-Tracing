public class Screen
{
    private final int width;
    private final int height;
    private final int mapSize;

    public Screen(int w, int h, int ms)
    {
        width = w;
        height = h;
        mapSize = ms;
    }

    public void update(Camera camera, Shape[] shapes, int[][] pixels)
    {
        for(int y = 0; y < height; y++)
        {
            for(int x = 0; x < width; x++)
            {
                if(y % (height/mapSize) == 0 || x % (width/mapSize) == 0)
                    pixels[y][x] = rgbNum(64, 64, 64);
                else
                    pixels[y][x] = rgbNum(0, 0, 0);
            }
        }
        pixels[(int) (height/mapSize * camera.pos.y)][(int) (width/mapSize * camera.pos.x)] = rgbNum(255, 255, 255);
        pixels[(int) (height/mapSize * camera.pos.y) - 1][(int) (width/mapSize * camera.pos.x)] = rgbNum(255, 255, 255);
        pixels[(int) (height/mapSize * camera.pos.y) + 1][(int) (width/mapSize * camera.pos.x)] = rgbNum(255, 255, 255);
        pixels[(int) (height/mapSize * camera.pos.y)][(int) (width/mapSize * camera.pos.x) - 1] = rgbNum(255, 255, 255);
        pixels[(int) (height/mapSize * camera.pos.y)][(int) (width/mapSize * camera.pos.x) + 1] = rgbNum(255, 255, 255);
        for(double angle = -camera.FOV/2.0; angle <= camera.FOV/2.0; angle++)
        {
            Ray ray = new Ray(camera.pos, camera.dir);
            ray.dir.setAngleXY(camera.dir.getAngleXY() + angle);
            while(!ray.hit)
            {
                ray.march(shapes, mapSize);
            }
            pixels[(int) (height/mapSize * ray.pos.y)][(int) (width/mapSize * ray.pos.x)] = rgbNum(255, 255, 255);
        }
    }

    private int rgbNum(int r, int g, int b)
    {
        //gets rgb decimal value from rgb input
        return r * 65536 + g * 256 + b;
    }
}