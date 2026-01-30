package model;


import exception.InvalidInputException;

abstract public class Content implements Playable, Searchable<Content>{
    private int id;
    private String name;
    private float rating;

    protected Content(int id, String name, float rating){
        setId(id);
        setName(name);
        setRating(rating);
    }

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public float getRating(){ return rating;}
    public void setRating(float rating){this.rating = rating;}

    abstract protected int countDuration();
    abstract protected String getContentType();
    abstract public void displayInfo();

    public boolean isHighlyRated(){
        return (rating >= 8.0);
    }

    @Override
    public boolean matches(String query){
        String q = Searchable.normalize(query);
        return Searchable.normalize(name).contains(q);
    }
    protected void validateBaseFields() throws InvalidInputException {
        if (id <= 0) {
            throw new InvalidInputException("id must be > 0");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidInputException("name must not be empty");
        }
        if (rating < 0 || rating > 10) {
            throw new InvalidInputException("rating must be between 0 and 10");
        }
    }
}
