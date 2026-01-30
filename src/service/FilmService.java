package service;

import exception.DuplicateResourceException;
import model.Film;
import repository.FilmRepository;
import repository.interfaces.FilmRepositoryI;


import java.sql.SQLException;
import java.util.ArrayList;

public class FilmService {
    private final FilmRepositoryI filmRepositoryI;

    public FilmService(FilmRepositoryI filmRepositoryI){
        this.filmRepositoryI = filmRepositoryI;
    }

    public Film createFilm(Film film) throws SQLException {
        film.validate();
        if(filmRepositoryI.existsByName(film.getName())){
            throw new DuplicateResourceException("Film with the name:" + film.getName() + " already exists");
        }
        return filmRepositoryI.create(film);
    }

    public ArrayList<Film> getAllFilms(){
        return (ArrayList<Film>) filmRepositoryI.getAll();
    }

    public Film getFilmById(int id){
        return filmRepositoryI.getById(id);
    }

    public Film updateFilm(int id, Film film) throws SQLException {
        film.validate();
        return filmRepositoryI.update(id, film);
    }

    public void deleteFilm(int id){
        filmRepositoryI.delete(id);
    }


}
