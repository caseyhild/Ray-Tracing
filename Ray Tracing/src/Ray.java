public class Ray
{
    public Vector3D pos;
    public Vector3D dir;
    public Vector3D energy;
    public Ray(Vector3D p, Vector3D d, Vector3D e)
    {
        pos = new Vector3D(p.x, p.y, p.z);
        dir = new Vector3D(d.x, d.y, d.z);
        energy = new Vector3D(e.x, e.y, e.z);
    }

    public boolean intersectsPlane(Plane p)
    {
        return dir.y < 0;
    }

    public boolean intersectsSphere(Sphere s)
    {
        Vector3D v = Vector3D.sub(s.pos, pos);
        double a = Vector3D.dotProduct(v, dir);
        double b = Vector3D.dotProduct(v, v);
        double c = a * a - (b - s.radius * s.radius);
        return Math.abs(c) <= 0.0001;
    }

    public RayHit trace(Plane p, Sphere[] spheres)
    {
        RayHit bestHit = new RayHit();
        double t = -(pos.y - p.y)/dir.y;
        if(t > 0 && t < bestHit.dist)
        {
            bestHit.dist = t;
            bestHit.pos = Vector3D.add(pos, Vector3D.mult(dir, t));
            bestHit.normal = new Vector3D(0, 1, 0);
            bestHit.shape = p;
        }
        for (Sphere sphere : spheres) {
            Vector3D d = Vector3D.sub(pos, sphere.pos);
            double p1 = -Vector3D.dotProduct(dir, d);
            double p2sqr = p1 * p1 - Vector3D.dotProduct(d, d) + sphere.radius * sphere.radius;
            if (p2sqr >= 0) {
                double p2 = Math.sqrt(p2sqr);
                t = p1 - p2 > 0 ? p1 - p2 : p1 + p2;
                if (t > 0 && t < bestHit.dist) {
                    bestHit.dist = t;
                    bestHit.pos = Vector3D.add(pos, Vector3D.mult(dir, t));
                    bestHit.normal = Vector3D.normalize(Vector3D.sub(bestHit.pos, sphere.pos));
                    bestHit.shape = sphere;
                }
            }
        }
        return bestHit;
    }
}