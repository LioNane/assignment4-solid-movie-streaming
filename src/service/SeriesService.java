package service;

import exception.DuplicateResourceException;
import model.Film;
import model.Series;
import repository.SeriesRepository;
import repository.interfaces.SeriesRepositoryI;
import service.interfaces.SeriesServiceI;
import utils.SortingUtils;


import java.util.ArrayList;
import java.util.List;

public class SeriesService implements SeriesServiceI {
    private final SeriesRepositoryI seriesRepository;

    public SeriesService(SeriesRepositoryI seriesRepository){
        this.seriesRepository = seriesRepository;
    }

    @Override
    public Series create(Series series){
        series.validate();
        if(seriesRepository.existsByName(series.getName())){
            throw new DuplicateResourceException("Series with the name: " + series.getName() + " already exists");
        }
        return seriesRepository.create(series);
    }

    @Override
    public List<Series> getAll(){
        return (List<Series>) seriesRepository.getAll();
    }

    @Override
    public Series getById(int id){
        return seriesRepository.getById(id);
    }

    @Override
    public Series update(int id, Series series){
        series.validate();
        return seriesRepository.update(id, series);
    }

    @Override
    public void delete(int id){
        seriesRepository.delete(id);
    }

    public void sortByRating(){
        SortingUtils.sort(
                getAll(),
                (s1, s2) -> Float.compare(s2.getRating(), s1.getRating())
        );
    }
}
