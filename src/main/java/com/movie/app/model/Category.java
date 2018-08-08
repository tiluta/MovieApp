package com.movie.app.model;

import java.util.ArrayList;
import java.util.List;

public class Category {
    public long idCategory;
    public String name;
    public List<Movie> movies;

    public Category(long idCategory, String name)
    {
        this.idCategory = idCategory;
        this.name = name;
        movies = new ArrayList<Movie>();
    }

    public long getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(long idCategory) {
        this.idCategory = idCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }


}
