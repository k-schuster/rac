package logic;

import dao.Road;
import graph.Affiliation;
import graph.Alliance;
import graph.City;
import logic.logicexceptions.NoSuchCityException;

import java.util.Collection;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

/**
 * This class stores all cities and their connections, modulating a graph
 * and contains the basic logic with which to access and manipulate it.
 *
 * @author Schuster
 * @version 0.2
 */
public class Board {

    private Map<Integer, City> cities;
    private Set<Road> roads;
    public Set<Alliance> alliances;

    /**
     * Constructs an empty board and initializes all data structures within,
     * ready to be filled.
     */
    public Board() {
        this.cities = new HashMap<Integer, City>();
        this.roads = new HashSet<Road>();
        this.alliances = new HashSet<Alliance>();
    }

    /**
     * Returns the number of cities stored on this board.
     * @return Number of cities stored on this board.
     */
    public int getNumberOfCities() {
        return cities.size();
    }

    /**
     * Returns the City with the specified id.
     * @param id Integer value representing the id of a city.
     * @return City with the specified id from this board.
     * @throws NoSuchCityException if there is no City with the specified id.
     */
    public City getCity(int id) throws NoSuchCityException {
        if (cities.containsKey(id))
            return cities.get(id);
        else
            throw new NoSuchCityException("No city with id: " + id);
    }

    /**
     * Returns a Collection of all the cities on this board.
     * @return Collection of all the cities on this board.
     */
    public Collection<City> getCities() {
        return cities.values();
    }

    /**
     * Returns a Set containing all the cities which have the specified ids.
     * @param idsOfCities Set of ids from which to fetch the cities.
     * @return Set containing all the cities which have the specified ids.
     * @throws NoSuchCityException if one of the specified ids has no
     * corresponding city.
     */
    public Set<City> getCities(Set<Integer> idsOfCities) throws NoSuchCityException {
        Set<City> result = new HashSet<City>();
        for (int id : idsOfCities) {
            result.add(getCity(id));
        }
        return result;
    }

    /**
     * @todo Make this throw an excetion?
     * Adds and associates the specified city with the specified id
     * on this board.
     * @param id Id with which the specified city is to be associated.
     * @param city City to be associated with the specified id.
     * @return true if city has been successfully added and associated,
     * false otherwise.
     */
    public boolean addCity(int id, City city) {
        if (cities.containsKey(id)) {
            return false;
        } else {
            cities.put(id, city);
            return true;
        }
    }

