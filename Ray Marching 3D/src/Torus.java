public class Torus extends Shape
{
    public Vector3D pos;
    private final Vector2D size;

    public Torus(double x, double y, double z, double r1, double r2, Vector3D color)
    {
        super(new Vector3D(color));
        pos = new Vector3D(x, y, z);
        size = new Vector2D(r1, r2);
    }

    public double getDist(Vector3D p, Vector2D r)
    {
        double x = new Vector2D(p.x, p.z).mag() - r.x;
        return new Vector2D(x, p.y).mag() - r.y;
    }
    
    public double getDist(Vector3D p)
    {
        return getDist(Vector3D.sub(p, pos), size);
    }
}