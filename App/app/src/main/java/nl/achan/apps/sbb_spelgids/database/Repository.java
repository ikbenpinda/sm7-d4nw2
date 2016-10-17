package nl.achan.apps.sbb_spelgids.database;

import java.util.List;

/**
 * Created by fhict on 17/10/16.
 */
public interface Repository<T> {

    T get(long id);
    List<T> getAll();
    boolean insert(T row);
    boolean update(T row);
    boolean delete(T row);

}
