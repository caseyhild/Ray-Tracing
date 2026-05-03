public class Plane extends Shape
{
    private final double pos;
    private final String axis;

    public Plane(double p, String a, Vector3D color)
    {
        super(new Vector3D(color));
        pos = p;
        axis = a;
    }

    public double getDist(Vector3D p)
    {
        return switch (axis) {
            case "xy" -> p.z - pos;
            case "xz" -> p.y - pos;
            case "yz" -> p.x - pos;
            default -> 0;
        };
    }
}