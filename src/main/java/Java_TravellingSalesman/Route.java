
package Java_TravellingSalesman;

public class Route {
    private String route;
    private double distance; 

    public Route(String route, double distance) {
        this.route = route;
        this.distance = distance;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
    
}
