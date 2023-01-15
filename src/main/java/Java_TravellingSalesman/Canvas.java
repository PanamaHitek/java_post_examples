package Java_TravellingSalesman;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.JPanel;
public class Canvas extends JPanel {
    List<City> cities = new ArrayList<>();
    List<String> routes = new ArrayList<>();
    private boolean steadyState = true;
    boolean liveAction = false;
    int squareSize = 10;

    public Canvas() {
        this.setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics graph) {
        super.paintComponent(graph);
        Graphics2D g = (Graphics2D) graph;
        g.setColor(Color.RED);
        if (routes.size() > 0) {
            for (int i = 0; i < routes.size(); i++) {
                String str = routes.get(i);
                for (int j = 0; j < str.length() - 1; j++) {
                    String str1 = str.charAt(j) + "";
                    String str2 = str.charAt(j + 1) + "";
                    City c1 = cities.stream().filter(a -> a.getLabel().equals(str1)).findAny().get();
                    City c2 = cities.stream().filter(a -> a.getLabel().equals(str2)).findAny().get();
                    g.drawLine(c1.getX(), c1.getY(), c2.getX(), c2.getY());
                }
            }
        }
        g.setColor(Color.BLACK);
        if (cities.size() > 0) {
            for (int i = 0; i < cities.size(); i++) {
                City city = cities.get(i);
                g.fillRect(city.getX() - (squareSize / 2), city.getY() - (squareSize / 2), squareSize, squareSize);
                g.drawString(city.getLabel(), city.getX() - 4, city.getY() - 8);
            }
        }
    }
    public void drawCities(List<City> cities) {
        this.cities = cities;
        this.repaint();
    }

    public void setActualRoute(String route) {
        this.getRoutes().clear();
        this.routes.add(route);
        this.repaint();
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    public List<String> getRoutes() {
        return routes;
    }

    public void setRoutes(List<String> routes) {
        this.routes = routes;
    }

}
