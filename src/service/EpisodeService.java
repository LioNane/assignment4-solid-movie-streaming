package service;

import exception.DuplicateResourceException;
import model.Episode;
import repository.EpisodeRepository;
import repository.interfaces.EpisodeRepositoryI;

import java.util.ArrayList;

public class EpisodeService {
    private final EpisodeRepositoryI episodeRepositoryI;

    public EpisodeService(EpisodeRepositoryI episodeRepositoryI){
        this.episodeRepositoryI = episodeRepositoryI;
    }

    public Episode createEpisode(Episode episode){
        episode.validate();


        if(episodeRepositoryI.existsBySeriesIdAndEpisodeName(episode.getSeriesId(), episode.getName())){
            throw new DuplicateResourceException("Episode with the name: " + episode.getName() + " already exists in series_id = " + episode.getSeriesId());
        }
        return episodeRepositoryI.create(episode);
    }

    public ArrayList<Episode> getAllEpisodes(){
        return (ArrayList<Episode>) episodeRepositoryI.getAll();
    }

    public Episode getEpisodeById(int id){
        return episodeRepositoryI.getById(id);
    }

    public Episode updateEpisode(int id, Episode episode){
        episode.validate();

        return episodeRepositoryI.update(id, episode);
    }

    public void deleteEpisode(int id){
        episodeRepositoryI.delete(id);
    }
}
