package repository.interfaces;

import model.Film;

public interface FilmRepositoryI extends CrudRepository<Film>{
    boolean existsByName(String name);
}
