package graph;


import java.util.Set;
import java.util.HashSet;

/**
 * This class is used to represent strongly connected components of a graph.
 * Internally uses HashSets to store individual vertices.
 * In our specific case, this class is used to store connected cities which
 * share the same affiliation.
 *
 * @author Schuster
 * @version 0.2
 */
public class Alliance {

    private Set<City> members;
    private Affiliation affiliation;

    /**
     * Constructs an empty alliance with the given affiliation.
     * @param affiliation The affiliation this alliance should have.
     */
    public Alliance(Affiliation affiliation) {
        this.members = new HashSet<City>();
        this.affiliation = affiliation;
    }

    /**
     * Returns the number of cities in this alliance.
     * @return the number of cities in this alliance
     */
    public int size() {
        return members.size();
    }

    /**
     * Returns a Set containing all cities in this alliance.
     * @return a Set containing all cities in this alliance.
     */
    public Set<City> getMembers() {
        return members;
    }

    public boolean addMember(City city) {
        if (members.contains(city))
            return false;
        else {
            members.add(city);
            return true;
        }
    }

    /**
     * Removes the specified City from this alliance, if it is present.
     * @param city City to be removed from this alliance.
     */
    public void removeMember(City city) {
        members.remove(city);
    }

    /**
     * Returns the affiliation of this alliance, which is the shared
     * affiliation of all cities within this alliance.
     * @return Affiliation of this alliance.
     */
    public Affiliation getAffiliation() {
        return affiliation;
    }

}