    /**
     * @todo Make this also throw a CityNotFoundException?
     * Creates an undirected edge between two cities, represented by their ids
     * by adding one city to the set of adjacent (neighboring) cities of
     * the other and vice versa.
     * Then stores this edge as a road, which is essentially a tuple,
     * on this board. This is currently only used to simplify the visualization
     * of the graph modulated by the cities on this board by the gui package.
     * @param city1 Id of first city to be connected by a road (edge).
     * @param city2 Id of the second city to be connected by a road (edge).
     * @return true is edge was successfully established, false otherwise.
     */
    public boolean addRoad(int city1, int city2) {
        if (cities.containsKey(city1) && cities.containsKey(city2)) {
            City c1 = cities.get(city1);
            City c2 = cities.get(city2);

            // add cities as neighbors to one another
            c1.addNeighbor(c2);
            c2.addNeighbor(c1);

            // store a unique tuple of each road in a set
            roads.add(new Road(c1, c2));
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns a Set containing all the roads on this board.
     * @return Set containing all the roads on this board.
     */
    public Set<Road> getRoads() {
        return this.roads;
    }

    /**
     * Returns a set containing all adjacent (neighboring) cities of
     * the specified city, represented by it's id.
     * @param id Id of the city which neighbors are to be returned,
     * @return Set containing all adjacent cities of the specified city.
     * @throws NoSuchCityException if there is no city with the specified id.
     */
    public Set<City> getNeighbors(int id) throws NoSuchCityException {
        return getCity(id).getNeighbors();
    }

    /**
     * Returns a set containing all adjacent (neighboring) cities of
     * the specified city.
     * @param city City which neighbors are to be returned.
     * @return Set containing all adjacent cities of the specified city.
     */
    public Set<City> getNeighbors(City city) {
        return city.getNeighbors();
    }

    /**
     * Given a Set of cities, returns a Set which contains all cities
     * adjacent to at least one of the given cities but contains
     * non of the given cities themselves.
     * @param setOfCities Set of cities which neighbors are to be returned.
     * @return Set of cities adjacent to at least one of the given cities.
     */
    public Set<City> getNeighbors(Set<City> setOfCities) {
        Set<City> result = new HashSet<City>();

        for(City city : setOfCities) {
            for (City neighbor : city.getNeighbors()) {
                result.add(neighbor);
            }
        }

        for (City city : setOfCities) {
            result.remove(city);
        }

        return result;
    }

    /**
     * Given a Set of cities represented by an alliance, returns a Set,
     * which contains all cities adjacent to at least one of the given cities
     * but contains non of the given cities themselves.
     * @param alliance Set of cities which neighbors are to be returned.
     * @return Set of cities adjacent to at least one of the given cities.
     */
    public Set<City> getNeighbors(Alliance alliance) {
        Set<City> result = new HashSet<City>();
        Set<City> allianceMembers = alliance.getMembers();

        for(City member : allianceMembers) {
            for (City neighbor : member.getNeighbors()) {
                result.add(neighbor);
            }
        }

        for (City member : allianceMembers) {
            result.remove(member);
        }

        return result;
    }

    /**
     * Checks if the given Set of cities all share the specified affiliation.
     * @param setOfCities Set of cities to be checked.
     * @param affiliation Affiliation to be checked for.
     * @return true if all given cities share the same affiliation,
     * false otherwise.
     */
    public boolean checkForSameAffiliation(Set<City> setOfCities,
                                           Affiliation affiliation) {
        for (City city : setOfCities) {
            if (city.getAffiliation() != affiliation)
                return false;
        }
        return true;
    }


    /**
     * Searches for strongly connected components within the graph of cities
     * on this board and adds them as alliances to this board.
     * Strongly connected are cities (vertices) which are all connected to each
     * other and share the same affiliation.
     * This is accomplished by going through every city on this board, if the
     * city is marked as unvisited, creating new alliance with the affiliation
     * of that city and then recursively searching for all strongly connected
     * components of that city, adding them to the alliance and marking them
     * as visited. After all components have been found and added, the alliance
     * itself is added to this board.
     */
    public void initAlliances() {
        // mark all cities as unvisited
        for (City c : getCities()) {
            c.visited = false;
        }
        for (City c : getCities()) {
            if (!c.visited) {
                Alliance a = new Alliance(c.getAffiliation());
                addCitiesToAlliances(a, c);
                alliances.add(a);
            }
        }
    }

    /**
     * Helper method adds city c to alliance a and then
     * recursively searches for all strongly connected components of
     * this city, by invoking this method on  all adjacent (neighboring)
     * cities that share the same affiliation and have yet not been visited.
     * @param a Alliance which stores all strongly connected components found.
     * @param c City which is either the root or strongly connected to it.
     */
    private void addCitiesToAlliances(Alliance a, City c) {
        c.visited = true;
        a.addMember(c);
        for (City neighbor : c.getNeighbors()) {
            if (neighbor.getAffiliation() == c.getAffiliation()) {
                if (!neighbor.visited) {
                    addCitiesToAlliances(a, neighbor);
                }
            }
        }
    }

    /**
     * Calculate the points the player with the specified affiliation
     * currently holds.
     * Each city within an alliance that holds the specified affiliation
     * and each city within an alliance with neutral affiliation, that is
     * adjacent only to cities with the specified affiliation, are worth
     * one point.
     * @param affiliation Affiliation to be checked points for.
     * @return Integer value representing the points the player with the
     * specified affiliation currently holds.
     */
    public int getPoints(Affiliation affiliation) {
        int result = 0;
        for (Alliance a : alliances) {
            Affiliation allianceAffiliation = a.getAffiliation();
            if (allianceAffiliation == affiliation) {
                result += a.size();
            } else if (allianceAffiliation == Affiliation.N) {
                if (checkForSameAffiliation(getNeighbors(a), affiliation)) {
                    result += a.size();
                }
            }
        }
        return result;
    }


}