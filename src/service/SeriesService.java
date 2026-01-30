package service;

import exception.DuplicateResourceException;
import model.Series;
import repository.SeriesRepository;
import repository.interfaces.SeriesRepositoryI;


import java.util.ArrayList;

public class SeriesService {
    private final SeriesRepositoryI seriesRepositoryI;

    public SeriesService(SeriesRepositoryI seriesRepositoryI){
        this.seriesRepositoryI = seriesRepositoryI;
    }

    public Series createSeries(Series series){
        series.validate();
        if(seriesRepositoryI.existsByName(series.getName())){
            throw new DuplicateResourceException("Series with the name: " + series.getName() + " already exists");
        }
        return seriesRepositoryI.create(series);
    }

    public ArrayList<Series> getAllSeries(){
        return (ArrayList<Series>) seriesRepositoryI.getAll();
    }

    public Series getSeriesById(int id){
        return seriesRepositoryI.getById(id);
    }

    public Series updateSeries(int id, Series series){
        series.validate();
        return seriesRepositoryI.update(id, series);
    }

    public void deleteSeries(int id){
        seriesRepositoryI.delete(id);
    }
}
