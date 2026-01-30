package service.interfaces;

import model.Series;

import java.util.List;

public interface SeriesServiceI {
    Series create(Series series);
    List<Series> getAll();
    Series getById(int id);
    Series update(int id, Series series);
    void delete(int id);
}
