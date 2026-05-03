public class Sphere extends Shape
{
    private final double radius;

    public Sphere(double x, double y, double z, double r)
    {
        pos = new Vector3D(x, y, z);
        radius = r;
    }

    public double getDist(Ray ray)
    {
        double rayDist = Vector3D.sub(ray.pos, pos).mag();
        return rayDist - radius;
    }
}