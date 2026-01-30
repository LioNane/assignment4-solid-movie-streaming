import exception.*;
import model.*;
import repository.*;
import repository.interfaces.EpisodeRepositoryI;
import repository.interfaces.FilmRepositoryI;
import repository.interfaces.SeriesRepositoryI;
import service.*;
import utils.ReflectionUtils;
import utils.SortingUtils;

import java.util.*;

public class Main{
public static void main() {

    FilmRepositoryI filmRepository = new FilmRepository();
    SeriesRepositoryI seriesRepository = new SeriesRepository();
    EpisodeRepositoryI episodeRepository = new EpisodeRepository();

    FilmService filmService = new FilmService(filmRepository);
    SeriesService seriesService = new SeriesService(seriesRepository);
    EpisodeService episodeService = new EpisodeService(episodeRepository, seriesRepository);

    try{
        System.out.println("Create content");

        Film film1 = new Film(1, "Inception", 148, 6.8f);
        Film film2 = new Film(2, "Interstellar", 169, 8.6f);

        Series series1 = new Series(1, "Breaking Bad", 9.5f);

        Episode ep1 = new Episode(1, "Pilot", 58, series1.getId());
        Episode ep2 = new Episode(2, "Cat's in the Bag...", 48, series1.getId());


        filmService.create(film1);
        filmService.create(film2);

        seriesService.create(series1);

        episodeService.create(ep1);
        episodeService.create(ep2);

        System.out.println("All content types were created\n");

        System.out.println("Read all content types");

        System.out.println("Films: ");
        List<Film> allFilms = filmService.getAll();
        for (Film f : allFilms) {
            f.displayInfo();
            System.out.println();
        }

        System.out.println("Series: ");
        List<Series> allSeries = seriesService.getAll();
        for (Series s : allSeries) {
            s.displayInfo();
            System.out.println();
        }

        System.out.println("Episodes: ");
        List<Episode> allEpisodes = episodeService.getAll();
        for (Episode e : allEpisodes) {
            e.displayEpisodeInfo();
            System.out.println();
        }

        System.out.println("Sorting by rating");
        List<Film> films = filmService.getAll();
        SortingUtils.sort(films,
                (f1, f2) -> Float.compare(f2.getRating(), f1.getRating()));
        films.forEach(f -> System.out.println(f.getName() + " - " + f.getRating()));
        System.out.println("Highly rated films");
        films.stream().filter(Film::isHighlyRated).forEach(f -> System.out.println(f.getName()));

        System.out.println("Interface methods");
        film1.validate();
        Validatable.requireNonEmpty(film1.getName(), "Film name");
        film1.validateAndReturn(film1);

        System.out.println("Reflection");
        ReflectionUtils.printInfo(film1);
        ReflectionUtils.printInfo(series1);

        System.out.println("Update Content");

        film1.setRating(8.9f);
        filmService.update(film1.getId(), film1);
        System.out.println("Updated film Inception rating = " + film1.getRating());

        System.out.println("Validation example");
        try {
            Film invalidFilm = new Film(3, "", 100, 7.0f);
            filmService.create(invalidFilm);
        } catch (InvalidInputException e) {
            System.out.println("Validation error: " + e.getMessage());
        }
        System.out.println();

        System.out.println("Duplicate example");
        try {
            Film duplicateFilm = new Film(4, "Inception", 120, 6.0f);
            filmService.create(duplicateFilm);
        } catch (DuplicateResourceException e) {
            System.out.println("Duplicate error: " + e.getMessage());
        }
        System.out.println();

        System.out.println("Delete episode");
        episodeService.delete(2);
        System.out.println("Deleted episode with id = 2 successfully.\n");

        System.out.println("Playable interface examples:");
        film1.play();
        ep1.play();
        series1.play();
        System.out.println();

        System.out.println("Content concrete method");
        if(film1.isHighlyRated()){
            System.out.println("Film: " + film1.getName() + " is highly rated");
        } else{
            System.out.println("Film: " + film1.getName() + " is not highly rated");
        }

        System.out.println("Final lists");

        System.out.println("Films:");
        allFilms = filmService.getAll();
        for (Film f : allFilms) {
            f.displayInfo();
            System.out.println();
        }

        System.out.println("Series:");
        allSeries = seriesService.getAll();
        for (Series s : allSeries) {
            s.displayInfo();
            System.out.println();
        }

        System.out.println("Episodes:");
        allEpisodes = episodeService.getAll();
        for (Episode e : allEpisodes) {
            e.displayEpisodeInfo();
            System.out.println();
        }


    } catch (Exception e){
        System.out.println("Unexpected error: " + e.getMessage());

    }

}
}
