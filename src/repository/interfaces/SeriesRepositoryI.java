package repository.interfaces;

import model.Series;

public interface SeriesRepositoryI extends CrudRepository<Series>{
    boolean existsByName(String name);
}
