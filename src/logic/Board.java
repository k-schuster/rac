package logic;

import dao.Road;
import graph.Affiliation;
import graph.City;

import java.util.Collection;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;


public class Board {

    private Map<Integer, City> cities;
    private Set<Road> roads;

    public Board() {
        this.cities = new HashMap<Integer, City>();
        this.roads = new HashSet<Road>();
    }

    public int getNumberOfCities() {
        return cities.size();
    }

    public City getCity(int id) {
        return cities.get(id);
    }

    public boolean addCity(int id, City city) {
        if (cities.containsKey(id)) {
            return false;
        } else {
            cities.put(id, city);
            return true;
        }
    }

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

    public Collection<City> getCities() {
        return cities.values();
    }

    public Set<Road> getRoads() {
        return this.roads;
    }

    public Set<City> getNeighbors(int id) {
        return cities.get(id).getNeighbors();
    }

    public Set<City> getNeighbors(City city) {
        return city.getNeighbors();
    }

    public Set<City> getNeighbors(Set<City> setOfCities) {
        Set<City> result = new HashSet<City>();

        for(City city : setOfCities) {
            for (City neighbors : city.getNeighbors()) {
                result.add(neighbors);
            }
        }

        for (City city : setOfCities) {
            result.remove(city);
        }

        return result;
    }

    public boolean checkForSameAffiliation(Set<City> setOfCities,
                                           Affiliation affiliation) {
        for (City city : setOfCities) {
            if (city.getAffiliation() != affiliation)
                return false;
        }
        return true;
    }

}