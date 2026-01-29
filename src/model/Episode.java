package model;


import exception.InvalidInputException;

public class Episode implements Validatable<Episode>, Playable, Searchable<Episode>{
    private int id;
    private String name;
    private int duration;
    private int seriesId;

    public Episode(int id, String name, int duration, int series_id){
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.seriesId = series_id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getSeriesId() {
        return seriesId;
    }

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }

    public void displayEpisodeInfo(){
        System.out.println("Episode title:" + name + "\n" +
                "ID: " + id + "\n" +
                "Duration: " + duration + "\n" +
                "Series ID: " + seriesId);
    }

    @Override
    public void validate() throws InvalidInputException {
        if (id <= 0) throw new InvalidInputException("id must be > 0");
        Validatable.requireNonEmpty(name, "name");
        if (duration <= 0) throw new InvalidInputException("duration must be > 0");
        if (seriesId <= 0) throw new InvalidInputException("seriesId must be > 0");
    }

    @Override
    public void play(){
        System.out.println("Play episode: " + name);
    }

    @Override
    public boolean matches(String query) {
        String q = Searchable.normalize(query);
        return Searchable.normalize(name).contains(q);
    }
}
