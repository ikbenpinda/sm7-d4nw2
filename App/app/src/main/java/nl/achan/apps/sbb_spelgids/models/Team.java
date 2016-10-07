package nl.achan.apps.sbb_spelgids.models;

/**
 * Created by fhict on 07/10/16.
 */
public class Team {
    public String name = "onbekend";
    public int score = 0;

    @Override
    public String toString() {
        return "Team{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }
}
