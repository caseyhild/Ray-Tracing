public class Vector3D
{
    public double x;
    public double y;
    public double z;

    public Vector3D()
    {
        x = 0; 
        y = 0;
        z = 0;
    }

    public Vector3D(double x, double y, double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public Vector3D(Vector3D v)
    {
        x = v.x;
        y = v.y;
        z = v.z;
    }

    public void setX(double x)
    {
        this.x = x;
    }

    public void setY(double y)
    {
        this.y = y;
    }
    
    public void setZ(double z)
    {
        this.z = z;
    }

    public void add(Vector3D v)
    {
        x += v.x;
        y += v.y;
        z += v.z;
    }

    public static Vector3D add(Vector3D v1, Vector3D v2)
    {
        Vector3D v = new Vector3D();
        v.setX(v1.x + v2.x);
        v.setY(v1.y + v2.y);
        v.setZ(v1.z + v2.z);
        return v;
    }

    public static Vector3D sub(Vector3D v1, Vector3D v2)
    {
        Vector3D v = new Vector3D();
        v.setX(v1.x - v2.x);
        v.setY(v1.y - v2.y);
        v.setZ(v1.z - v2.z);
        return v;
    }

    public static Vector3D mult(Vector3D v1, double n)
    {
        Vector3D v = new Vector3D();
        v.setX(v1.x * n);
        v.setY(v1.y * n);
        v.setZ(v1.z * n);
        return v;
    }
    
    public static double dotProduct(Vector3D v1, Vector3D v2)
    {
        return v1.x * v2.x + v1.y * v2.y + v1.z * v2.z;
    }

    public void div(double n)
    {
        x /= n;
        y /= n;
        z /= n;
    }

    public double mag()
    {
        return Math.sqrt(x * x + y * y + z * z);
    }

    public void normalize()
    {
        if(mag() > 0)
            div(mag());
    }

    public static Vector3D normalize(Vector3D v1)
    {
        Vector3D v = new Vector3D();
        if(v1.mag() > 0)
        {
            v.setX(v1.x/v1.mag());
            v.setY(v1.y/v1.mag());
            v.setZ(v1.z/v1.mag());
        }
        return v;
    }
    public static Vector3D abs(Vector3D v)
    {
        return new Vector3D(Math.abs(v.x), Math.abs(v.y), Math.abs(v.z));
    }

    public static Vector3D max(Vector3D v1, Vector3D v2)
    {
        return new Vector3D(Math.max(v1.x, v2.x), Math.max(v1.y, v2.y), Math.max(v1.z, v2.z));
    }

    public void rotateY3D(double theta, Vector3D origin)
    { 
        double sinTheta = Math.sin(Math.toRadians(theta));
        double cosTheta = Math.cos(Math.toRadians(theta));
        double xCopy = x - origin.x;
        double zCopy = z - origin.z;
        x = origin.x + xCopy * cosTheta - zCopy * sinTheta;
        z = origin.z + zCopy * cosTheta + xCopy * sinTheta;
    }

    public void rotateX3D(double theta, Vector3D origin)
    { 
        double sinTheta = Math.sin(Math.toRadians(theta));
        double cosTheta = Math.cos(Math.toRadians(theta));
        double yCopy = y - origin.y;
        double zCopy = z - origin.z;
        y = origin.y + yCopy * cosTheta - zCopy * sinTheta;
        z = origin.z + zCopy * cosTheta + yCopy * sinTheta;
    }

    public String toString()
    {
        return x + " " + y + " " + z;
    }
}