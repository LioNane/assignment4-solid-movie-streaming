package repository.interfaces;

import model.Episode;

public interface EpisodeRepositoryI extends CrudRepository<Episode>{
    boolean existsBySeriesIdAndEpisodeName(int seriesId, String name);
}
