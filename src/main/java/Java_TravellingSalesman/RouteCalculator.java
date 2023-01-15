package Java_TravellingSalesman;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class RouteCalculator {

    private List<String> permutations;
    private List<City> cities;

    public RouteCalculator(List<String> permutations, List<City> cities) {
        this.permutations = permutations;
        this.cities = cities;
    }
    public List<Route> calculateAllRoutes(JTable table) {

        DefaultTableModel model = ((DefaultTableModel) table.getModel());
        model.setRowCount(0);
        List<Route> routes = new ArrayList<>();
        for (int i = 0; i < permutations.size(); i++) {
            String perm = permutations.get(i);
            double overallDistance = 0;
            for (int j = 0; j < perm.length() - 1; j++) {
                String str1 = perm.charAt(j) + "";
                String str2 = perm.charAt(j + 1) + "";
                City c1 = cities.stream().filter(a -> a.getLabel().equals(str1)).findAny().get();
                City c2 = cities.stream().filter(a -> a.getLabel().equals(str2)).findAny().get();
                int deltaX = Math.abs(c1.getX() - c2.getX());
                int deltaY = Math.abs(c1.getY() - c2.getY());
                double distance = Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
                overallDistance = distance + overallDistance;
            }
            Route route = new Route(perm, overallDistance);
            model.addRow(new Object[]{route.getRoute(), (int) (Math.round(route.getDistance()))});
            routes.add(route);
        }
        return routes;
    }

    public List<String> getPermutations() {
        return permutations;
    }

    public void setPermutations(List<String> permutations) {
        this.permutations = permutations;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

}
