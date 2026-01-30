package service.interfaces;

import model.Film;
import java.util.List;

public interface FilmServiceI {
    Film create(Film film);
    List<Film> getAll();
    Film getById(int id);
    Film update(int id, Film film);
    void delete(int id);
}
