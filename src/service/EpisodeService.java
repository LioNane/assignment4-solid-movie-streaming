package service;

import exception.DuplicateResourceException;
import model.Episode;
import model.Film;
import repository.EpisodeRepository;
import repository.interfaces.EpisodeRepositoryI;
import repository.interfaces.SeriesRepositoryI;
import service.interfaces.EpisodeServiceI;
import utils.SortingUtils;

import java.util.ArrayList;
import java.util.List;

public class EpisodeService implements EpisodeServiceI {
    private final EpisodeRepositoryI episodeRepository;
    private final SeriesRepositoryI seriesRepository;

    public EpisodeService(EpisodeRepositoryI episodeRepository, SeriesRepositoryI seriesRepository){
        this.episodeRepository = episodeRepository;
        this.seriesRepository = seriesRepository;
    }

    @Override
    public Episode create(Episode episode){
        episode.validate();

        seriesRepository.getById(episode.getSeriesId());

        if(episodeRepository.existsBySeriesIdAndEpisodeName(episode.getSeriesId(), episode.getName())){
            throw new DuplicateResourceException("Episode with the name: " + episode.getName() + " already exists in series_id = " + episode.getSeriesId());
        }
        return episodeRepository.create(episode);
    }

    @Override
    public List<Episode> getAll(){
        return (ArrayList<Episode>) episodeRepository.getAll();
    }

    @Override
    public Episode getById(int id){
        return episodeRepository.getById(id);
    }

    @Override
    public Episode update(int id, Episode episode){
        episode.validate();

        seriesRepository.getById(episode.getSeriesId());

        return episodeRepository.update(id, episode);
    }

    @Override
    public void delete(int id){
        episodeRepository.delete(id);
    }

    public void sortByDuration(){
        SortingUtils.sort(
                getAll(),
                (e1, e2) -> Float.compare(e2.getDuration(), e1.getDuration())
        );
    }
}
