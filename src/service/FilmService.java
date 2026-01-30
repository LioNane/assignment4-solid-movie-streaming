package service;

import exception.DuplicateResourceException;
import model.Film;
import repository.FilmRepository;
import repository.interfaces.FilmRepositoryI;
import service.interfaces.FilmServiceI;
import utils.SortingUtils;


import java.sql.SQLException;
import java.util.List;

public class FilmService implements FilmServiceI {
    private final FilmRepositoryI filmRepository;

    public FilmService(FilmRepositoryI filmRepository){
        this.filmRepository = filmRepository;
    }

    @Override
    public Film create(Film film) {
        film.validate();
        if(filmRepository.existsByName(film.getName())){
            throw new DuplicateResourceException("Film with the name:" + film.getName() + " already exists");
        }
        return filmRepository.create(film);
    }

    @Override
    public List<Film> getAll(){
        return (List<Film>) filmRepository.getAll();
    }

    @Override
    public Film getById(int id){
        return filmRepository.getById(id);
    }

    @Override
    public Film update(int id, Film film) {
        film.validate();
        return filmRepository.update(id, film);
    }

    @Override
    public void delete(int id){
        filmRepository.delete(id);
    }

    public void sortByRating(){
        SortingUtils.sort(
                getAll(),
                (f1, f2) -> Float.compare(f2.getRating(), f1.getRating())
        );
    }
}
