package com.movie.app.service;

import com.movie.app.model.Movie;

public interface MovieService {

    Movie findById(long id);
    void create(Movie movie);
    void update(Movie movie);
    void delete(Movie movie);

}
