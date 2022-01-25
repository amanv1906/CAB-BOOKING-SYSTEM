package CabBooking.src.enums_and_constants;

public class EuclidieanDistance {
    public static int get_distance(int x1, int y1, int x2, int y2)
    {
        double x_dist = Math.abs(x2-x1);
        double y_dist = Math.abs(y2-y1);
        
        return (int) Math.round(Math.sqrt(Math.pow(x_dist, 2) + Math.pow(y_dist, 2)));
    }
}
