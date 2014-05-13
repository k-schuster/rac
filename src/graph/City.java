package graph;

import java.util.Set;
import java.util.HashSet;


public class City {

    private final int id;
    private Affiliation affiliation;

    public boolean visited = false;
    private Alliance alliance;

    private final int x, y;

    private Set<City> neighbors;


    public City(int id, Affiliation affiliation, int x, int y) {
        this.id = id;
        this.affiliation = affiliation;
        this.x = x;
        this.y = y;
        this.neighbors = new HashSet<City>();
    }

    public City(int id, String affiliation, int x, int y) {
        this.id = id;
        this.affiliation = Affiliation.valueOf(affiliation);
        this.x = x;
        this.y = y;
        this.neighbors = new HashSet<City>();
    }

    public int getId() {
        return this.id;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public Affiliation getAffiliation() {
        return this.affiliation;
    }

    public void setAffiliation(String affiliation) {
        this.affiliation = Affiliation.valueOf(affiliation);
    }

    public void setAffiliation(Affiliation affiliation) {
        this.affiliation = affiliation;
    }

    public Set<City> getNeighbors() {
        return neighbors;
    }

    public boolean addNeighbor(City city) {
        if (neighbors.contains(city))
            return false;
        else {
            neighbors.add(city);
            return true;
        }
    }

    private String printNeighbors() {
        String result = "";
        for (City c : getNeighbors()) {
            result += c.getId() + ", ";
        }
        return result;
    }

    @Override
    public String toString() {
        return "Id: " + id +
                " Affiliation: " + affiliation +
                " Pos: " + x + "," + y +
                " Adj: " + printNeighbors();
    }

}