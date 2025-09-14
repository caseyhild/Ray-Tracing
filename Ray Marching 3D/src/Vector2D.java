public class Vector2D
{
    public double x;
    public double y;

    public Vector2D(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    public void sub(Vector2D v)
    {
        x -= v.x;
        y -= v.y;
    }

    public void div(double n)
    {
        x /= n;
        y /= n;
    }

    public double mag()
    {
        return Math.sqrt(x * x + y * y);
    }

    public String toString()
    {
        return x + " " + y;
    }
}