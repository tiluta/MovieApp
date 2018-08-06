package com.movie.app.service;

import com.movie.app.model.Movie;

public interface MovieService {

    Movie findById(long id);
    void saveMovie(Movie movie);
    Movie findByName(String name);
    boolean isMovieExist(Movie movie);
    void updateExistentMovie(Movie movie);
    void deleteExistentMovie(Movie movie);

}
