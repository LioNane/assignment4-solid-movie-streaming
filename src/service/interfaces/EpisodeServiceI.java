package service.interfaces;

import model.Episode;

import java.util.List;

public interface EpisodeServiceI {
    Episode create(Episode episode);
    List<Episode> getAll();
    Episode getById(int id);
    Episode update(int id, Episode episode);
    void delete(int id);
}
