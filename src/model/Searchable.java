package model;

public interface Searchable<T> {
    abstract boolean matches(String query);

    static String normalize(String s){
        return s == null ? "" : s.trim().toLowerCase();
    }
}
