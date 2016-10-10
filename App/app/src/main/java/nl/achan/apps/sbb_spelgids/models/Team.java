package nl.achan.apps.sbb_spelgids.models;

/**
 * Created by fhict on 07/10/16.
 */
public class Team {

    /**
     * As needed by Cupboard for the persistence layer.
     */
    public long       _id = -1;

    /**
     * Name of the team. Can be either predefined or user-generated (moderation vs. self-expression).
     */
    public String    name = "onbekend";

    /**
     * Total score across all levels.
     */
    public int      score = 0;

    @Override
    public String toString() {
        return "Team{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }
}
