package graph;


import java.util.Set;
import java.util.HashSet;

public class Alliance {

    private Set<City> members;
    private Affiliation affiliation;


    public Alliance(Affiliation affiliation) {
        this.members = new HashSet<City>();
        this.affiliation = affiliation;
    }


    public int size() {
        return members.size();
    }

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

    public void removeMember(City city) {
        members.remove(city);
    }

    public Affiliation getAffiliation() {
        return affiliation;
    }

}
