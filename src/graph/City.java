package graph;

import java.util.Set;
import java.util.HashSet;

/**
 * This class represents a city. It's basically a vertex on a graph storing
 * all adjacent vertices, thereby it's edges, as a Set of Neighbors.
 */
public class City {

    private final int id;
    private Affiliation affiliation;

    public boolean visited = false;
    private Alliance alliance;

    private final int x, y;

    private Set<City> neighbors;


    /**
     * @todo Should probably throw an exception.
     * Constructs a city with the given (unique) id, affiliation and
     * x, y coordinates.
     * @param id Integer value representing this cities unique id.
     * @param affiliation Affiliation this city is to be constructed with.
     * @param x Integer value representing x coordinate.
     * @param y Integer value representing y coordinate.
     */
    public City(int id, Affiliation affiliation, int x, int y) {
        this.id = id;
        this.affiliation = affiliation;
        this.x = x;
        this.y = y;
        this.neighbors = new HashSet<City>();
    }

    /**
     * @todo Should probably throw an exception.
     * Constructs a city with the given (unique) id, affiliation and
     * x, y coordinates.
     * @param id Integer value representing this cities unique id.
     * @param affiliation String representing affiliation this city
     *                    is to be constructed with.
     * @param x Integer value representing x coordinate.
     * @param y Integer value representing y coordinate.
     */
    public City(int id, String affiliation, int x, int y) {
        this.id = id;
        this.affiliation = Affiliation.valueOf(affiliation);
        this.x = x;
        this.y = y;
        this.neighbors = new HashSet<City>();
    }

    /**
     * Returns the id of this city.
     * @return Id of this city.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Returns the x coordinate of this city.
     * @return X coordinate of this city.
     */
    public int getX() {
        return this.x;
    }

    /**
     * Returns the y coordinate of this city.
     * @return Y coordinate of this city.
     */
    public int getY() {
        return this.y;
    }

    /**
     * Returns affiliation of this city.
     * @return Affiliation of this city.
     */
    public Affiliation getAffiliation() {
        return this.affiliation;
    }

    /**
     * Changes current affiliation of this city to the specified one.
     * @param affiliation String representing new affiliation to be
     *                    assigned to this city.
     */
    public void setAffiliation(String affiliation) {
        this.affiliation = Affiliation.valueOf(affiliation);
    }

    /**
     * Changes current affiliation of this city to the specified one.
     * @param affiliation New affiliation to be assigned to this city.
     */
    public void setAffiliation(Affiliation affiliation) {
        this.affiliation = affiliation;
    }

    /**
     * Returns a set of all adjacent (neighboring) cities of this city.
     * @return Set of all adjacent cities of this city.
     */
    public Set<City> getNeighbors() {
        return neighbors;
    }

    /**
     * Adds a city to the set of adjacent (neighboring) cities of this city.
     * @param city City to be added.
     * @return true if city was added, false if it was already part of this
     * city's adjacent cities.
     */
    public boolean addNeighbor(City city) {
        if (neighbors.contains(city))
            return false;
        else {
            neighbors.add(city);
            return true;
        }
    }

    /**
     * Helper method returns the ids of all adjacent cities of this city
     * as a string.
     * @return String containing the ids of all adjacent cities.
     */
    private String printNeighbors() {
        String result = "";
        for (City c : getNeighbors()) {
            result += c.getId() + ", ";
        }
        return result;
    }

    /**
     * Returns a human readable string representation of this city with all
     * relevant information.
     * @return Human readable string representation of this city.
     */
    @Override
    public String toString() {
        return "Id: " + id +
                " Affiliation: " + affiliation +
                " Pos: " + x + "," + y +
                " Adj: " + printNeighbors();
    }

}