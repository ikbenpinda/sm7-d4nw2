package nl.achan.apps.sbb_spelgids.leaderboard;

import java.util.ArrayList;
import java.util.List;

import nl.achan.apps.sbb_spelgids.database.Repository;

/**
 * In-memory repository of highscores for the current group.
 *
 * Created by fhict on 17/10/16.
 */
public class HighscoreRepository implements Repository<Highscore> {

    public ArrayList<Highscore> highscores = new ArrayList<>();

    @Override
    public Highscore get(long id) {
        throw new RuntimeException("Not supported yet.");
    }

    @Override
    public List<Highscore> getAll() {
        return highscores;
    }

    @Override
    public boolean insert(Highscore row) {
        return highscores.add(row);
    }

    @Override
    public boolean update(Highscore row) {
        if (highscores.remove(row) && highscores.add(row))
                return true;
        return false;
    }

    @Override
    public boolean delete(Highscore row) {
        return highscores.remove(row);
    }
}
